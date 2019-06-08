package com.jiachenzh.exception;

/**
 * @ClassName LaboratoryException
 * @Description Laboratory项目的自定义异常
 * @Author
 * @Date 2019/3/1 19:48
 * @Version 1.0
 */
public class LaboratoryException extends RuntimeException {

    // 异常信息
    private String message;

    public LaboratoryException() {
        super();
    }

    public LaboratoryException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
