package com.token.service;

import com.token.dto.GoodsDTO;
import com.token.dto.GoodsPageQueryDTO;
import com.token.entity.Goods;
import com.token.result.PageResult;

public interface GoodsService {

    /**
     * 新增商品
     * @param goodsDTO
     * @return
     */
    void insert(GoodsDTO goodsDTO);

    /**
     * 修改商品
     * @param id
     * @param goodsDTO
     * @return
     */
    void update(Long id, GoodsDTO goodsDTO);

    /**
     * 根据id批量删除
     * @param ids
     */
    void delete(Long[] ids);

    void getGoodsInfo(Long id);

    /**
     * 分页查询
     * @param goodsPageQueryDTO
     * @return
     */
    PageResult page(GoodsPageQueryDTO goodsPageQueryDTO);

    /**
     * 修改商品状态
     * @param id
     * @param status
     */
    void status(Long id, Long status);
}
