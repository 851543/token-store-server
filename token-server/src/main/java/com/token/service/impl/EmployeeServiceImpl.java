package com.token.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.token.constant.*;
import com.token.dto.EmployeeLoginDTO;
import com.token.dto.EmployeePageQueryDTO;
import com.token.entity.Employee;
import com.token.entity.EmployeeLoginDetails;
import com.token.exception.AccountIsDisableException;
import com.token.exception.AccountNotExistException;
import com.token.exception.PasswordErrorException;
import com.token.exception.UsernameIsExistException;
import com.token.mapper.EmployeeMapper;
import com.token.mapper.SysRoleMapper;
import com.token.properties.JwtProperties;
import com.token.result.PageResult;
import com.token.service.EmployeeService;
import com.token.utils.JwtUtil;
import com.token.vo.EmployeeLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 员工登陆
     *
     * @param employeeLoginDTO
     * @return
     */
    public EmployeeLoginVO login(EmployeeLoginDTO employeeLoginDTO) {
        //  Security认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(employeeLoginDTO.getUsername(),DigestUtils.md5DigestAsHex(employeeLoginDTO.getPassword().getBytes()));
        //  获取认证信息
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //  员工登陆详细信息
        EmployeeLoginDetails employeeLoginDetails = (EmployeeLoginDetails) authenticate.getPrincipal();
        Employee employee = employeeLoginDetails.getEmployee();
        if (employee.getStatus() == StatusConstant.DISABLE) {
            // 账号已禁用
            throw new AccountIsDisableException(MessageConstant.ACCOUNT_IS_DISABLE);
        }

        // 封装empId
        Map<String, Object> claims = new HashMap();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());

        // 生产token令牌
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims
        );

        // authenticate的登陆对象信息存入redis
        redisTemplate.opsForValue().set(RedisKeyConstant.TOKEN_ADMIN_LOGIN_INFO_KEY_ + employee.getId(), employeeLoginDetails, jwtProperties.getAdminTtl(), TimeUnit.SECONDS);

        return EmployeeLoginVO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .username(employee.getUsername())
                .token(token)
                .build();
    }

    /**
     * 新增员工-用户名是唯一标识
     *
     * @param employee
     */
    public void insert(Employee employee) {
        Employee ByEmployee = employeeMapper.getByUsername(employee.getUsername());
        if (!ObjectUtils.isEmpty(ByEmployee)) {
            throw new UsernameIsExistException(MessageConstant.USERNAME_IS_EXIST);
        }
        employee.setPassword(passwordEncoder.encode(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes())));
        employee.setStatus(StatusConstant.DISABLE);
        employeeMapper.insert(employee);

        //  绑定角色表
        sysRoleMapper.insertSysUserRole(employee.getId(),SysRoleConstant.EMPLOYEE);
    }

    /**
     * 修改员工
     *
     * @param employee
     */
    public void update(Employee employee) {
        Employee ByEmployee = employeeMapper.getByUsername(employee.getUsername());
        if (!ObjectUtils.isEmpty(ByEmployee)) {
            throw new UsernameIsExistException(MessageConstant.USERNAME_IS_EXIST);
        }
        //  二次加密
        employee.setPassword(passwordEncoder.encode(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes())));
        employeeMapper.update(employee);
    }

    /**
     * 删除员工
     *
     * @param ids
     */
    public void delete(Long[] ids) {
        //  校验员工状态
        List<Long> status = employeeMapper.getEnableStatusByIds(ids);
        if((!ObjectUtils.isEmpty(status)) && status.size() > 0) {
            //  抛出异常
            throw new AccountIsDisableException(MessageConstant.EMPLOYEE_STATUS_IS_ENABLE);
        }
        employeeMapper.delete(ids);
    }

    /**
     * 修改员工状态
     *
     * @param status
     */
    public void status(Long id, Long status) {
        employeeMapper.update(Employee.builder().id(id).status(status.intValue()).build());
    }

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    public PageResult page(EmployeePageQueryDTO employeePageQueryDTO) {
        // 开启PageHelper插件
        PageHelper.startPage(employeePageQueryDTO.getPageNow(),employeePageQueryDTO.getPageSize());

        // 查询数据集合
        Page<Employee> page = (Page<Employee>) employeeMapper.queryList(employeePageQueryDTO);

        return PageResult.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();
    }

    /**
     * 查询员工信息
     *
     * @param id
     */
    public Employee getEmployeeInfo(Long id) {
        return employeeMapper.getEmployeeById(id);
    }
}
