package com.token.entity;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName(value ="setmeal_goods")
@ApiModel(value = "SetmealGoods" , description="套餐商品关系")
public class SetmealGoods  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id")
    @ApiModelProperty(value="主键")
    private Long id;

    @TableField(value="setmeal_id")
    @ApiModelProperty(value="套餐id")
    private Integer setmealId;

    @TableField(value="goods_id")
    @ApiModelProperty(value="商品id")
    private Integer goodsId;

    @TableField(value="name")
    @ApiModelProperty(value="商品名称 （冗余字段）")
    private String name;

    @TableField(value="price")
    @ApiModelProperty(value="商品单价（冗余字段）")
    private Double price;

    @TableField(value="copies")
    @ApiModelProperty(value="商品份数")
    private Integer copies;

}
