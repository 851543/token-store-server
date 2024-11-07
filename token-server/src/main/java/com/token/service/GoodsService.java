package com.token.service;

import com.token.dto.GoodsDTO;
import com.token.entity.Goods;

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
     *
     * @param id
     * @param status
     */
    void status(Long id, Long status);
}
