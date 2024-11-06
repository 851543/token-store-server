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
@TableName(value ="sys_menu")
@ApiModel(value = "SysMenu" , description="菜单表")
public class SysMenu  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id")
    @ApiModelProperty(value="null")
    private Long id;

    @TableField(value="menu_name")
    @ApiModelProperty(value="菜单名")
    private String menuName;

    @TableField(value="path")
    @ApiModelProperty(value="路由地址")
    private String path;

    @TableField(value="component")
    @ApiModelProperty(value="组件路径")
    private String component;

    @TableField(value="visible")
    @ApiModelProperty(value="菜单状态（0显示 1隐藏）")
    private String visible;

    @TableField(value="status")
    @ApiModelProperty(value="菜单状态（0正常 1停用）")
    private String status;

    @TableField(value="perms")
    @ApiModelProperty(value="权限标识")
    private String perms;

    @TableField(value="icon")
    @ApiModelProperty(value="菜单图标")
    private String icon;

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

    @TableField(value="del_flag")
    @ApiModelProperty(value="是否删除（0未删除 1已删除）")
    private Integer delFlag;

    @TableField(value="remark")
    @ApiModelProperty(value="备注")
    private String remark;

}
