package com.token.service.impl;

import com.token.constant.MessageConstant;
import com.token.entity.Employee;
import com.token.entity.EmployeeLoginDetails;
import com.token.exception.AccountNotExistException;
import com.token.mapper.EmployeeMapper;
import com.token.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Set;

@Service
public class EmployeeLoginDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeMapper.getByUsername(username);
        if (ObjectUtils.isEmpty(employee)){
            throw new AccountNotExistException(MessageConstant.ACCOUNT_NOT_EXIST);
        }
        //  查询员工的权限
        Set<String> permissionKeyList =  sysMenuMapper.getPermsById(employee.getId());
        return new EmployeeLoginDetails(employee,permissionKeyList);
    }
}
