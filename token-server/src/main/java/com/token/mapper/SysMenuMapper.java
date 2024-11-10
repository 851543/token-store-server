package com.token.mapper;

import com.token.annotation.AutoFill;
import com.token.entity.SysMenu;
import com.token.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    /**
     * 根据id查询权限信息
     * @param id
     * @return
     */
    List<String> getPermsById(Long id);

    /**
     * 添加
     * @param sysMenu
     */
    @Insert("insert into sys_menu(menu_name, path, component, visible, status, perms, icon, create_by, create_time, update_by, update_time, del_flag, remark) VALUES " +
            "(#{menuName},#{path},#{component},#{visible},#{status},#{perms},#{icon},#{createBy},#{createTime},#{updateBy},#{updateTime},#{delFlag},#{remark})")
    @AutoFill(OperationType.INSERT)
    void insertSysMenu(SysMenu sysMenu);

    /**
     * 修改
     * @param sysMenu
     */
    @AutoFill(OperationType.UPDATE)
    void updateSysMenu(SysMenu sysMenu);
}
