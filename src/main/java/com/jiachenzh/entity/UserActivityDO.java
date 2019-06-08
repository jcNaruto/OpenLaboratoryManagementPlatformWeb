package com.jiachenzh.entity;

import javax.persistence.*;

@Entity
@Table(name="user_activity")
@IdClass(UserActivityMultiKey.class)
public class UserActivityDO {
    @Id
    private String userId;
    @Id
    private Integer activityId;
    @Column(name = "is_completed")
    private Integer completedFlag;
    private Integer score;

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

    public Integer getCompletedFlag() {
        return completedFlag;
    }

    public void setCompletedFlag(Integer completedFlag) {
        this.completedFlag = completedFlag;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "UserActivityDO{" +
                "userId='" + userId + '\'' +
                ", activityId=" + activityId +
                ", completedFlag=" + completedFlag +
                ", score=" + score +
                '}';
    }
}