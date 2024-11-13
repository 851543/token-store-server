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
@TableName(value ="sys_role")
@ApiModel(value = "SysRole" , description="角色表")
public class SysRole  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id")
    @ApiModelProperty(value="主键")
    private Long id;

    @TableField(value="name")
    @ApiModelProperty(value="null")
    private String name;

    @TableField(value="role_key")
    @ApiModelProperty(value="角色权限字符串")
    private String roleKey;

    @TableField(value="status")
    @ApiModelProperty(value="角色状态 0:禁用，1:启用")
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
