package com.token.service.impl;

import com.token.constant.*;
import com.token.dto.EmployeeLoginDTO;
import com.token.entity.Employee;
import com.token.exception.AccountIsDisableException;
import com.token.exception.AccountNotExistException;
import com.token.exception.PasswordErrorException;
import com.token.exception.UsernameIsExistException;
import com.token.mapper.EmployeeMapper;
import com.token.properties.JwtProperties;
import com.token.service.EmployeeService;
import com.token.utils.JwtUtil;
import com.token.vo.EmployeeLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
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

    /**
     * 员工登陆
     *
     * @param employeeLoginDTO
     * @return
     */
    public EmployeeLoginVO login(EmployeeLoginDTO employeeLoginDTO) {
        Employee employee = employeeMapper.getByUsername(employeeLoginDTO.getUsername());
        if (employee == null) {
            // 账号不存在
            throw new AccountNotExistException(MessageConstant.ACCOUNT_NOT_EXIST);
        }
        if (!employee.getPassword().equals(DigestUtils.md5DigestAsHex(employeeLoginDTO.getPassword().getBytes()))) {
            // 密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
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

        // token令牌存入redis
        redisTemplate.opsForValue().set(RedisKeyConstant.REDIS_ADMIN_TOKEN_KEY_ + employee.getId(), token, jwtProperties.getAdminTtl(), TimeUnit.SECONDS);

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
        if (ByEmployee != null) {
            throw new UsernameIsExistException(MessageConstant.USERNAME_IS_EXIST);
        }
        employee.setPassword(DigestUtils.md5DigestAsHex(PassWordConstant.DEFAULT_PASSWORD.getBytes()));
        employee.setStatus(StatusConstant.DISABLE);
        employeeMapper.insert(employee);
    }

    /**
     * 修改员工
     *
     * @param employee
     */
    public void update(Employee employee) {
        employeeMapper.update(employee);
    }
}
