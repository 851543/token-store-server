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
@TableName(value ="order_detail")
@ApiModel(value = "OrderDetail" , description="订单明细表")
public class OrderDetail  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id")
    @ApiModelProperty(value="主键")
    private Long id;

    @TableField(value="name")
    @ApiModelProperty(value="名字")
    private String name;

    @TableField(value="image")
    @ApiModelProperty(value="图片")
    private String image;

    @TableField(value="order_id")
    @ApiModelProperty(value="订单id")
    private Integer orderId;

    @TableField(value="dish_id")
    @ApiModelProperty(value="商品id")
    private Integer dishId;

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

}
