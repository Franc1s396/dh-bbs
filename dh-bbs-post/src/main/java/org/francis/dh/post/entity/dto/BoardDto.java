package org.francis.dh.post.entity.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Franc1s
 * @date 2022/6/22
 * @apiNote
 */
@ApiModel("板块Dto")
@Data
public class BoardDto implements Serializable {
    @ApiModelProperty(value = "板块编号")
    private Long id;

    @ApiModelProperty(value = "板块名字")
    private String name;

    @ApiModelProperty(value = "板块创建者")
    private Long createUser;

    @ApiModelProperty(value = "创建者昵称")
    private String nickname;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;
}
