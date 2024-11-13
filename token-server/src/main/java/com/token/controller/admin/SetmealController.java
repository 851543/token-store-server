package com.token.controller.admin;

import com.token.constant.MessageConstant;
import com.token.dto.SetmealDTO;
import com.token.result.Result;
import com.token.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "套餐")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;


    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    @PreAuthorize("@ss.hasPermi('admin:setmeal:insert')")
    @PostMapping("/add")
    @ApiOperation(value = "新增套餐")
    public Result<String> add(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐:{}",setmealDTO);
        setmealService.add(setmealDTO);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 修改套餐
     * @param setmealDTO
     * @return
     */
    @PreAuthorize("@ss.hasPermi('admin:setmeal:update')")
    @PutMapping("/edit")
    @ApiOperation(value = "修改套餐")
    public Result<String> edit(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐:{}",setmealDTO);
        setmealService.edit(setmealDTO);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 获取套餐详情
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('admin:setmeal:select')")
    @GetMapping
    @ApiOperation(value = "获取套餐详情")
    public Result<Setmeal> get(Long id){
        Setmeal setmeal = setmealService.getByIdSetmea(id);
        return Result.success(setmeal);
    }


    /**
     * 删除套餐
     */
    @PreAuthorize("@ss.hasPermi('admin:setmeal:delete')")
    @DeleteMapping("/del")
    @ApiOperation(value = "删除套餐")
    public Result<String> del(Long[] ids){
        log.info("删除了套餐{}",ids);
        setmealService.delete(ids);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

}
