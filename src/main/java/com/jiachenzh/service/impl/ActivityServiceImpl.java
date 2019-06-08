package com.jiachenzh.service.impl;

import com.jiachenzh.dao.ActivityDao;
import com.jiachenzh.dao.ActivityDetailDao;
import com.jiachenzh.entity.ActivityDO;
import com.jiachenzh.entity.ActivityDetailDO;
import com.jiachenzh.entity.ActivityPO;
import com.jiachenzh.entity.UserActivityTo;
import com.jiachenzh.exception.LaboratoryException;
import com.jiachenzh.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ActivityServiceImpl
 * @Description
 * @Author
 * @Date 2019/3/4 13:57
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActivityDetailDao activityDetailDao;
    //activity开放状态标识
    private final int activityAbledFlag = 1;


    @Override
    public List<UserActivityTo> listFindAllByType(ActivityPO activityPO) {
        if(activityPO.getMyAbledFlag() == activityAbledFlag){
            List<ActivityDO> activityDOList = activityDao.findByAbledFlag(activityAbledFlag);
            List<UserActivityTo> myActivityList = new ArrayList<>();
            //TODO 改进的地方，for循环中连接数据库查找，改进
            for(int i = 0; i < activityDOList.size(); i++){
                Integer activityId = activityDOList.get(i).getActivityId();
                List<ActivityDetailDO> activityDetailDOList = activityDetailDao.findByActivityId(activityId);
                myActivityList.add(new UserActivityTo(activityDOList.get(i), activityDetailDOList));
            }
            return myActivityList;
        } else {
            return null;
        }
    }

    @Override
    public void insert(ActivityDO activityDO) {
        activityDO.setAbledFlag(activityAbledFlag);
        activityDO.setCreateTime(new Date());
        activityDO.setRestNumber(activityDO.getMaxNumber());
        activityDO.setUpdateTime(new Date());
        ActivityDO save = activityDao.save(activityDO);
        if(save == null){
            throw new LaboratoryException("课程插入失败");
        }
    }

}
