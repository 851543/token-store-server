package com.token.controller.admin;

import com.token.constant.MessageConstant;
import com.token.dto.SetmealDTO;
import com.token.dto.SetmealQueryDTO;
import com.token.result.PageResult;
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
    public Result<SetmealDTO> get(Long id){
        SetmealDTO setmealDTO = setmealService.getByIdSetmea(id);
        return Result.success(setmealDTO);
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

    /**
     * 更改套餐状态
     */
    @PreAuthorize("@ss.hasPermi('admin:setmeal:update')")
    @ApiOperation(value = "修改套餐状态")
    @PostMapping("/status/{status}")
    public  Result<String> Status(@PathVariable Long status,Long id){
        log.info("修改商品{}状态{}",id,status);
        setmealService.status(id,status);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     分页查询
     * @param
     *
     * @return
     */

    @PreAuthorize("@ss.hasPermi('admin:setmeal:select')")
    @PostMapping("/page")
    @ApiOperation(value = "套餐分页查询")
    public  Result<PageResult> page(SetmealQueryDTO setmealQueryDTO){
        log.info("分页查询：{}",setmealQueryDTO);
        return Result.success(setmealService.page(setmealQueryDTO));

    }




}
