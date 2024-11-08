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
    @Insert("insert into sys_role(name, role_key, status, del_flag, create_by, create_time, update_by, update_time, remark) VALUES " +
            "(#{name},#{roleKey},#{status},#{delFlag},#{createBy},#{createTime},#{updateBy},#{updateTime},#{remark})")
    @AutoFill(OperationType.INSERT)
    void insertSysRole(SysRole sysRole);

    /**
     * 修改
     * @param sysRole
     */
    @AutoFill(OperationType.UPDATE)
    void updateSysRole(SysRole sysRole);
}
