package com.jiachenzh.controller;

import com.jiachenzh.common.Result;
import com.jiachenzh.common.StatusCode;
import com.jiachenzh.common.AbstractUserAuthorizationType;
import com.jiachenzh.entity.ToolDO;
import com.jiachenzh.service.ToolService;
import com.jiachenzh.util.BindResultUtil;
import com.jiachenzh.util.JwtUtil;
import com.jiachenzh.util.UserAuthorizationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @ClassName ToolController
 * @Description 物品相关
 * @Author
 * @Date 2019/3/5 18:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    private ToolService toolService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * @Author
     * @Description 获取物品列表
     * @Date 11:51 2019/3/9
     * @Param [request]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @PostMapping("/search")
    @ResponseBody
    public Result findAllTools( @RequestBody ToolDO toolDO, HttpServletRequest request){
        UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY);
        return new Result(true, StatusCode.OK,"查询成功",toolService.listFindAll(toolDO.getBorrowedFlag()));
    }

    /**
     * @Author
     * @Description 后台插入tool
     * @Date 11:51 2019/3/9
     * @Param [toolDO, bindingResult, request]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @PostMapping
    @ResponseBody
    public Result insertTool(@Valid @RequestBody ToolDO toolDO, BindingResult bindingResult, HttpServletRequest request){
        BindResultUtil.logAndThrowException(bindingResult);
        UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.TEACHER_AUTHORIZATION_KEY);
        toolService.insertTool(toolDO);
        return new Result(true, StatusCode.OK,"插入成功");
    }

    /**
     * @Author
     * @Description 物品的借用或者归还
     * @Date 18:23 2019/3/5
     * @Param [toolDO, bindingResult]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @PostMapping("/circulate")
    @ResponseBody
    public Result borrowOrLend(@RequestBody ToolDO toolDO, HttpServletRequest request){
        UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY);
        String userId = jwtUtil.parseJWTGetUserId(request);
        String toolUuid = toolService.borrowOrLend(toolDO,userId);
        return new Result(true, StatusCode.OK, "请求成功，请用二维码对准扫码机",toolUuid);
    }

}
