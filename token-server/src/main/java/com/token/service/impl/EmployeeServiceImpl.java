package com.token.service.impl;

import com.token.constant.MessageConstant;
import com.token.dto.EmployeeLoginDTO;
import com.token.entity.Employee;
import com.token.exception.AccountNotExistException;
import com.token.exception.PasswordNotRightException;
import com.token.mapper.EmployeeMapper;
import com.token.service.EmployeeService;
import com.token.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登陆
     * @param employeeLoginDTO
     * @return
     */
    public EmployeeLoginVO login(EmployeeLoginDTO employeeLoginDTO) {
        Employee employee = employeeMapper.getByUsername(employeeLoginDTO.getUsername());
        if (employee == null){
            throw new AccountNotExistException(MessageConstant.ACCOUNT_NOT_EXIST);
        }
        if (!employee.getPassword().equals(DigestUtils.md5DigestAsHex(employeeLoginDTO.getPassword().getBytes()))){
            throw new PasswordNotRightException(MessageConstant.PASSWORD_NOT_RIGHT);
        }
        // TODO
        return null;
    }
}
