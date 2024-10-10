package com.token.entity;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/** 
 * @author 851543
 * @description  
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value ="shopping_cart")
@ApiModel(value = "ShoppingCart" , description="购物车")
public class ShoppingCart  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id")
    @ApiModelProperty(value="主键")
    private Long id;

    @TableField(value="name")
    @ApiModelProperty(value="商品名称")
    private String name;

    @TableField(value="image")
    @ApiModelProperty(value="图片")
    private String image;

    @TableField(value="user_id")
    @ApiModelProperty(value="主键")
    private Integer userId;

    @TableField(value="goods_id")
    @ApiModelProperty(value="商品id")
    private Integer goodsId;

    @TableField(value="setmeal_id")
    @ApiModelProperty(value="套餐id")
    private Integer setmealId;

    @TableField(value="goods_specs")
    @ApiModelProperty(value="规格")
    private String goodsSpecs;

    @TableField(value="number")
    @ApiModelProperty(value="数量")
    private Integer number;

    @TableField(value="amount")
    @ApiModelProperty(value="金额")
    private Double amount;

    @TableField(value="create_time")
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

}
