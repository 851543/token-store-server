package com.token.service.impl;

import com.token.constant.DefaultPriceConstant;
import com.token.constant.MessageConstant;
import com.token.constant.StatusConstant;
import com.token.dto.GoodsDTO;
import com.token.entity.Goods;
import com.token.entity.GoodsSpecs;
import com.token.exception.CategoryIdNotEmptyException;
import com.token.exception.GoodsNameIsExistException;
import com.token.exception.GoodsNameNotEmptyException;
import com.token.mapper.GoodsMapper;
import com.token.mapper.GoodsSpecsMapper;
import com.token.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsSpecsMapper goodsSpecsMapper;

    /**
     * 新增商品
     * @param goodsDTO
     * @return
     */
    @Transactional  //  开启事务
    public void insert(GoodsDTO goodsDTO) {
        //  参数合法校验
        if (StringUtils.isEmpty(goodsDTO.getName())){
            throw new GoodsNameNotEmptyException(MessageConstant.GOODS_NAME_NOT_EMPTY);
        }
        if (goodsDTO.getCategoryId() == null){
            throw new CategoryIdNotEmptyException(MessageConstant.CATEGORY_ID_NOT_EMPTY);
        }
        //  商品名称唯一性
        if (findGoodsByName(goodsDTO.getName()) != null){
            throw new GoodsNameIsExistException(MessageConstant.GOODS_NAME_IS_EXIST);
        }

        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsDTO,goods);

        //  默认数值
        goods.setPrice(DefaultPriceConstant.GoodsPrice);
        goods.setStatus(StatusConstant.DISABLE);

        //  新增商品数据并返回新增id
        goodsMapper.insert(goods);

        //  新增一批规模数据
        List<GoodsSpecs> goodsSpecsList = goodsDTO.getGoodsSpecsList();
        if (goodsSpecsList != null && goodsSpecsList.size() > 0){
            goodsSpecsList.forEach((item)->{
                item.setGoodsId(goods.getId().intValue());
            });
            goodsSpecsMapper.insertBatch(goodsSpecsList);
        }
    }

    /**
     * 根据商品名称查询商品
     * @param name
     * @return
     */
    public Goods findGoodsByName(String name){
        return goodsMapper.getGoodsByName(name);
    }
}
