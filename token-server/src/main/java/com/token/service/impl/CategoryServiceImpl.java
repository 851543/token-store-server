package com.token.service.impl;

import com.token.constant.StatusConstant;
import com.token.dto.CategoryDTO;
import com.token.entity.Category;
import com.token.mapper.CategoryMapper;
import com.token.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 新增分类
     * @return
     */
    public void insert(CategoryDTO categoryDTO) {
        Category category = Category.builder().build();
        //  拷贝属性
        BeanUtils.copyProperties(categoryDTO,category);
        category.setStatus(StatusConstant.DISABLE);
        categoryMapper.insert(category);
    }

    /**
     * 根据id删除分类
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }

    /**
     * 修改分类信息
     *
     * @param categoryDTO
     */
    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = Category.builder().build();
        //  拷贝属性
        BeanUtils.copyProperties(categoryDTO,category);
        categoryMapper.update(category);
    }
}
