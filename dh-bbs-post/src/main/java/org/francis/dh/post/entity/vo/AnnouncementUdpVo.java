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
@ApiModel(value = "公告更新参数")
@Data
public class AnnouncementUdpVo {
    @NotNull(message = "公告编号不能为空")
    @ApiModelProperty(value = "公告编号")
    private Integer id;

    @NotBlank(message = "公告内容不能为空")
    @ApiModelProperty(value = "公告内容")
    private String content;
}
