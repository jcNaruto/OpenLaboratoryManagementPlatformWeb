package com.jiachenzh.controller;

import com.jiachenzh.common.Result;
import com.jiachenzh.common.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理器，在此统一接受异常往外抛取，并进行相应的记录
 * @Author
 * @Date 2019/3/1 19:59
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        logger.warn(e.getMessage(),e);
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
