package com.token.dto;

import com.token.constant.MessageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


/**
 * @author 851543
 * @description
 */

@Data
@ApiModel(description = "员工登录时传递的数据模型")
public class EmployeeLoginDTO implements Serializable {

    @ApiModelProperty(value = "用户名")
    @NotEmpty(message = MessageConstant.USERNAME_LENGTH_RANGE, groups = A.class)
    @Pattern(regexp = "^\\S{3,12}$", message = MessageConstant.USERNAME_NOT_EMPTY, groups = C.class)
    private String username;

    @ApiModelProperty(value = "密码")
    @NotEmpty(message = MessageConstant.PASSWORD_NOT_EMPTY, groups = B.class)
    @Pattern(regexp = "^\\S{3,12}$", message = MessageConstant.PASSWORD_LENGTH_RANGE, groups = D.class)
    private String password;

    public interface A {

    }

    public interface B {

    }

    public interface C {

    }

    public interface D {

    }

    @GroupSequence({A.class, B.class, C.class, D.class})
    public interface Group {

    }

}
