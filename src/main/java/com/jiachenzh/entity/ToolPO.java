package com.jiachenzh.entity;

/**
 * @ClassName ToolPO
 * @Description TODO
 * @Author
 * @Date 2019/3/9 15:58
 * @Version 1.0
 */
public class ToolPO {
    private Integer boxId;
    private Integer borrowedFlag;

    public Integer getBorrowedFlag() {
        return borrowedFlag;
    }

    public void setBorrowedFlag(Integer borrowedFlag) {
        this.borrowedFlag = borrowedFlag;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }
}
