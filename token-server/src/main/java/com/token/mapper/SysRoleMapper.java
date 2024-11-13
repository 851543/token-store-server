package com.token.mapper;

import com.token.annotation.AutoFill;
import com.token.entity.SysRole;
import com.token.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMapper {
    /**
     * 添加
     * @param sysRole
     */
    @Insert("insert into sys_role(name, role_key, status, create_time, update_time, create_user, update_user) VALUES " +
            "(#{name},#{roleKey},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    void insertSysRole(SysRole sysRole);

    /**
     * 修改
     * @param sysRole
     */
    @AutoFill(OperationType.UPDATE)
    void updateSysRole(SysRole sysRole);

    /**
     * 关联角色表
     * @param emId
     * @param roleId
     */
    @Insert("insert into sys_user_role(user_id, role_id) values (#{emId},#{roleId})")
    void insertSysUserRole(Long emId,Integer roleId);
}
