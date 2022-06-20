package org.francis.dh.system.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Franc1s
 * @date 2022/6/19
 * @apiNote
 */
@ApiModel(value = "用户添加参数")
@Data
public class UserAddVo {
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户性别")
    private String sex;
}
