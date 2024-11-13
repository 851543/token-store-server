package com.token.mapper;

import com.token.annotation.AutoFill;
import com.token.entity.SysMenu;
import com.token.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface SysMenuMapper {

    /**
     * 根据id查询权限信息
     *
     * @param id
     * @return
     */
    Set<String> getPermsById(Long id);

    /**
     * 添加
     *
     * @param sysMenu
     */
    @Insert("insert into sys_menu(menu_name, perms, status, create_time, update_time, create_user, update_user) VALUES " +
            "(#{menuName},#{perms},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    void insertSysMenu(SysMenu sysMenu);

    /**
     * 修改
     *
     * @param sysMenu
     */
    @AutoFill(OperationType.UPDATE)
    void updateSysMenu(SysMenu sysMenu);
}
