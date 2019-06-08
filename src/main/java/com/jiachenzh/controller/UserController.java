package com.jiachenzh.controller;

import com.jiachenzh.common.Result;
import com.jiachenzh.common.StatusCode;
import com.jiachenzh.entity.UserDO;
import com.jiachenzh.entity.UserTO;
import com.jiachenzh.entity.VerificationCodePO;
import com.jiachenzh.service.EmailService;
import com.jiachenzh.service.UserService;
import com.jiachenzh.util.BindResultUtil;
import com.jiachenzh.util.JwtUtil;
import com.jiachenzh.util.UserRoleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @ClassName UserController
 * @Description
 * @Author
 * @Date 2019/2/28 19:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * @Author
     * @Description 普通用户登陆
     * @Date 12:32 2019/3/6
     * @Param [userDO]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @PostMapping("/login")
    @ResponseBody
    public Result userLogin(@RequestBody UserDO userDO){
        UserDO userLogin = userService.logIn(userDO);
        if(userLogin != null){
            String jwt = jwtUtil.createJWT(userLogin.getUserId(), userLogin.getName(), UserRoleUtil.getValue(userLogin.getRole()));
            return new Result(true, StatusCode.OK,"登陆成功", new UserTO(jwt, userLogin.getName()));
        } else {
            return new Result(false, StatusCode.LOGINERROR, "登陆失败");
        }
    }


    /**
     * @Author
     * @Description 用户注册方法
     * @Date 22:16 2019/3/2
     * @Param [userDO, bindingResult]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @PostMapping("/register")
    @ResponseBody
    public Result userRegister(@Valid @RequestBody UserDO userDO, BindingResult bindingResul){
        BindResultUtil.logAndThrowException(bindingResul);
        userService.insertUser(userDO,userDO.getVerificationCode());
        return new Result(true, StatusCode.OK,"注册成功");
    }


    /*
     * @Author
     * @Description 通过verificationCodePO中的相应属性判断验证码类型并发送邮件
     * @Date 18:55 2019/3/1
     * @Param [verificationCodePO]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @PostMapping("/sendverificationcode")
    @ResponseBody
    public Result sendVerificationCodeByEmail(@Valid @RequestBody VerificationCodePO verificationCodePO, BindingResult bindingResult){
        BindResultUtil.logAndThrowException(bindingResult);
        emailService.emailVerify(verificationCodePO);
        emailService.sendEmailToUser(verificationCodePO);
        return new Result(true, StatusCode.OK,"发送成功");
    }

}
