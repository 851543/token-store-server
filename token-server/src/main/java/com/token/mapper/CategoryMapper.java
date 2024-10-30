package com.token.mapper;

import com.token.annotation.AutoFill;
import com.token.entity.Category;
import com.token.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 修改分类信息
     *
     * @param category
     */
    @Update("update category set type=#{type},name=#{name},status=#{status},update_time=#{updateTime},update_user=#{updateUser} where id = #{id}")
    @AutoFill(OperationType.UPDATE)
    void update(Category category);
}
