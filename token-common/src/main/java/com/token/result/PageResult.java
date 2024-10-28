package com.token.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PageResult", description = "分页查询结果")
public class PageResult implements Serializable {

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private long total;

    /**
     * 当前页数据集合
     */
    @ApiModelProperty(value = "当前页数据集合")
    private List records;
}
