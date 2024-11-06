package com.token.mapper;

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
}
