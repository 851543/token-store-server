package com.token.controller.admin;

import com.token.constant.MessageConstant;
import com.token.dto.SetmealDTO;
import com.token.result.Result;
import com.token.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/add")
    @ApiOperation(value = "新增套餐")
    public Result<String> add(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐:{}",setmealDTO);
        setmealService.add(setmealDTO);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }


    /**
     * 删除套餐
     */
    @DeleteMapping("/del")
    @ApiOperation(value = "删除套餐")
    public Result<String> del( Long[] ids){
        log.info("删除了套餐{}",ids);
        setmealService.delete(ids);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

}
