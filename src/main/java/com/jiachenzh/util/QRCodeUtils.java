package com.jiachenzh.util;

import java.util.UUID;

/**
 * @ClassName QRCodeUtils
 * @Description
 * @Author
 * @Date 2019/3/5 19:06
 * @Version 1.0
 */
public class QRCodeUtils {

    /**
     * @Author
     * @Description 手动去除“-”,之后会用此符号做隔断号
     * @Date 20:34 2019/3/4
     * @Param []
     * @throws
     * @return java.lang.String
     */
    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
}
