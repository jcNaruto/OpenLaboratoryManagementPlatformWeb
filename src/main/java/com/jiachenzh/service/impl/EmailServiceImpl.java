package com.jiachenzh.service.impl;

import com.jiachenzh.dao.UserDao;
import com.jiachenzh.entity.UserDO;
import com.jiachenzh.entity.VerificationCodePO;
import com.jiachenzh.exception.LaboratoryException;
import com.jiachenzh.service.EmailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName EmailServiceImpl
 * @Description
 * @Author
 * @Date 2019/3/2 11:15
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class EmailServiceImpl implements EmailService {
    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String senderMailAddress;

    //用户注册业务发送验证码
    private  final int USER_REGISTER_EMAIL = 0;
    //用户修改密码发送验证码
    private  final int CHANGE_PWD_EMAIL = 1;
    //用户注册发送验证码有效期为15分钟，并且存储在redis中的形式为user_register_email:+随机生成的6位数字
    public final static String USER_REGISTER_REDIS_PREFIX = "user_register_email_";
    //确保用户获取验证码的间隔时间为15秒，向redis中存入为期15秒的"user_register_register_flag"：true
    public final static String USER_REGISTER_CLICK_FLAG = "user_register_register_flag_";

    /**
     * @Author
     * @Description 发送验证码邮件前的条件校验
     * @Date 9:40 2019/3/3
     * @Param [verificationCodePO]
     * @throws
     * @return void
     */
    @Override
    public void emailVerify(VerificationCodePO verificationCodePO){
        //确保用户的获取验证码间隔15秒
        Object flag = redisTemplate.opsForValue().get(USER_REGISTER_CLICK_FLAG + verificationCodePO.getEmail());
        if(flag != null){
            throw new LaboratoryException("验证码获取频繁");
        }
        UserDO userDaoByEmail = userDao.findByEmail(verificationCodePO.getEmail());
        if(userDaoByEmail != null){
            throw new LaboratoryException("该email以注册过");
        }
    }

   /**
    * @Author
    * @Description 仅仅发送邮件不做任何的校验处理，为异步方法
    * @Date 9:41 2019/3/3
    * @Param [verificationCodePO]
    * @throws
    * @return void
    */
    @Override
    @Async
    public void sendEmailToUser(VerificationCodePO verificationCodePO) {
        if(verificationCodePO.getVerificationCodeType() == USER_REGISTER_EMAIL){
            //发送HTML格式的邮件
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            String verificationCode = RandomStringUtils.randomNumeric(6);
            redisTemplate.opsForValue().set(USER_REGISTER_REDIS_PREFIX+verificationCodePO.getEmail(),verificationCode,10L, TimeUnit.MINUTES);
           //渲染邮件
            String text = getStringByHTML(verificationCodePO.getEmail(), verificationCode);
            try {
                helper.setSubject("格智空间验证码");
                helper.setText(text,true);
                helper.setTo(verificationCodePO.getEmail());
                helper.setFrom(senderMailAddress);
            } catch (MessagingException e) {
                logger.warn("邮件构建失败"+e.getMessage(),e);
                throw new LaboratoryException("邮件发送失败");
            }
            mailSender.send(mimeMessage);
            //向redis中设置15s的用户获取验证码间隔的标志
            redisTemplate.opsForValue().set(USER_REGISTER_CLICK_FLAG+verificationCodePO.getEmail(),true,15, TimeUnit.SECONDS);
        } else if(verificationCodePO.getVerificationCodeType() == CHANGE_PWD_EMAIL){

        }
    }

    /**
     * @Author
     * @Description  使用thymeleaf获取邮件模板，并填充部分邮箱地址和验证码，
     * @Date 13:53 2019/3/2
     * @Param [email, verificationCode]
     * @throws
     * @return java.lang.String 已经准备好的HTML字符串，直接传入serText中即可
     */
    private String getStringByHTML(String email,String verificationCode){
        Map<String,Object> temp = new HashMap<>(3);
        Context context = new Context();
        temp.put("email",email);
        temp.put("verificationCode",verificationCode);
        context.setVariables(temp);
        String content = this.templateEngine.process("VerificationCodeTemplate.html", context);
        return content;
    }
}
