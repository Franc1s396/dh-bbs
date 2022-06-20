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
@TableName("b_user_like_post")
@ApiModel(value="UserLikePost对象", description="")
public class UserLikePost implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "帖子编号")
    private Long postId;


}
