package com.jiachenzh.entity;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="manipulation")
public class ManipulationDO {
    //TODO  操作记录为同步插入，如果出错，日志记录详细信息，但是给用户的提示为操作记录录入失败
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer manipulationId;
    @NotNull(message = "操作用户不能为空")
    private String userId;
    @NotNull(message = "操作时间不能为空")
    private Date time;
    @NotNull(message = "操作类型不能为空")
    private Integer functionType;
    @NotNull(message = "操作box不能为空")
    private Integer boxId;

    public Integer getManipulationId() {
        return manipulationId;
    }

    public void setManipulationId(Integer manipulationId) {
        this.manipulationId = manipulationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getFunctionType() {
        return functionType;
    }

    public void setFunctionType(Integer functionType) {
        this.functionType = functionType;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    @Override
    public String toString() {
        return "ManipulationDO{" +
                "manipulationId=" + manipulationId +
                ", userId='" + userId + '\'' +
                ", time=" + time +
                ", functionType=" + functionType +
                ", boxId=" + boxId +
                '}';
    }
}