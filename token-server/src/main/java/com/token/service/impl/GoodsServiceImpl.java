package com.token.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.token.constant.DefaultPriceConstant;
import com.token.constant.MessageConstant;
import com.token.constant.StatusConstant;
import com.token.dto.GoodsDTO;
import com.token.dto.GoodsPageQueryDTO;
import com.token.entity.Goods;
import com.token.entity.GoodsSpecs;
import com.token.exception.AccountIsDisableException;
import com.token.exception.CategoryIdNotEmptyException;
import com.token.exception.GoodsNameIsExistException;
import com.token.exception.GoodsNameNotEmptyException;
import com.token.mapper.GoodsMapper;
import com.token.mapper.GoodsSpecsMapper;
import com.token.result.PageResult;
import com.token.service.GoodsService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsSpecsMapper goodsSpecsMapper;

    /**
     * 新增商品
     *
     * @param goodsDTO
     * @return
     */
    @Transactional  //  开启事务
    public void insert(GoodsDTO goodsDTO) {
        //  参数合法校验
        if (StringUtils.isEmpty(goodsDTO.getName())) {
            throw new GoodsNameNotEmptyException(MessageConstant.GOODS_NAME_NOT_EMPTY);
        }
        if (goodsDTO.getCategoryId() == null) {
            throw new CategoryIdNotEmptyException(MessageConstant.CATEGORY_ID_NOT_EMPTY);
        }
        //  商品名称唯一性
        if (findGoodsByName(goodsDTO.getName()) != null) {
            throw new GoodsNameIsExistException(MessageConstant.GOODS_NAME_IS_EXIST);
        }

        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsDTO, goods);

        //  默认数值
        goods.setPrice(DefaultPriceConstant.GoodsPrice);
        goods.setStatus(StatusConstant.DISABLE);

        //  新增商品数据并返回新增id
        goodsMapper.insert(goods);

        //  新增一批规模数据
        insertBatch(goods.getId(), goodsDTO.getGoodsSpecsList());
    }

    /**
     * 修改商品
     *
     * @param id
     * @param goodsDTO
     * @return
     */
    @Transactional
    public void update(Long id, GoodsDTO goodsDTO) {
        //  商品名称唯一性
        if (findGoodsByName(goodsDTO.getName()) != null) {
            throw new GoodsNameIsExistException(MessageConstant.GOODS_NAME_IS_EXIST);
        }

        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsDTO, goods);

        goodsMapper.update(goods, id);
        //  覆盖的方式处理商品规模
        Long[] ids = new Long[1];
        ids[0] = id;
        goodsSpecsMapper.deleteByGoodsIds(ids);
        //  新增一批规模数据
        insertBatch(id, goodsDTO.getGoodsSpecsList());
    }

    /**
     * 删除商品
     * @param ids
     */
    @Transactional
    public void delete(Long[] ids) {
        List<Long> status = goodsMapper.getEnableStatusByIds(ids);
        if ((!ObjectUtils.isEmpty(status)) && status.size() > 0) {
            throw new AccountIsDisableException(MessageConstant.GOODS_STATUS_IS_ENABLE);
        }
        goodsMapper.delete(ids);
        goodsSpecsMapper.deleteByGoodsIds(ids);
    }

    /**
     * 根据id查询商品信息
     *
     * @param id
     * @return
     */
    public Goods getGoodsInfo(Long id) {
        return goodsMapper.getGoodsById(id);
    }

    public PageResult page(GoodsPageQueryDTO goodsPageQueryDTO) {
        PageHelper.startPage(goodsPageQueryDTO.getPageNow(), goodsPageQueryDTO.getPageSize());
        Page<Goods> page = (Page<Goods>) goodsMapper.queryList(goodsPageQueryDTO);
        return PageResult.builder().
                total(page.getTotal()).
                records(page.getResult()).
                build();
    }

    /**
     *修改商品状态
     * @param id
     * @param status
     */
    public void status(Long id, Long status) {
        goodsMapper.update(Goods.builder().status(status.intValue()).build(),id);
    }


    /**
     * 根据商品名称查询商品
     *
     * @param name
     * @return
     */
    public Goods findGoodsByName(String name) {
        return goodsMapper.getGoodsByName(name);
    }

    /**
     * 批量新增商品规模
     *
     * @param id
     * @param goodsSpecsList
     */
    public void insertBatch(Long id, List<GoodsSpecs> goodsSpecsList) {
        if (goodsSpecsList != null && goodsSpecsList.size() > 0) {
            goodsSpecsList.forEach((item) -> {
                item.setGoodsId(id.intValue());
            });
            goodsSpecsMapper.insertBatch(goodsSpecsList);
        }
    }
}
