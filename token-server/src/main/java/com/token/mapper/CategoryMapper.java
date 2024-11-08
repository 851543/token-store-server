package com.token.mapper;

import com.token.annotation.AutoFill;
import com.token.dto.CategoryPageQueryDTO;
import com.token.entity.Category;
import com.token.enumeration.OperationType;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 新增分类数据
     * @param category
     */
    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user) " +
            "values(#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{createUser})")
    @AutoFill(OperationType.INSERT)
    void insert(Category category);


    /**
     * 根据id删除分类
     *
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 修改分类信息
     *
     * @param category
     */
    @AutoFill(OperationType.UPDATE)
    void update(Category category);

    /**
     * 根据id查询分类数据
     * @param id
     * @return
     */
    @Select("select * from category where id = #{id}")
    Category getCategoryById(Long id);

    /**
     * 查询分类数据集合
     * @param categoryPageQueryDTO
     * @return
     */
    List<Category> queryList(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据分类名称-查询分类数据
     * @param name
     * @return
     */
    @Select("select * from category where name = #{name}")
    Category getCategoryByName(String name);
}
