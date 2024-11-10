package com.token.service;

import com.token.entity.SysRole;

public interface SysRoleService {
    /**
     * 添加
     * @param sysRole
     */
    void insertSysRole(SysRole sysRole);

    /**
     * 修改
     * @param sysRole
     */
    void updateSysRole(SysRole sysRole);
}
