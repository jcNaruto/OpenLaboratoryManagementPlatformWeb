package com.jiachenzh.controller;

import com.jiachenzh.common.Result;
import com.jiachenzh.common.StatusCode;
import com.jiachenzh.common.AbstractUserAuthorizationType;
import com.jiachenzh.entity.ActivityDO;
import com.jiachenzh.entity.ActivityPO;
import com.jiachenzh.entity.UserActivityDO;
import com.jiachenzh.entity.UserActivityTo;
import com.jiachenzh.service.ActivityService;
import com.jiachenzh.util.BindResultUtil;
import com.jiachenzh.util.UserAuthorizationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName ActivityController
 * @Description
 * @Author
 * @Date 2019/3/4 14:12
 * @Version 1.0
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    /**
     * @Author
     * @Description 根据传入的参数查询所有课程，该参数仅仅是课程开放与否
     * @Date 14:20 2019/3/4
     * @Param [activityPO]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @PostMapping("/search")
    @ResponseBody
    public Result findAllActivity(@RequestBody ActivityPO activityPO){
        List<UserActivityTo> list = activityService.listFindAllByType(activityPO);
        return new Result(true, StatusCode.OK,"查询成功", list);
    }


    /**
     * @Author
     * @Description 后台管理员插入活动
     * @Date 14:30 2019/3/4
     * @Param [activityDO]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @PostMapping
    @ResponseBody
    public Result insert(@Valid @RequestBody ActivityDO activityDO, BindingResult bindingResult, HttpServletRequest request){
        BindResultUtil.logAndThrowException(bindingResult);

        UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.ADMIN_AUTHORIZATION_KEY);

         activityService.insert(activityDO);
         return new Result(true, StatusCode.OK,"插入成功");
    }



}
