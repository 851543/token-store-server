package com.token.controller.admin;

import com.token.constant.MessageConstant;
import com.token.dto.GoodsDTO;
import com.token.entity.Goods;
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
     *
     * @param goodsDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增商品")
    public Result<String> insert(@RequestBody GoodsDTO goodsDTO) {
        log.info("新增商品:{}", goodsDTO);
        goodsService.insert(goodsDTO);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 修改商品
     *
     * @param id
     * @param goodsDTO
     * @return
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "修改商品")
    public Result<String> update(@PathVariable Long id, @RequestBody GoodsDTO goodsDTO) {
        log.info("修改商品:{}", goodsDTO);
        goodsService.update(id, goodsDTO);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * @param id
     * @param status
     * @return
     */
    @PostMapping ("/status/{status}")
    @ApiOperation(value = "修改商品状态")
    public Result<String> status(@PathVariable Long status, Long id) {
        log.info("商品{}修改了状态{}", id, status);
        goodsService.status(id, status);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }
}
