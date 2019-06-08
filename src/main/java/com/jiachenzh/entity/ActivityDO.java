package com.jiachenzh.entity;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="activity")
public class ActivityDO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer activityId;
    @NotNull(message = "活动类型不能为空")
    private Integer type;
    @Column(name = "is_abled")
    private Integer abledFlag;
    @NotNull(message = "活动名称不能为空")
    private String name;
    private Integer restNumber;
    @NotNull(message = "活动最大容量不能为空")
    private Integer maxNumber;
    private Date createTime;
    private Date updateTime;
    @NotNull(message = "主讲人不能为空")
    private String teacher;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAbledFlag() {
        return abledFlag;
    }

    public void setAbledFlag(Integer abledFlag) {
        this.abledFlag = abledFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getRestNumber() {
        return restNumber;
    }

    public void setRestNumber(Integer restNumber) {
        this.restNumber = restNumber;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher == null ? null : teacher.trim();
    }

    @Override
    public String toString() {
        return "ActivityDO{" +
                "activityId=" + activityId +
                ", type=" + type +
                ", abledFlag=" + abledFlag +
                ", name='" + name + '\'' +
                ", restNumber=" + restNumber +
                ", maxNumber=" + maxNumber +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}