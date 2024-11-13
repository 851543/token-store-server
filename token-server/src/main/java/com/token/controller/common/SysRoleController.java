package com.token.controller.common;

import com.token.constant.MessageConstant;
import com.token.entity.SysRole;
import com.token.result.Result;
import com.token.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common/sysrole")
@Slf4j
@Api(tags = "角色")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 添加
     * @param sysRole
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "添加角色")
    public Result<String> insertSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.insertSysRole(sysRole);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 修改
     * @param sysRole
     * @return
     */
    @PutMapping()
    @ApiOperation(value = "修改角色")
    public Result<String> updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }
}
