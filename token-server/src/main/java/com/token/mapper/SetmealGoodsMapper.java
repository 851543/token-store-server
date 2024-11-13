package com.token.mapper;

import com.token.entity.SetmealGoods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealGoodsMapper {
    /**
     * 新增一批套餐商品关联数据
     * @param setmealGoodsList
     */
    void insertBatch(List<SetmealGoods> setmealGoodsList);

    /**
     *  根据id删除套餐商品关联数据
     * @param id
     */
    @Delete("delete from setmeal_goods where setmeal_id = #{id}")
    void deleteSetmealById(Long id);
}
