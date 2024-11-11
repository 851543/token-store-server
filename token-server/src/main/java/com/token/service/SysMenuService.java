package com.token.service;

import com.token.entity.SysMenu;

public interface SysMenuService {
    /**
     * 添加
     * @param sysMenu
     */
    void insertSysMenu(SysMenu sysMenu);

    /**
     * 修改
     * @param sysMenu
     */
    void updateSysMenu(SysMenu sysMenu);
}
