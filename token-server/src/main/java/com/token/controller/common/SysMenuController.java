package com.token.controller.common;


import com.token.constant.MessageConstant;
import com.token.entity.SysMenu;
import com.token.result.Result;
import com.token.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common/sysmenu")
@Slf4j
@Api(tags = "菜单")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 添加
     * @param sysMenu
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "添加菜单")
    public Result<String> insertSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.insertSysMenu(sysMenu);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    @PutMapping()
    @ApiOperation(value = "修改菜单")
    public Result<String> updateSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateSysMenu(sysMenu);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

}
