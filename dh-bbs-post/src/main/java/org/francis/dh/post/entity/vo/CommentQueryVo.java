package org.francis.dh.post.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Franc1s
 * @date 2022/6/22
 * @apiNote
 */
@ApiModel("评论查询参数")
@Data
public class CommentQueryVo {
    @ApiModelProperty(value = "页码",required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "页大小",required = true)
    private Integer pageSize;

    @NotNull(message = "帖子编号不能为空")
    @ApiModelProperty(value = "帖子编号",required = true)
    private Long id;
}
