package com.jiachenzh.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName WeekMaintenanceDO
 * @Description TODO
 * @Author
 * @Date 2019/3/7 19:33
 * @Version 1.0
 */
@Entity
@Table(name="week_maintenance")
public class WeekMaintenanceDO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer weekMaintenanceId;
    @NotNull(message = "开学时间不能为空")
    private Date schoolTime;
    @NotNull(message = "放假时间不能为空")
    private Date vocationTime;
    private String queryKey;

    public Integer getWeekMaintenanceId() {
        return weekMaintenanceId;
    }

    public void setWeekMaintenanceId(Integer weekMaintenanceId) {
        this.weekMaintenanceId = weekMaintenanceId;
    }

    public Date getSchoolTime() {
        return schoolTime;
    }

    public void setSchoolTime(Date schoolTime) {
        this.schoolTime = schoolTime;
    }

    public Date getVocationTime() {
        return vocationTime;
    }

    public void setVocationTime(Date vocationTime) {
        this.vocationTime = vocationTime;
    }

    public String getQueryKey() {
        return queryKey;
    }

    public void setQueryKey(String queryKey) {
        this.queryKey = queryKey;
    }

    @Override
    public String toString() {
        return "WeekMaintenanceDO{" +
                "weekMaintenanceId=" + weekMaintenanceId +
                ", schoolTime=" + schoolTime +
                ", vocationTime=" + vocationTime +
                ", queryKey='" + queryKey + '\'' +
                '}';
    }
}
