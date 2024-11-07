package com.token.mapper;

import com.token.dto.GoodsDTO;
import com.token.entity.Employee;
import com.token.entity.Goods;
import com.token.entity.GoodsSpecs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsSpecsMapper {

    /**
     * 新增一批商品规模数据
     * @param goodsSpecsList
     */
    void insertBatch(List<GoodsSpecs> goodsSpecsList);

    /**
     * 根据商品ID删除一批商品规模数据
     * @param goodsSpecsList
     */
    void deleteBatchByGoodsId(List<GoodsSpecs> goodsSpecsList);

    /**
     *
     * @param status
     */
    void upstatus(Employee.EmployeeBuilder status);
}
