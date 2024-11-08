package com.token.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.token.entity.GoodsSpecs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "商品的数据模型")
public class GoodsDTO {

    @TableField(value="name")
    @ApiModelProperty(value="商品名称")
    private String name;

    @TableField(value="category_id")
    @ApiModelProperty(value="商品分类id")
    private Integer categoryId;

    @TableField(value="price")
    @ApiModelProperty(value="商品价格")
    private Double price;

    @TableField(value="image")
    @ApiModelProperty(value="图片")
    private String image;

    @TableField(value="description")
    @ApiModelProperty(value="描述信息")
    private String description;

    @TableField(value="status")
    @ApiModelProperty(value="0 停售 1 起售")
    private Integer status;

    @ApiModelProperty(value = "商品规模列表")
    private List<GoodsSpecs> goodsSpecsList;
}
