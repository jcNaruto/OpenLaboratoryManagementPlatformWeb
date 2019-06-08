package com.jiachenzh.entity;

import java.util.List;

/**
 * @ClassName UserActivityTo
 * @Description TODO
 * @Author
 * @Date 2019/3/9 9:07
 * @Version 1.0
 */
public class UserActivityTo  {
    public UserActivityTo() {
    }

    public UserActivityTo(ActivityDO activityDO, List<ActivityDetailDO> activityDetailDOList) {
        this.activityDO = activityDO;
        this.activityDetailDOList = activityDetailDOList;
    }

    private ActivityDO activityDO;

    private List<ActivityDetailDO> activityDetailDOList;

    public ActivityDO getActivityDO() {
        return activityDO;
    }

    public void setActivityDO(ActivityDO activityDO) {
        this.activityDO = activityDO;
    }

    public List<ActivityDetailDO> getActivityDetailDOList() {
        return activityDetailDOList;
    }

    public void setActivityDetailDOList(List<ActivityDetailDO> activityDetailDOList) {
        this.activityDetailDOList = activityDetailDOList;
    }
}
