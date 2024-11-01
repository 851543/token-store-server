package com.token.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "商品")
public class GoodsDTO {

    private static final long serialVersionUID = 1L;

    @TableId(value="id")
    @ApiModelProperty(value="主键")
    private Long id;

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
