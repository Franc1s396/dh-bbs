package org.francis.dh.system.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Franc1s
 * @date 2022/6/19
 * @apiNote
 */
@ApiModel(value = "用户查询参数")
@Data
public class UserQueryVo {
    @ApiModelProperty(value = "页码",required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "页大小",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "用户编号")
    private Long id;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "性别")
    private String sex;
}
