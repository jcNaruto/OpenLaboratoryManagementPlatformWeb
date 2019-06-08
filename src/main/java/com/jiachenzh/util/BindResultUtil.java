package com.jiachenzh.util;

import com.jiachenzh.exception.LaboratoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.validation.BindingResult;
import java.util.List;

/**
 * @ClassName BindResultUtil
 * @Description BindResult工具类，提出参数校验的重复代码
 * @Author
 * @Date 2019/3/4 14:45
 * @Version 1.0
 */
public class BindResultUtil {

    private static final Logger logger = LoggerFactory.getLogger(BindResultUtil.class);

    public static void logAndThrowException(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> exceptions = bindingResult.getAllErrors();
            for (int i = 0; i < exceptions.size(); i++){
                logger.warn(exceptions.get(i).getDefaultMessage(),exceptions.get(i));
            }
            throw new LaboratoryException(exceptions.get(0).getDefaultMessage());
        }
    }

}
