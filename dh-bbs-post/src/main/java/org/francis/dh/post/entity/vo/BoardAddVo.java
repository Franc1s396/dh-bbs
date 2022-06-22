package org.francis.dh.post.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Franc1s
 * @date 2022/6/19
 * @apiNote
 */
@ApiModel("板块添加参数")
@Data
public class BoardAddVo {
    @NotBlank(message = "板块名字不能为空")
    @ApiModelProperty(value = "板块名字",required = true)
    private String name;
}
