package org.francis.dh.post.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Franc1s
 * @date 2022/6/21
 * @apiNote
 */
@ApiModel(value = "板块查询参数")
@Data
public class BoardQueryVo {
    @ApiModelProperty(value = "页码",required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "页大小",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "板块编号")
    private Long id;

    @ApiModelProperty(value = "板块名字")
    private String name;
}
