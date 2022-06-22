package org.francis.dh.post.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Franc1s
 * @date 2022/6/22
 * @apiNote
 */
@ApiModel("帖子添加参数")
@Data
public class PostAddVo {
    @NotNull(message = "板块不能为空")
    @ApiModelProperty(value = "板块编号",required = true)
    private Long boardId;

    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "帖子标题",required = true)
    private String title;

    @NotBlank(message = "内容不能为空")
    @ApiModelProperty(value = "帖子内容",required = true)
    private String content;

    @ApiModelProperty(value = "帖子封面")
    private String cover;
}
