package com.jiachenzh.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName UserActivityMultiKey
 * @Description user_acticity的复合主键类，用于UserActivityDO中,有两个复合主键组成
 * @Author
 * @Date 2019/3/3 10:47
 * @Version 1.0
 */
public class UserActivityMultiKey implements Serializable {

    private String userId;
    private Integer activityId;

    public UserActivityMultiKey() {
    }

    public UserActivityMultiKey(String userId, Integer activityId) {
        this.userId = userId;
        this.activityId = activityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final UserActivityMultiKey other = (UserActivityMultiKey)obj;
        if(userId == null){
            if(other.userId != null){
                return false;
            }
        }else if(!userId.equals(other.userId)){
            return false;
        }
        if(userId == null){
            if(other.userId != null){
                return false;
            }
        }else if(!userId.equals(other.userId)){
            return false;
        }
        if(activityId == null){
            if(other.activityId != null){
                return false;
            }
        }else if(!activityId.equals(other.activityId)){
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((activityId == null) ? 0 : activityId.hashCode());
        return result;

    }
}
