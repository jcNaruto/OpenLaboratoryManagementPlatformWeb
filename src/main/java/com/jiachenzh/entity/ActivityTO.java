package com.jiachenzh.entity;

import com.jiachenzh.common.Result;

/**
 * @ClassName ActivityTO
 * @Description 用于我的课表的业务中多一个当前周数的属性
 * @Author
 * @Date 2019/3/4 21:09
 * @Version 1.0
 */
public class ActivityTO extends Result {

    private Integer currWeek;

    public ActivityTO() {
    }

    public ActivityTO(Boolean flag, Integer code, String message, Object data, Integer currWeek) {
        super(flag, code, message, data);
        this.currWeek = currWeek;
    }

    public Integer getCurrWeek() {
        return currWeek;
    }

    public void setCurrWeek(Integer currWeek) {
        this.currWeek = currWeek;
    }
}
