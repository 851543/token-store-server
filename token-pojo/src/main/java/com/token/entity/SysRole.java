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
    @ApiModelProperty(value="null")
    private Long id;

    @TableField(value="name")
    @ApiModelProperty(value="null")
    private String name;

    @TableField(value="role_key")
    @ApiModelProperty(value="角色权限字符串")
    private String roleKey;

    @TableField(value="status")
    @ApiModelProperty(value="角色状态（0正常 1停用）")
    private String status;

    @TableField(value="del_flag")
    @ApiModelProperty(value="del_flag")
    private Integer delFlag;

    @TableField(value="create_by")
    @ApiModelProperty(value="null")
    private Integer createBy;

    @TableField(value="create_time")
    @ApiModelProperty(value="null")
    private LocalDateTime createTime;

    @TableField(value="update_by")
    @ApiModelProperty(value="null")
    private Integer updateBy;

    @TableField(value="update_time")
    @ApiModelProperty(value="null")
    private LocalDateTime updateTime;

    @TableField(value="remark")
    @ApiModelProperty(value="备注")
    private String remark;

}
