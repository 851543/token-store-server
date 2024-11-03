package com.token.controller.admin;

import com.token.constant.MessageConstant;
import com.token.dto.GoodsDTO;
import com.token.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/goods")
@Api(tags = "商品")
@Slf4j
public class GoodsController {

    /**
     * 新增商品
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增商品")
    public Result<String> insert(){
        return Result.success(MessageConstant.OPERATE_SUCCESS);

    }
}
