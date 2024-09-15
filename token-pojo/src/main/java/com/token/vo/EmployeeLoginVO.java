package com.token.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 851543
 * @description
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="员工登陆的响应数据")
public class EmployeeLoginVO  implements Serializable {

    @ApiModelProperty(value="主键")
    private Long id;

    @ApiModelProperty(value="姓名")
    private String name;

    @ApiModelProperty(value="用户名")
    private String username;

    @ApiModelProperty(value="jwt令牌")
    private String token;

}
