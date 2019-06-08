package com.jiachenzh.common;
/**
 * @Author
 * @Description 用户可执行的  操作类型
 * @Date 8:25 2019/3/7
 */
public enum ManipulationEnum {

    /*
     *操作中的开门
     */
    OPEN_DOOR(1,"开门"),
    /*
     *操作中的借用物品
     */
    BORROW_TOOL(2,"借用物品"),
    /*
     *操作中的归还物品
     */
    BACK_TOOL(3,"归还物品");

    private int seq;
    private String desc;

    ManipulationEnum(int seq, String desc){
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
