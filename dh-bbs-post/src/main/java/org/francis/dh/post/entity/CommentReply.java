package org.francis.dh.post.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
 * @since 2022-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("b_comment_reply")
@ApiModel(value="CommentReply对象", description="")
public class CommentReply implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论回复编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "帖子编号")
    private Long postId;

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
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
