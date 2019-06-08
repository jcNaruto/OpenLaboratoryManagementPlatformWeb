package com.jiachenzh.util;

import com.jiachenzh.common.AbstractUserAuthorizationType;
import com.jiachenzh.exception.LaboratoryException;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserAuthorizationUtil
 * @Description 抽取每个具体的controller层中的权限验证的方法
 * @Author
 * @Date 2019/3/6 21:38
 * @Version 1.0
 */
public class UserAuthorizationUtil {

    /**
     * @Author
     * @Description 高权限的人不具备低权限的角色的功能，高权限的人的个别功能与低权限的人有只有很低的重合，为了安全起见
     * 对于teacher重新实现少数的相似功能
     * @Date 22:13 2019/3/6
     * @Param [request, userAuthorizationKey]
     * @throws
     * @return void
     */
    public static void userAuthorization(HttpServletRequest request, String userAuthorizationKey){
        Claims claims=(Claims) request.getAttribute(userAuthorizationKey);
        if(claims==null){
            if(AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY.equals(userAuthorizationKey)){
                throw new LaboratoryException("请登陆");
            }
            throw new LaboratoryException("无权访问");
        }
    }
}
