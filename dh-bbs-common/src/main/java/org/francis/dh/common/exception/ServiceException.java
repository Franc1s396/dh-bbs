package org.francis.dh.common.exception;


/**
 * @author Franc1s
 * @date 2022/6/17
 * @apiNote
 */
public class ServiceException extends RuntimeException{
    private String message;
    private Integer code;

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
