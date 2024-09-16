package com.token.service.impl;

import com.token.constant.JwtClaimsConstant;
import com.token.constant.MessageConstant;
import com.token.constant.StatusConstant;
import com.token.dto.EmployeeLoginDTO;
import com.token.entity.Employee;
import com.token.exception.AccountIsDisableException;
import com.token.exception.AccountNotExistException;
import com.token.exception.PasswordErrorException;
import com.token.mapper.EmployeeMapper;
import com.token.properties.JwtProperties;
import com.token.service.EmployeeService;
import com.token.utils.JwtUtil;
import com.token.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private JwtProperties jwtProperties;

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
        return EmployeeLoginVO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .username(employee.getUsername())
                .token(token)
                .build();
    }
}
