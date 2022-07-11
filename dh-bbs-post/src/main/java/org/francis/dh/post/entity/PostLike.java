package org.francis.dh.post.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("b_post_like")
@ApiModel(value="PostLike对象", description="")
public class PostLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "帖子点赞数主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "帖子编号")
    private Long postId;

    @ApiModelProperty(value = "帖子点赞数")
    private Integer postLike;


}
