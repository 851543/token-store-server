package com.token.controller.admin;

import com.token.constant.MessageConstant;
import com.token.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类")
@Slf4j
public class Category {

    /**
     * 新增分类
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增分类")
    public Result<String> insert(){
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }
}
