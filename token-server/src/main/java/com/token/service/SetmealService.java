package com.token.service;

import com.token.dto.SetmealDTO;
import com.token.dto.SetmealQueryDTO;
import com.token.entity.Setmeal;
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
    Setmeal getByIdSetmea(Long id);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void edit(SetmealDTO setmealDTO);

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 修改套餐状态
     * @param status
     * @param id
     * @return
     */
    void status(Long id, Long status);

    /**
     * 套餐分页查询
     * @param setmealQueryDTO
     * @return
     */
    PageResult page(SetmealQueryDTO setmealQueryDTO);
}
