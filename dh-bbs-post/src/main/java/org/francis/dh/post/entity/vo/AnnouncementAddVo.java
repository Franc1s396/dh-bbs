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
@ApiModel(value = "公告添加参数")
@Data
public class AnnouncementAddVo {
    @NotBlank(message = "公告内容不能为空")
    @ApiModelProperty(value = "公告内容",required = true)
    private String content;
}
