package com.token.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商品分页查询时传递的数据模型")

public class GoodsPageQueryDTO {
    @ApiModelProperty(value = "当前页")
    private Integer pageNow;

    @ApiModelProperty(value = "当前页数")
    private Integer pageSize;

    @ApiModelProperty(value = "商品名称")
    private String name;
}
