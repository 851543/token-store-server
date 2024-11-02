package com.token.service;

import com.token.dto.GoodsDTO;

public interface GoodsService {

    /**
     * 新增商品
     * @param goodsDTO
     * @return
     */
    void insert(GoodsDTO goodsDTO);
}
