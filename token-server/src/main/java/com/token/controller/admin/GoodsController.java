package com.token.controller.admin;

import com.alibaba.druid.support.logging.Log;
import com.token.constant.MessageConstant;
import com.token.dto.GoodsDTO;
import com.token.dto.GoodsPageQueryDTO;
import com.token.entity.Goods;
import com.token.result.PageResult;
import com.token.result.Result;
import com.token.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/goods")
@Api(tags = "商品")
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 新增商品
     * @param goodsDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增商品")
    public Result<String> insert(@RequestBody GoodsDTO goodsDTO){
        log.info("新增商品:{}",goodsDTO);
        goodsService.insert(goodsDTO);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 修改商品
     * @param id
     * @param goodsDTO
     * @return
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "修改商品")
    public Result<String> update(@PathVariable Long id, @RequestBody GoodsDTO goodsDTO){
        log.info("修改商品:{}",goodsDTO);
        goodsService.update(id,goodsDTO);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 删除商品
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "删除商品")
    public Result<String> delete(@RequestParam Long[] ids){
        log.info("删除了商品:{}",ids);
        goodsService.delete(ids);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查询商品信息")
    public Result<Goods> selectById(@PathVariable Long id){
        log.info("查询了商品：{}",id);
        goodsService.getGoodsInfo(id);
        return Result.success(goodsService.getGoodsInfo(id));
    }

    /**
     * 分页查询
     * @param goodsPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "商品分页查询")
    public Result<PageResult> page(GoodsPageQueryDTO goodsPageQueryDTO){
        log.info("分页查询：{}",goodsPageQueryDTO);
        return Result.success(goodsService.page(goodsPageQueryDTO));
    }

    /**
     * 修改商品状态
     * @param status
     * @param id
     * @return
     */
    @ApiOperation(value = "修改商品状态")
    @PostMapping("/status/{status}")
    public Result<String> status(@PathVariable Long status, Long id){
        log.info("修改了商品{}状态：{}",id,status);
        goodsService.status(id,status);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }
}
