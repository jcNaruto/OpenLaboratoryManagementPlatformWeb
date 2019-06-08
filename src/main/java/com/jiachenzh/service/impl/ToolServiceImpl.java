package com.jiachenzh.service.impl;

import com.jiachenzh.common.ManipulationEnum;
import com.jiachenzh.dao.ToolDao;
import com.jiachenzh.entity.ToolDO;
import com.jiachenzh.exception.LaboratoryException;
import com.jiachenzh.service.ToolService;
import com.jiachenzh.util.QRCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ToolServiceImpl
 * @Description TODO
 * @Author
 * @Date 2019/3/5 18:06
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ToolServiceImpl implements ToolService {
    private static Logger logger = LoggerFactory.getLogger(ToolServiceImpl.class);
    @Autowired
    private ToolDao toolDao;
    @Autowired
    private RedisTemplate redisTemplate;


    //物品初可借状态
    private final int availableFlag = 0;
    //物品的不可借状态
    private final int unavailableFlag = 1;


    /**
     * @Author
     * @Description 获取物品列表
     * @Date 18:07 2019/3/5
     * @Param []
     * @throws
     * @return java.util.List<com.jiachenzh.entity.ToolDO>
     */

    @Override
    public List<ToolDO> listFindAll(Integer borrowedFlag) {
        return toolDao.findByBorrowedFlag(borrowedFlag);
    }


    @Override
    public void insertTool(ToolDO toolDO) {
        toolDO.setBorrowedFlag(availableFlag);
        toolDO.setCreateTime(new Date());
        toolDO.setUpdateTime(new Date());
        ToolDO save = toolDao.save(toolDO);
        if(save == null){
            throw new LaboratoryException("tool插入失败");
        }
    }

    /**
     * @Author
     * @Description 借物品或者还物品，同时传入用户id记录操作历史，返回字符串前端生成二维码
     * @Date 18:25 2019/3/5
     * @Param [toolDO, userId]
     * @throws
     * @return java.lang.String
     */
    @Override
    public String borrowOrLend(ToolDO toolDO, String userId) {

        //生成二维码的字符串的原始部分，为了接受硬件传来的反馈，手动以"-"为分隔符加上操作类型和userId
        String uuid = QRCodeUtils.getUUID();

        //将可借物品借出
        if(toolDO.getBorrowedFlag().equals(availableFlag)){
            toolDO.setBorrowedFlag(unavailableFlag);

            uuid += "-" + ManipulationEnum.BORROW_TOOL.getSeq() + "-201605059" + "-"+toolDO.getBoxId();
        //归还借出物品
        }else if(toolDO.getBorrowedFlag().equals(unavailableFlag)) {
            toolDO.setBorrowedFlag(availableFlag);
            uuid += "-" + ManipulationEnum.BACK_TOOL.getSeq() + "-201605059" + "-"+toolDO.getBoxId();
        } else {

            logger.error("物品状态出错");
            throw new LaboratoryException("物品状态出错");
        }


        redisTemplate.opsForValue().set(uuid,true, 1, TimeUnit.MINUTES);

        return uuid;
    }
}
