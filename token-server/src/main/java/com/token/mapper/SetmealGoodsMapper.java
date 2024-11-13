package com.token.mapper;

import com.token.entity.SetmealGoods;
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
     * 根据商品id删除关联数据
     * @param ids
     */
    void deleteByGoodsIds(Long[] ids);

    /**
     *  根据id删除套餐商品关联数据
     * @param id
     */
}
