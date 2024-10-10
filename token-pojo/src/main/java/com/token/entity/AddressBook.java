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
@TableName(value ="address_book")
@ApiModel(value = "AddressBook" , description="地址簿")
public class AddressBook  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id")
    @ApiModelProperty(value="主键")
    private Long id;

    @TableField(value="user_id")
    @ApiModelProperty(value="用户id")
    private Integer userId;

    @TableField(value="consignee")
    @ApiModelProperty(value="收货人")
    private String consignee;

    @TableField(value="sex")
    @ApiModelProperty(value="性别")
    private String sex;

    @TableField(value="phone")
    @ApiModelProperty(value="手机号")
    private String phone;

    @TableField(value="province_code")
    @ApiModelProperty(value="省级区划编号")
    private String provinceCode;

    @TableField(value="province_name")
    @ApiModelProperty(value="省级名称")
    private String provinceName;

    @TableField(value="city_code")
    @ApiModelProperty(value="市级区划编号")
    private String cityCode;

    @TableField(value="city_name")
    @ApiModelProperty(value="市级名称")
    private String cityName;

    @TableField(value="district_code")
    @ApiModelProperty(value="区级区划编号")
    private String districtCode;

    @TableField(value="district_name")
    @ApiModelProperty(value="区级名称")
    private String districtName;

    @TableField(value="detail")
    @ApiModelProperty(value="详细地址")
    private String detail;

    @TableField(value="label")
    @ApiModelProperty(value="标签")
    private String label;

    @TableField(value="is_default")
    @ApiModelProperty(value="默认 0 否 1是")
    private Integer isDefault;

}
