package com.token.service;

import com.token.dto.SetmealDTO;
import com.token.dto.SetmealQueryDTO;
import com.token.result.PageResult;

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
    SetmealDTO getByIdSetmea(Long id);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void edit(SetmealDTO setmealDTO);

    void delete(Long[] ids);

    void status(Long id, Long status);


    PageResult page(SetmealQueryDTO setmealQueryDTO);
}
