package com.token.service;

import com.token.dto.SetmealDTO;
import com.token.entity.Setmeal;

public interface SetmealService {

    /**
     * 新增套餐
     * @param setmealDTO
     */
    void add(SetmealDTO setmealDTO);

    /**
     * 获取套餐详情
     * @param id
     * @return
     */
    Setmeal getByIdSetmea(Long id);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void edit(SetmealDTO setmealDTO);

    void delete(Long[] ids);
}
