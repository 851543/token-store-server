package com.token.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.token.entity.EmployeeLoginDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * 自定义权限实现，ss取自SpringSecurity首字母
 * @author ROCKY
 */
@Service("ss")
public class PermissionService {

    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";

    /**
     * 验证用户是否具备某权限
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermi(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        EmployeeLoginDetails employeeLoginDetails = (EmployeeLoginDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (ObjectUtils.isEmpty(employeeLoginDetails) || CollectionUtils.isEmpty(employeeLoginDetails.getPermissions())) {
            return false;
        }
        return hasPermissions(employeeLoginDetails.getPermissions(), permission);
    }

    /**
     * 验证用户是否不具备某权限，与 hasPermi逻辑相反
     * @param permission 权限字符串
     * @return 用户是否不具备某权限
     */
    public boolean lacksPermi(String permission) {
        return hasPermi(permission) != true;
    }

    /**
     * 判断是否包含权限
     * @param permissions 权限列表
     * @param permission  权限字符串
     * @return 用户是否具备某权限
     */
    private boolean hasPermissions(Set<String> permissions, String permission) {
        return permissions.contains(ALL_PERMISSION) || permissions.contains(StringUtils.trim(permission));
    }
}
