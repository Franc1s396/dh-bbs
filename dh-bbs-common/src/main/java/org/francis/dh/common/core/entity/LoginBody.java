package org.francis.dh.common.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 用户登录对象
 *
 * @author tianchin
 */
@ApiModel("用户登录对象")
@Data
public class LoginBody {
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "请输入邮箱")
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 用户密码
     */
    @NotBlank(message = "请输入密码")
    @ApiModelProperty(value = "用户密码")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "请输入验证码")
    @ApiModelProperty(value = "验证码")
    private String code;

    /**
     * 唯一标识
     */
    @ApiModelProperty(value = "唯一标识")
    private String uuid;
}
