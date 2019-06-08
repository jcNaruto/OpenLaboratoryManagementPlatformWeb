package com.jiachenzh.entity;

/**
 * @ClassName UserTO
 * @Description 用于用户登陆之后返回username和token
 * @Author
 * @Date 2019/3/6 12:50
 * @Version 1.0
 */
public class UserTO {

    public UserTO(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }

    private String token;

   private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
