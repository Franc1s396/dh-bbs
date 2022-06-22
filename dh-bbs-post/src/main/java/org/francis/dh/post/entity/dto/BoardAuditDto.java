package org.francis.dh.post.entity.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Franc1s
 * @date 2022/6/22
 * @apiNote
 */
@ApiModel("板块审核DTO")
@Data
public class BoardAuditDto {
    @ApiModelProperty(value = "板块审核编号")
    private Long id;

    @ApiModelProperty(value = "板块名字")
    private String name;

    @ApiModelProperty(value = "板块申请人编号")
    private Long createUser;

    @ApiModelProperty(value = "板块申请人昵称")
    private String createUserNickname;

    @ApiModelProperty(value = "板块审核人编号")
    private Long auditUser;

    @ApiModelProperty(value = "板块审核人昵称")
    private String auditUserNickname;

    @ApiModelProperty(value = "审核状态(0:未审核 1:已通过 2:未通过)")
    private Integer auditStatus;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;
}
