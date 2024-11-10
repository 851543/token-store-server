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
     * 回显套餐详情
     * @param id
     * @return
     */
    SetmealDTO getByIdSetmea(Long id);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void edit(SetmealDTO setmealDTO);
}
