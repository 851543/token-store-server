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
@TableName(value ="orders")
@ApiModel(value = "Orders" , description="订单表")
public class Orders  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id")
    @ApiModelProperty(value="主键")
    private Long id;

    @TableField(value="number")
    @ApiModelProperty(value="订单号")
    private String number;

    @TableField(value="select_status")
    @ApiModelProperty(value="订单状态 1店内 2打包 3配送")
    private Integer selectStatus;

    @TableField(value="status")
    @ApiModelProperty(value="订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款")
    private Integer status;

    @TableField(value="user_id")
    @ApiModelProperty(value="下单用户id")
    private Integer userId;

    @TableField(value="address_book_id")
    @ApiModelProperty(value="地址id")
    private Integer addressBookId;

    @TableField(value="order_time")
    @ApiModelProperty(value="下单时间")
    private LocalDateTime orderTime;

    @TableField(value="checkout_time")
    @ApiModelProperty(value="结账时间")
    private LocalDateTime checkoutTime;

    @TableField(value="pay_method")
    @ApiModelProperty(value="支付方式 1微信,2支付宝")
    private Integer payMethod;

    @TableField(value="pay_status")
    @ApiModelProperty(value="支付状态 0未支付 1已支付 2退款")
    private Integer payStatus;

    @TableField(value="amount")
    @ApiModelProperty(value="实收金额")
    private Double amount;

    @TableField(value="remark")
    @ApiModelProperty(value="备注")
    private String remark;

    @TableField(value="phone")
    @ApiModelProperty(value="手机号")
    private String phone;

    @TableField(value="address")
    @ApiModelProperty(value="地址")
    private String address;

    @TableField(value="user_name")
    @ApiModelProperty(value="用户名称")
    private String userName;

    @TableField(value="consignee")
    @ApiModelProperty(value="收货人")
    private String consignee;

    @TableField(value="cancel_reason")
    @ApiModelProperty(value="订单取消原因")
    private String cancelReason;

    @TableField(value="rejection_reason")
    @ApiModelProperty(value="订单拒绝原因")
    private String rejectionReason;

    @TableField(value="cancel_time")
    @ApiModelProperty(value="订单取消时间")
    private LocalDateTime cancelTime;

    @TableField(value="estimated_delivery_time")
    @ApiModelProperty(value="预计送达时间")
    private LocalDateTime estimatedDeliveryTime;

    @TableField(value="delivery_status")
    @ApiModelProperty(value="配送状态  1立即送出  0选择具体时间")
    private Integer deliveryStatus;

    @TableField(value="delivery_time")
    @ApiModelProperty(value="送达时间")
    private LocalDateTime deliveryTime;

    @TableField(value="pack_amount")
    @ApiModelProperty(value="打包费")
    private Integer packAmount;

    @TableField(value="tableware_number")
    @ApiModelProperty(value="餐具数量")
    private Integer tablewareNumber;

    @TableField(value="tableware_status")
    @ApiModelProperty(value="餐具数量状态  1按餐量提供  0选择具体数量")
    private Integer tablewareStatus;

}
