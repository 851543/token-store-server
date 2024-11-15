package com.token.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
@Data
@ApiModel
public class SetmealQueryDTO {
    @ApiModelProperty(value = "当前页")
    private String pageNow;

    @ApiModelProperty(value = "当前页数")
    private Integer pageSize;

    @ApiModelProperty(value = "商品名称")
    private String name;
}
