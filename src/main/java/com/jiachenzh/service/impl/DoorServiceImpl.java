package com.jiachenzh.service.impl;

import com.jiachenzh.dao.UserDao;
import com.jiachenzh.entity.UserDO;
import com.jiachenzh.exception.LaboratoryException;
import com.jiachenzh.service.DoorService;
import com.jiachenzh.util.QRCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName DoorServiceImpl
 * @Description TODO
 * @Author
 * @Date 2019/3/4 20:22
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class DoorServiceImpl implements DoorService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;

    //用户具备门禁和借用物品的权限
    private static final int USER_ALLOW_ALLOWED = 1;

    @Override
    public String openDoor(String userId) {
        UserDO userDO = userDao.findByUserId(userId);
        if(userDO.getAllowedFlag() != USER_ALLOW_ALLOWED){
            throw new LaboratoryException("您不具备相应权限，请首先申请权限");
        }

        String openDoorUUID = QRCodeUtils.getUUID() + "-" + userId;

        redisTemplate.opsForValue().set(openDoorUUID,true,1, TimeUnit.MINUTES);
        return openDoorUUID;
    }


}
