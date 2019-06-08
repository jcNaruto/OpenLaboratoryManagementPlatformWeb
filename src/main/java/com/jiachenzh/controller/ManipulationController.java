package com.jiachenzh.controller;

import com.jiachenzh.common.AbstractUserAuthorizationType;
import com.jiachenzh.common.Result;
import com.jiachenzh.common.StatusCode;
import com.jiachenzh.entity.ManipulationDO;
import com.jiachenzh.entity.ManipulationTO;
import com.jiachenzh.service.ManipulationSerice;
import com.jiachenzh.util.JwtUtil;
import com.jiachenzh.util.UserAuthorizationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName ManipulationController
 * @Description 操作历史相关
 * @Author
 * @Date 2019/3/9 21:24
 * @Version 1.0
 */
@RestController
@RequestMapping("/manipulate")
public class ManipulationController {
    @Autowired
    private ManipulationSerice manipulationSerice;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * @Author
     * @Description 查询所有操作历史
     * @Date 21:27 2019/3/9
     * @Param [request]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @GetMapping
    @ResponseBody
    public Result findAllActivity(HttpServletRequest request){
        UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY);
        final String userId = jwtUtil.parseJWTGetUserId(request);
        List<ManipulationTO> manipulationTOList = manipulationSerice.findAllByUserId(userId);
        return new Result(true, StatusCode.OK,"查询成功", manipulationTOList);
    }

}
