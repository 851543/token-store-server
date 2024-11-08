package com.token.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.token.entity.GoodsSpecs;
import com.token.entity.SetmealGoods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(description = "套餐的数据模型")
public class SetmealDTO {

    @TableField(value="category_id")
    @ApiModelProperty(value="商品分类id")
    private Integer categoryId;

    @TableField(value="name")
    @ApiModelProperty(value="套餐名称")
    private String name;

    @TableField(value="price")
    @ApiModelProperty(value="套餐价格")
    private Double price;

    @TableField(value="status")
    @ApiModelProperty(value="售卖状态 0:停售 1:起售")
    private Integer status;

    @TableField(value="description")
    @ApiModelProperty(value="描述信息")
    private String description;

    @TableField(value="image")
    @ApiModelProperty(value="图片")
    private String image;

    @ApiModelProperty(value = "套餐商品关联列表")
    private List<SetmealGoods> setmealGoodsList;
}
