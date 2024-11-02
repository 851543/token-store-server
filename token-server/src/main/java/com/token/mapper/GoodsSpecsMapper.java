package com.token.mapper;

import com.token.entity.Goods;
import com.token.entity.GoodsSpecs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsSpecsMapper {

    /**
     * 新增一批规模数据
     * @param goodsSpecsList
     */
    void insertBatch(List<GoodsSpecs> goodsSpecsList);
}
