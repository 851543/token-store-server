package com.token.entity;

import com.token.annotation.Sex;
import com.token.constant.MessageConstant;
import com.token.dto.EmployeeLoginDTO;
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

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;


/**
 * @author 851543
 * @description
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "employee")
@ApiModel(value = "Employee", description = "员工信息")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

    @TableField(value = "name")
    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = MessageConstant.NAME_NOT_EMPTY)
    @Pattern(regexp = "^\\S{2,12}$", message = MessageConstant.NAME_LENGTH_RANGE)
    private String name;

    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    @NotEmpty(message = MessageConstant.USERNAME_LENGTH_RANGE, groups = Employee.A.class)
    @Pattern(regexp = "^\\S{3,12}$", message = MessageConstant.USERNAME_LENGTH_RANGE, groups = Employee.C.class)
    private String username;

    @TableField(value = "password")
    @ApiModelProperty(value = "密码")
    private String password;

    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号")
    @NotEmpty(message = MessageConstant.PHONE_NOT_EMPTY)
    @Size(min = 11, max = 11, message = MessageConstant.PHONE_REGULAR)
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = MessageConstant.PHONE_FORMAT_ERROR)
    private String phone;

    @TableField(value = "sex")
    @ApiModelProperty(value = "性别")
    @Sex(message = MessageConstant.SEX_FORMAT_ERROR)
    private String sex;

    @TableField(value = "id_number")
    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @TableField(value = "status")
    @ApiModelProperty(value = "状态 0:禁用，1:启用")
    private Integer status;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @TableField(value = "create_user")
    @ApiModelProperty(value = "创建人")
    private Integer createUser;

    @TableField(value = "update_user")
    @ApiModelProperty(value = "修改人")
    private Integer updateUser;

    public interface A {

    }

    public interface B {

    }

    public interface C {

    }

    public interface D {

    }

    public interface E extends Default {

    }

    @GroupSequence({Employee.A.class, Employee.C.class, Employee.E.class})
    public interface Group {

    }

}
