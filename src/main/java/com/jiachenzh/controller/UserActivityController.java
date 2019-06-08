package com.jiachenzh.controller;

import com.jiachenzh.common.Result;
import com.jiachenzh.common.StatusCode;
import com.jiachenzh.common.AbstractUserAuthorizationType;
import com.jiachenzh.entity.ActivityDO;
import com.jiachenzh.entity.ActivityTO;
import com.jiachenzh.entity.UserActivityDO;
import com.jiachenzh.entity.UserActivityTo;
import com.jiachenzh.service.UserActivityService;
import com.jiachenzh.util.BindResultUtil;
import com.jiachenzh.util.JwtUtil;
import com.jiachenzh.util.UserAuthorizationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

/**
 * @ClassName UserActivityController
 * @Description 学生选课相关
 * @Author
 * @Date 2019/3/4 14:38
 * @Version 1.0
 */
@RestController
@RequestMapping("/useractivity")
public class UserActivityController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserActivityService userActivityService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * @Author
     * @Description 学生选课
     * @Date 14:42 2019/3/4
     * @Param [userActivityDO]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @PostMapping
    @ResponseBody
    public Result chooseActivity(@Valid @RequestBody UserActivityDO userActivityDO, BindingResult bindingResult, HttpServletRequest request){
        BindResultUtil.logAndThrowException(bindingResult);

        UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY);

        userActivityService.insertOneUserActivity(userActivityDO);
        return new Result(true, StatusCode.OK,"选课成功");
    }

    /**
     * @Author
     * @Description 我的课表功能
     * @Date 22:51 2019/3/6
     * @Param [request]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @GetMapping("/myactivity")
    @ResponseBody
    public Result myActivity(HttpServletRequest request){
        UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY);

        String userId = jwtUtil.parseJWTGetUserId(request);

        List<UserActivityTo> userActivityList = userActivityService.listFindMyActivity(userId);
        return new ActivityTO(true, StatusCode.OK,"查询成功",userActivityList, getCurrWeek());
    }

    /**
     * @Author
     * @Description 得到当前周数
     * @Date 20:15 2019/3/7
     * @Param []
     * @throws
     * @return java.lang.Integer
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    private Integer getCurrWeek() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.YEAR);
        int j = calendar.get(Calendar.MONTH);
        //noinspection AlibabaUndefineMagicConstant
        if(j == 2) {
            //return null;
        }else //noinspection AlibabaUndefineMagicConstant
            if(j == 8) {
            //return null;
        }else if(j == 1) {
            i-=1;
        }
        LocalDate startDate = null;
        //noinspection AlibabaUndefineMagicConstant,AlibabaUndefineMagicConstant,AlibabaUndefineMagicConstant,AlibabaUndefineMagicConstant
        if(j==9 || j==10 || j==11 || j==12 || j==1) {
            startDate = LocalDate.of(i, Month.SEPTEMBER,3);
        }else //noinspection AlibabaUndefineMagicConstant,AlibabaUndefineMagicConstant,AlibabaUndefineMagicConstant,AlibabaUndefineMagicConstant,AlibabaUndefineMagicConstant
            if(j==3 || j==4 || j==5 || j==6 || j==7){
            startDate = LocalDate.of(i, Month.MARCH,1);
        }

        long until = until(startDate);
        until=-1*until;
        int week = (int) (until/7);
        //noinspection AlibabaUndefineMagicConstant
        if(until%7 >0) {
            week+=1;
        }
        return week;
    }

    /**
     * 计算当前日期与{@code endDate}的间隔天数
     *
     * @param endDate
     * @return 间隔天数
     */
    private long until(LocalDate endDate){
        return LocalDate.now().until(endDate, ChronoUnit.DAYS);
    }



}
