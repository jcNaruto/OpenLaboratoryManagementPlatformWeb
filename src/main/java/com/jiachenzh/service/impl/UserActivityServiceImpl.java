package com.jiachenzh.service.impl;

import com.jiachenzh.dao.ActivityDao;
import com.jiachenzh.dao.ActivityDetailDao;
import com.jiachenzh.dao.UserActivityDao;
import com.jiachenzh.entity.ActivityDO;
import com.jiachenzh.entity.ActivityDetailDO;
import com.jiachenzh.entity.UserActivityDO;
import com.jiachenzh.entity.UserActivityTo;
import com.jiachenzh.exception.LaboratoryException;
import com.jiachenzh.service.UserActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserActivityServiceImpl
 * @Description TODO
 * @Author
 * @Date 2019/3/4 14:32
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class UserActivityServiceImpl implements UserActivityService {
    private static Logger logger = LoggerFactory.getLogger(UserActivityServiceImpl.class);

    @Autowired
    private ActivityDetailDao activityDetailDao;
    @Autowired
    private UserActivityDao userActivityDao;
    @Autowired
    private ActivityDao activityDao;
    //未完成的课程的标识
    private final Integer unCompletedFlag = 0;

    /**
     * @Author
     * @Description 选课
     * @Date 19:32 2019/3/4
     * @Param [userActivityDO]
     * @throws
     * @return void
     */
    @Override
    public void insertOneUserActivity(UserActivityDO userActivityDO) {
        //TODO 查重
        UserActivityDO save = userActivityDao.save(userActivityDO);
        if(save == null){
            throw new LaboratoryException("选课失败");
        }
    }

    /**
     * @Author
     * @Description 我的课表,只查看未完成的activity，也就是当前正在进行的activity
     * @Date 19:33 2019/3/4
     * @Param [userId]
     * @throws
     * @return java.util.List<com.jiachenzh.entity.ActivityDO>
     */
    @Override
    public List<UserActivityTo> listFindMyActivity(String userId) {
        List<UserActivityDO> userActivityDOList = userActivityDao.findByUserIdAndCompletedFlag(userId, unCompletedFlag);
        List<UserActivityTo> myActivityList = new ArrayList<>();
        //TODO 改进的地方，for循环中连接数据库查找，改进
        for(int i = 0; i < userActivityDOList.size(); i++){
            Integer activityId = userActivityDOList.get(i).getActivityId();
            ActivityDO activityDO = activityDao.findByActivityId(activityId);
            List<ActivityDetailDO> activityDetailDOList = activityDetailDao.findByActivityId(activityDO.getActivityId());
            myActivityList.add(new UserActivityTo(activityDO, activityDetailDOList));
        }
        return myActivityList;
    }




}
