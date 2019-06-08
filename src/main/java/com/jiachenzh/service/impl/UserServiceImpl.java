package com.jiachenzh.service.impl;

import com.jiachenzh.common.UserRoleEnum;
import com.jiachenzh.dao.UserDao;
import com.jiachenzh.entity.UserDO;
import com.jiachenzh.exception.LaboratoryException;
import com.jiachenzh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //用户初始积分
    private static final int USER_INITIAL_COIN = 0;
    //用户的初始权限，均不具备门禁和借用物品的权限
    private static final int USER_INTIAL_ALLOWED = 0;




    @Override
    public List<UserDO> findAll(){
       return  userDao.findAll();
    }

    /**
     * @Author
     * @Description 用户注册，向数据库中插入用户
     * @Date 22:33 2019/3/2
     * @Param [userDO]
     * @throws
     * @return java.lang.Integer
     */
    @Override
    public void insertUser(UserDO userDO,String verificationCode) {
        verificationCodeCompare(userDO.getEmail(),verificationCode);

        //密码加密
        String newPWD = bCryptPasswordEncoder.encode(userDO.getPwdHash());
        userDO.setPwdHash(newPWD);
        userDO.setCreateTime(new Date());
        userDO.setUpdateTime(new Date());
        userDO.setRole(UserRoleEnum.USER_ROLE.getSeq());
        userDO.setCoin(USER_INITIAL_COIN);
        userDO.setAllowedFlag(USER_INTIAL_ALLOWED);
        userDO.setDeletedFlag(0);
        userDO.setAvatarHash("123123123");
        UserDO save = userDao.save(userDO);
        if(save == null){
            throw new LaboratoryException("注册失败");
        }
    }

    /**
     * @Author
     * @Description 用户登陆
     * @Date 12:21 2019/3/6
     * @Param [userDO]
     * @throws
     * @return com.jiachenzh.entity.UserDO
     */
    @Override
    public UserDO logIn(UserDO userDO) {
        UserDO userDaoByName = userDao.findByUserId(userDO.getUserId());
        //userDO.getPwdHash()为用户输入的密码，userDaoByName.getPwdHash()为数据库中查询的加密后的密码
        if(userDaoByName != null && bCryptPasswordEncoder.matches(userDO.getPwdHash(),userDaoByName.getPwdHash())){
            return userDaoByName;
        }
        return null;
    }


    /**
    * @Author
    * @Description 验证码验证功能
    * @Date 23:17 2019/3/2
    * @Param [email, rawCode]
    * @throws
    * @return void
    */
    private void verificationCodeCompare(String email, String rawCode){
        if(rawCode == null){
            throw new LaboratoryException("验证码为空");
        }
        String realCode = (String) redisTemplate.opsForValue().get(EmailServiceImpl.USER_REGISTER_REDIS_PREFIX + email);
        if(!rawCode.equals(realCode)){
          throw new LaboratoryException("验证码错误");
        }
    }


}
