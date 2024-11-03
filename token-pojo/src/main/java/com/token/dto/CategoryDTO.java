package com.token.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.token.annotation.Type;
import com.token.constant.MessageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
@Data
@ApiModel(description = "新增分类的数据模型")
public class CategoryDTO {

    @TableId(value = "type")
    @ApiModelProperty(value = "类型")
    @Type(message = MessageConstant.CATEGORY_TYPE_ERROR)
    private Integer type;

    @TableId(value = "name")
    @ApiModelProperty(value = "分类名称")
    @NotEmpty(message = MessageConstant.CATEGORY_NAME_NOT_EMPTY)
    private String name;

    @TableId(value = "sort")
    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @TableId(value = "status")
    @ApiModelProperty(value = "商品状态")
    private Integer status;
}
