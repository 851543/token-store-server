package com.token.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.token.constant.DefaultPriceConstant;
import com.token.constant.MessageConstant;
import com.token.constant.StatusConstant;
import com.token.dto.SetmealDTO;
import com.token.dto.SetmealQueryDTO;
import com.token.entity.*;
import com.token.exception.*;
import com.token.mapper.*;
import com.token.result.PageResult;
import com.token.service.SetmealService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealGoodsMapper setmealGoodsMapper;

    /**
     * 新增套餐
     *
     * Long 转 Integer
     * 使用 intValue() 方法：简单但没有范围检查。
     * 使用 Math.toIntExact()：安全，能捕捉超出范围的情况。
     *
     * Integer 转 Long
     * 使用 Long.valueOf() 或 longValue() 方法：简单且有效。
     *
     * @param setmealDTO
     */
    @Transactional
    public void add(SetmealDTO setmealDTO) {
        Category category = categoryMapper.getCategoryById(Long.valueOf(setmealDTO.getCategoryId()));
        if (ObjectUtils.isEmpty(category)){
            throw new CategoryNotExistException(MessageConstant.CATEGORY_NOT_EXIST);
        }
        if (StringUtils.isEmpty(setmealDTO.getName())){
            throw new SetmealNameNotEmptyException(MessageConstant.SETMEAL_NAME_NOT_EMPTY);
        }
        if (setmealDTO.getPrice() == null) setmealDTO.setPrice(DefaultPriceConstant.GoodsPrice);
        setmealDTO.setStatus(StatusConstant.DISABLE);
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmealMapper.insert(setmeal);
        // 关联商品表
        insertBatch(setmeal.getId(),setmealDTO.getSetmealGoodsList());
    }

    /**
     * 修改套餐
     * @param setmealDTO
     */
    @Transactional
    public void edit(SetmealDTO setmealDTO) {
        Category category = categoryMapper.getCategoryById(Long.valueOf(setmealDTO.getCategoryId()));
        if (ObjectUtils.isEmpty(category)){
            throw new CategoryNotExistException(MessageConstant.CATEGORY_NOT_EXIST);
        }
        if (StringUtils.isEmpty(setmealDTO.getName())){
            throw new SetmealNameNotEmptyException(MessageConstant.SETMEAL_NAME_NOT_EMPTY);
        }
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);

        setmealMapper.update(setmeal);

        Long[] ids = new Long[1];
        ids[0] = setmeal.getId();
        setmealGoodsMapper.deleteByGoodsIds(ids);

        insertBatch(setmeal.getId(),setmealDTO.getSetmealGoodsList());
    }

    /**
     * 回显套餐详情
     * @param id
     * @return
     */
    @Override
    public Setmeal getByIdSetmea(Long id) {

        return setmealMapper.getByIdSetmea(id);
    }

    public void insertBatch(Long id,List<SetmealGoods> setmealGoodsList) {
        // 关联商品表
        if (ObjectUtils.isNotEmpty(setmealGoodsList) && setmealGoodsList.size() > 0){
            setmealGoodsList.forEach((item)->{
                item.setSetmealId(Math.toIntExact(id));
            });
            setmealGoodsMapper.insertBatch(setmealGoodsList);
        }
    }

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @Transactional
    public void delete(Long[] ids) {
        List<Long> status = setmealMapper.getEnableStatusByIds(ids);
        if ((!ObjectUtils.isEmpty(status)) && status.size() > 0){
            throw new AccountIsDisableException(MessageConstant.SETMEAL_STATUS_IS_ENABLE);
        }
        setmealMapper.delete(ids);
        //根据商品id删除
        setmealGoodsMapper.deleteByGoodsIds(ids);
    }

    @Override
    public void status(Long id, Long status) {
        Setmeal setmeal = Setmeal.builder().id(id).status(status.intValue()).build();
        setmealMapper.update(setmeal);

    }

    @Override
    public PageResult page(SetmealQueryDTO setmealQueryDTO) {
        PageHelper.startPage(Integer.parseInt(setmealQueryDTO.getPageNow()),setmealQueryDTO.getPageSize());
        Page<Setmeal> page=(Page<Setmeal>)  setmealMapper.queryList(setmealQueryDTO);
        return PageResult.builder().
                total(page.getTotal()).
                records(page.getResult()).build();
    }


}
