package org.francis.dh.post.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Franc1s
 * @date 2022/6/19
 * @apiNote
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("b_announcement")
@ApiModel(value="Announcement对象", description="")
public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公告编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "公告内容")
    private String content;

    @ApiModelProperty(value = "公告创建人编号")
    private Long createUser;

    @ApiModelProperty(value = "播放(0:不播放 1:播放)")
    @TableField(value = "is_played")
    private Integer play;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}