package com.jiachenzh.service.impl;

import com.jiachenzh.common.ManipulationEnum;
import com.jiachenzh.dao.ManipulationDao;
import com.jiachenzh.entity.ManipulationDO;
import com.jiachenzh.exception.LaboratoryException;
import com.jiachenzh.service.HardWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName HardWareServiceImpl
 * @Description TODO
 * @Author
 * @Date 2019/3/5 19:47
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HardWareServiceImpl implements HardWareService {

    @Autowired
    private ManipulationDao manipulationDao;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 门在tool中并无对应，所以人为规定门的id为0，用于存储到操作的box_id中
     */
    private final int doorId = 0;
    /**
     * 开门操作的字符串分割操作之后的长度
     */
    private final int openDoorFlag = 2;
    /**
     * 物品相关操作分割操作之后的长度
     */
    private final int borrowBackToolFlag = 4;

    /**
     * @Author
     * @Description 硬件操作反馈的业务处理方法，redis验证+操作记录同步
     * @Date 19:53 2019/3/5
     * @Param [UUID]
     * @throws
     * @return void
     */
    @Override
    public void hardWareHandle(String uuid) {
        Object openDoorResult = redisTemplate.opsForValue().get(uuid);
        if(openDoorResult == null){
            throw new LaboratoryException("验证码无效，请重试");
        }

        String[] split = uuid.split("-");
        //异步执行操作记录同步
        if (split.length == openDoorFlag){
            insertManipulation(split[1], doorId, ManipulationEnum.OPEN_DOOR.getSeq());
        } else if(split.length == borrowBackToolFlag) {
            insertManipulation(split[1], Integer.getInteger(split[3]), Integer.getInteger(split[2]));
        }
    }



    /**
     * @Author
     * @Description 异步插入操作记录 ,直接给硬件反馈是否操作成功，避免操作插入失败而不允许操作本身
     * @Date 20:19 2019/3/5
     * @Param [userId, boxId, functionType]
     * @throws
     * @return void
     */
    @Async
    public void insertManipulation(String userId, Integer boxId, Integer functionType){

        ManipulationDO manipulationDO = new ManipulationDO();
        manipulationDO.setBoxId(boxId);
        manipulationDO.setFunctionType(functionType);
        manipulationDO.setUserId(userId);
        manipulationDO.setTime(new Date());
        ManipulationDO save = manipulationDao.save(manipulationDO);
        if(save == null){
            throw new LaboratoryException("操作记录同步失败");
        }
    }
}
