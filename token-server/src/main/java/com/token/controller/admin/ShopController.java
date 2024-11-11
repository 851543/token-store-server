package com.token.controller.admin;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/shop")
@Api(tags = "店铺")
@Slf4j
public class ShopController {
    // TODO 使用redis 设置店铺状态和获取店铺状态
}
