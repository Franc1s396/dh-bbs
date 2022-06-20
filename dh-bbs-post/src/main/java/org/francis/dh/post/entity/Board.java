package org.francis.dh.post.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("b_board")
@ApiModel(value="Board对象", description="")
public class Board implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "板块编号")
    private Long id;

    @ApiModelProperty(value = "板块名字")
    private String name;

    @ApiModelProperty(value = "板块创建者")
    private Long createUser;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
