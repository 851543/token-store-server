package com.token.mapper;

import com.github.pagehelper.Page;
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
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void delete(Integer id);

    //  TODO 这里也需要修改 使用Mapper可以达到复用
    /**
     * 修改分类信息
     *
     * @param category
     */
    @Update("update category set type=#{type},name=#{name},status=#{status},update_time=#{updateTime},update_user=#{updateUser} where id = #{id}")
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

    /**
     * 修改分类状态数据
     * @param id
     * @param status
     */
    @AutoFill(OperationType.UPDATE)
    @Update("update category set status=#{status} where id=#{id}")
    void status(Long id, Long status);
}
