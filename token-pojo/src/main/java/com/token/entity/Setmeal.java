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
@TableName(value ="setmeal")
@ApiModel(value = "Setmeal" , description="套餐")
public class Setmeal  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id")
    @ApiModelProperty(value="主键")
    private Long id;

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

    @TableField(value="create_time")
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    @TableField(value="update_time")
    @ApiModelProperty(value="更新时间")
    private LocalDateTime updateTime;

    @TableField(value="create_user")
    @ApiModelProperty(value="创建人")
    private Integer createUser;

    @TableField(value="update_user")
    @ApiModelProperty(value="修改人")
    private Integer updateUser;

}
