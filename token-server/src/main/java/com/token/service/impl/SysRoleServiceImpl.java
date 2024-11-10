package com.token.service.impl;

import com.token.entity.SysRole;
import com.token.mapper.SysRoleMapper;
import com.token.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 添加
     * @param sysRole
     */
    public void insertSysRole(SysRole sysRole) {
        sysRoleMapper.insertSysRole(sysRole);
    }

    /**
     * 修改
     * @param sysRole
     */
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }
}
