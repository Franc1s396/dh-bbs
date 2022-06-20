package org.francis.dh.post.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Franc1s
 * @date 2022/6/19
 * @apiNote
 */
@ApiModel(value = "公告查询参数")
@Data
public class AnnouncementQueryVo {
    @ApiModelProperty(value = "页码",required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "页大小",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "公告编号")
    private Integer id;

    @ApiModelProperty(value = "公告内容")
    private String content;

    @ApiModelProperty(value = "公告创建人编号")
    private Long createUser;
}
