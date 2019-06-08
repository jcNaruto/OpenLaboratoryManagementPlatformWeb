package com.jiachenzh.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="activity_detail")
public class ActivityDetailDO {
    @Override
    public String toString() {
        return "ActivityDetailDO{" +
                "activityDetailId=" + activityDetailId +
                ", week='" + week + '\'' +
                ", day='" + day + '\'' +
                ", activityOrder='" + activityOrder + '\'' +
                ", location='" + location + '\'' +
                ", activityId=" + activityId +
                '}';
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer activityDetailId;
    @Range(min=1, max=30, message = "error：week超出范围")
    @NotNull(message = "必须指定第几周")
    private String week;
    @Range(min=1, max=7, message = "error：day超出范围")
    @NotNull(message = "必须指定星期几")
    private String day;
    @Range(min=1, max=12, message = "error：activityOrder超出范围")
    @NotNull(message = "必须指定第几节")
    private String activityOrder;
    @NotNull(message = "必须指定上课位置")
    private String location;
    @NotNull(message = "必须指定课程")
    private Integer activityId;

    public Integer getActivityDetailId() {
        return activityDetailId;
    }

    public void setActivityDetailId(Integer activityDetailId) {
        this.activityDetailId = activityDetailId;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public String getActivityOrder() {
        return activityOrder;
    }

    public void setActivityOrder(String activityOrder) {
        this.activityOrder = activityOrder == null ? null : activityOrder.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}