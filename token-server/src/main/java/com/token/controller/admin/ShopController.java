package com.token.controller.admin;

import com.token.constant.MessageConstant;
import com.token.constant.RedisKeyConstant;
import com.token.constant.StatusConstant;
import com.token.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shop")
@Api(tags = "店铺")
@Slf4j
public class ShopController {
    // TODO 使用redis 设置店铺状态和获取店铺状态
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/{status}")
    @ApiOperation(value = "设置店铺状态")
    public Result<String> setShopStatus(@PathVariable Long status) {
        log.info("设置了店铺状态：{}",status);
        redisTemplate.opsForValue().set(RedisKeyConstant.TOKEN_ADMIN_SHOP_STATUS_, status);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    @GetMapping()
    @ApiOperation(value = "获取店铺状态")
    public Result<Long> getShopStatus() {
        Long status = (Long) redisTemplate.opsForValue().get(RedisKeyConstant.TOKEN_ADMIN_SHOP_STATUS_);
        log.info("当前店铺状态：{}",status == 1 ? "营业中":"打烊了");
        return Result.success(status);
    }
}
