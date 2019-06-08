package com.jiachenzh.service;

import com.jiachenzh.entity.ActivityDO;
import com.jiachenzh.entity.ActivityPO;
import com.jiachenzh.entity.UserActivityTo;

import java.util.List;

public interface ActivityService {

    /**
     * @Author Jiacheng
     * @Description 查询处于开课状态的所有课程
     * @Date 23:43 2019/6/7
     * @Param [activityPO] activityPO
     * @throws
     * @return java.util.List<com.jiachenzh.entity.UserActivityTo>
     */
    List<UserActivityTo> listFindAllByType(ActivityPO activityPO);

    /**
     * @Author Jiacheng
     * @Description 课程插入
     * @Date 23:44 2019/6/7
     * @Param [activityDO] activityDO
     * @throws
     * @return void
     */
    void insert(ActivityDO activityDO);
}
