package com.jiachenzh.util;

import com.jiachenzh.common.UserRoleEnum;

/**
 * @ClassName UserRoleUtil
 * @Description TODO
 * @Author
 * @Date 2019/3/6 13:03
 * @Version 1.0
 */
public class UserRoleUtil {

    public static String getValue(int code) {
        for (UserRoleEnum ele : UserRoleEnum.values()) {
            if(ele.getSeq() == code) {
                return ele.getDesc();
            }
        }
        return null;
    }

}
