package com.jiachenzh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
@Table(name="user")
public class UserDO {

    @Id
    @NotNull(message = "用户id不能为空")
    private String userId;
    @NotNull(message = "用户名不能为空")
    private String name;
    @NotNull(message = "密码不能为空")
    private String pwdHash;
    private String avatarHash;
    private String major;
    @Email(message = "email格式不正确")
    private String email;
    @Column(name = "is_allowed")
    private Integer allowedFlag;
    private Integer role;
    private Integer coin;
    private Date createTime;
    private Date updateTime;
    @Column(name = "is_deleted")
    private Integer deletedFlag;

    private String verificationCode;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Integer getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(Integer deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public String getAvatarHash() {
        return avatarHash;
    }

    public void setAvatarHash(String avatarHash) {
        this.avatarHash = avatarHash;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAllowedFlag() {
        return allowedFlag;
    }

    public void setAllowedFlag(Integer allowedFlag) {
        this.allowedFlag = allowedFlag;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
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
        return "UserDO{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", pwdHash='" + pwdHash + '\'' +
                ", avatarHash='" + avatarHash + '\'' +
                ", major='" + major + '\'' +
                ", email='" + email + '\'' +
                ", allowedFlag=" + allowedFlag +
                ", role=" + role +
                ", coin=" + coin +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deletedFlag=" + deletedFlag +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
