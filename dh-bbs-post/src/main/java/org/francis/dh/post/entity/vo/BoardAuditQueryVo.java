package org.francis.dh.post.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Franc1s
 * @date 2022/6/22
 * @apiNote
 */
@ApiModel("板块审核查询参数")
@Data
public class BoardAuditQueryVo {
    @ApiModelProperty(value = "页码",required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "页大小",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "板块审核编号")
    private Long id;

    @ApiModelProperty(value = "板块名字")
    private String name;

    @ApiModelProperty(value = "板块申请人编号")
    private Long createUser;

    @ApiModelProperty(value = "板块审核人编号")
    private Long auditUser;

    @ApiModelProperty(value = "审核状态")
    private Integer auditStatus;
}
