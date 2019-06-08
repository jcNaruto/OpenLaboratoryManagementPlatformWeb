package com.jiachenzh.controller;

import com.jiachenzh.common.AbstractUserAuthorizationType;
import com.jiachenzh.common.Result;
import com.jiachenzh.common.StatusCode;
import com.jiachenzh.service.DoorService;
import com.jiachenzh.util.JwtUtil;
import com.jiachenzh.util.UserAuthorizationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName DoorController
 * @Description 该controller不对应DO实体，仅仅和门禁业务相关
 * @Author
 * @Date 2019/3/4 20:16
 * @Version 1.0
 */
@RestController
@RequestMapping("/door")
public class DoorController {

    @Autowired
    private DoorService doorService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * @Author Jiacheng
     * @Description 开门
     * @Date 9:34 2019/6/8
     * @Param [request]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @GetMapping("/open")
    @ResponseBody
    public Result openDoor(HttpServletRequest request){
        UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY);
        final String userId = jwtUtil.parseJWTGetUserId(request);
        String openDoorUUID = doorService.openDoor(userId);
        return new Result(true, StatusCode.OK,"二维码请求成功，该二维码一分钟内有效",openDoorUUID);
    }

}
