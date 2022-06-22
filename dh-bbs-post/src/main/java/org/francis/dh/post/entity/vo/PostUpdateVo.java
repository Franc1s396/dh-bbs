package org.francis.dh.post.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Franc1s
 * @date 2022/6/22
 * @apiNote
 */
@ApiModel("帖子更新参数")
@Data
public class PostUpdateVo {
    @ApiModelProperty(value = "帖子编号")
    private Long id;

    @ApiModelProperty(value = "板块编号")
    private Long boardId;

    @ApiModelProperty(value = "帖子标题")
    private String title;

    @ApiModelProperty(value = "帖子内容")
    private String content;

    @ApiModelProperty(value = "帖子封面")
    private String cover;
}
