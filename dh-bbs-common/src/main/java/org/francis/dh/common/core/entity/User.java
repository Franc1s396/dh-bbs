package org.francis.dh.common.core.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author francis
 * @since 2022-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "用户封禁状态(0:正常 1:封禁)")
    private Integer userEnabled;

    @ApiModelProperty(value = "最后登录IP")
    private String loginIp;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime loginDate;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "角色")
    @TableField(exist = false)
    private List<Role> roles;
}
