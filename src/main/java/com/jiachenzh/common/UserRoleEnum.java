package com.jiachenzh.common;

public enum UserRoleEnum {

    /*
     *角色为普通用户，例如学生等
     */
    USER_ROLE(1,"normal_user"),
    /*
     *角色为教师，该角色只允许管理员创建并授权，
     * 该角色可以给普通用户进行门禁和借用物品的授权
     */
    TEACHER_ROLE(2,"teacher"),
    /*
     *角色为管理员，允许创建教师角色，系统交付时以初始化完毕
     */
    ADMIN_ROLE(3,"admin");

    private int seq;
    private String desc;

    UserRoleEnum(int seq, String desc){
        this.seq = seq;
        this.desc = desc;
    }

    public int getSeq(){
        return seq;
    }
    public String getDesc(){
        return desc;
    }


}
