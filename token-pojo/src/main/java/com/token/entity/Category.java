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
@TableName(value ="category")
@ApiModel(value = "Category" , description="商品及套餐分类")
public class Category  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id")
    @ApiModelProperty(value="主键")
    private Long id;

    @TableField(value="type")
    @ApiModelProperty(value="类型   1 商品分类 2 套餐分类")
    private Integer type;

    @TableField(value="name")
    @ApiModelProperty(value="分类名称")
    private String name;

    @TableField(value="sort")
    @ApiModelProperty(value="顺序")
    private Integer sort;

    @TableField(value="status")
    @ApiModelProperty(value="分类状态 0:禁用，1:启用")
    private Integer status;

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
