package org.francis.dh.post.entity.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Franc1s
 * @date 2022/6/22
 * @apiNote
 */
@ApiModel("评论回复DTO")
@Data
public class CommentReplyDto {
    @ApiModelProperty(value = "评论回复编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "帖子编号")
    private Long postId;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "用户姓名")
    private Long userNickname;

    @ApiModelProperty(value = "评论编号")
    private Long commentId;

    @ApiModelProperty(value = "回复内容")
    private String content;

    @ApiModelProperty(value = "回复对象")
    private Long toUser;

    @ApiModelProperty(value = "回复对象名字")
    private String toUserNickname;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;
}
