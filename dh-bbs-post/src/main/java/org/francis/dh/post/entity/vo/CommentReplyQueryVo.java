package org.francis.dh.post.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Franc1s
 * @date 2022/6/22
 * @apiNote
 */
@ApiModel("评论回复查询参数")
@Data
public class CommentReplyQueryVo {
    @ApiModelProperty(value = "页码",required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "页大小",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "评论编号",required = true)
    private Long commentId;
}
