package org.francis.dh.post.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Franc1s
 * @date 2022/6/19
 * @apiNote
 */
@ApiModel("模块更新参数")
@Data
public class BoardUdpVo {
    @NotNull(message = "板块编号不能为空")
    @ApiModelProperty(value = "板块编号",required = true)
    private Long id;

    @NotBlank(message = "板块名字不能为空")
    @ApiModelProperty(value = "板块名字",required = true)
    private String name;
}
