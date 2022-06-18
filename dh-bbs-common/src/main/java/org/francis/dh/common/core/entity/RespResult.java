package org.francis.dh.common.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Franc1s
 * @date 2022/1/29
 * @apiNote
 */
@Data
public class RespResult {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();
    @ApiModelProperty(value = "时间戳")
    private String dateTime;

    @JsonIgnore
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private RespResult() {
    }

    public static RespResult ok() {
        RespResult respResult = new RespResult();
        respResult.setCode(ResultCode.SUCCESS);
        respResult.setSuccess(true);
        respResult.setDateTime(formatter.format(LocalDateTime.now()));
        return respResult;
    }


    public static RespResult error() {
        RespResult respResult = new RespResult();
        respResult.setCode(ResultCode.ERROR);
        respResult.setSuccess(false);
        respResult.setMessage("失败");
        respResult.setDateTime(formatter.format(LocalDateTime.now()));
        return respResult;
    }

    public RespResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public RespResult message(String message){
        this.setMessage(message);
        return this;
    }

    public RespResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public RespResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public RespResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
