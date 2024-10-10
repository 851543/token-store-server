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
@TableName(value ="goods_specs")
@ApiModel(value = "GoodsSpecs" , description="商品规格关系表")
public class GoodsSpecs  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id")
    @ApiModelProperty(value="主键")
    private Long id;

    @TableField(value="goods_id")
    @ApiModelProperty(value="商品id")
    private Integer goodsId;

    @TableField(value="name")
    @ApiModelProperty(value="规格名称")
    private String name;

    @TableField(value="value")
    @ApiModelProperty(value="规格数据list")
    private String value;

}
