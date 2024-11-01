package com.token.service;

import com.token.dto.CategoryDTO;

public interface CategoryService {

    /**
     * 新增分类
     *
     * @return
     */
    void insert(CategoryDTO categoryDTO);

    /**
     * 根据id删除分类
     *
     * @param id
     */
    void delete(Integer id);


    /**
     * 修改分类信息
     *
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);
}
