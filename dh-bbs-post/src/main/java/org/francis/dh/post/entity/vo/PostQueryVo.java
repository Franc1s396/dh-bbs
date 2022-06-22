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
@ApiModel("帖子查询参数")
@Data
public class PostQueryVo {
    @ApiModelProperty(value = "页码",required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "页大小",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "帖子编号")
    private Long id;

    @ApiModelProperty(value = "用户编号")
    private Long userId;

    @ApiModelProperty(value = "板块编号")
    private Long boardId;

    @ApiModelProperty(value = "帖子标题")
    private String title;

    @ApiModelProperty(value = "帖子内容")
    private String content;

    @ApiModelProperty(value = "是否置顶")
    private Integer top;
}
