package com.token.service.impl;

import com.token.entity.SysMenu;
import com.token.mapper.SysMenuMapper;
import com.token.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public void insertSysMenu(SysMenu sysMenu) {
        sysMenuMapper.insertSysMenu(sysMenu);
    }

    @Override
    public void updateSysMenu(SysMenu sysMenu) {
        sysMenuMapper.updateSysMenu(sysMenu);
    }
}
