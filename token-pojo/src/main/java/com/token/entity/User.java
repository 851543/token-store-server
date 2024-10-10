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
@TableName(value ="user")
@ApiModel(value = "User" , description="用户信息")
public class User  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id")
    @ApiModelProperty(value="主键")
    private Long id;

    @TableField(value="openid")
    @ApiModelProperty(value="微信用户唯一标识")
    private String openid;

    @TableField(value="name")
    @ApiModelProperty(value="姓名")
    private String name;

    @TableField(value="phone")
    @ApiModelProperty(value="手机号")
    private String phone;

    @TableField(value="sex")
    @ApiModelProperty(value="性别")
    private String sex;

    @TableField(value="id_number")
    @ApiModelProperty(value="身份证号")
    private String idNumber;

    @TableField(value="avatar")
    @ApiModelProperty(value="头像")
    private String avatar;

    @TableField(value="create_time")
    @ApiModelProperty(value="null")
    private LocalDateTime createTime;

}
