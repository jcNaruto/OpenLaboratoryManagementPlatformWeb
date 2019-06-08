package com.jiachenzh.entity;

import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="tool")
public class ToolDO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer boxId;
    @NotBlank(message = "物品名称不能为空")
    private String name;
    @Column(name = "is_borrowed")
    private Integer borrowedFlag;
    private String userId;
    private Date createTime;
    private Date updateTime;

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getBorrowedFlag() {
        return borrowedFlag;
    }

    public void setBorrowedFlag(Integer borrowedFlag) {
        this.borrowedFlag = borrowedFlag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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

    @Override
    public String toString() {
        return "ToolDO{" +
                "boxId=" + boxId +
                ", name='" + name + '\'' +
                ", borrowedFlag=" + borrowedFlag +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}