package com.token.mapper;

import com.token.annotation.AutoFill;
import com.token.dto.CategoryPageQueryDTO;
import com.token.dto.GoodsDTO;
import com.token.entity.Category;
import com.token.entity.Goods;
import com.token.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper {

    /**
     * 根据商品名称查询商品数据
     * @param name
     * @return
     */
    @Select("select * from goods where name = #{name}")
    Goods getGoodsByName(String name);

    /**
     * 新增商品数据
     * @param goods
     */
    @Insert("insert into goods(name, category_id, price, image, description, status, create_time, update_time, create_user, update_user) values " +
            "(#{name},#{categoryId},#{price},#{image},#{description},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Goods goods);

    /**
     * 根据id修改商品数据
     * @param id
     * @param goods
     */
    @AutoFill(OperationType.UPDATE)
    void update(@Param("goods") Goods goods,@Param("id") Long id);


}
