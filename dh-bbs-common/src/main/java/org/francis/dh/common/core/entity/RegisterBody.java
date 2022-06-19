package org.francis.dh.common.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 用户注册对象
 *
 * @author tianchin
 */
@ApiModel(value = "用户注册对象")
@Data
public class RegisterBody {
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

    @ApiModelProperty(value = "用户昵称")
    @NotBlank(message = "请输入用户昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号码")
    @Pattern(regexp="0?(13|14|15|18|17)[0-9]{9}",message="手机号码格式错误！")
    @NotBlank(message = "请输入手机号码")
    private String phone;
}
