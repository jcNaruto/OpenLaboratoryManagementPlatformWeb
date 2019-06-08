package com.jiachenzh.service;

public interface DoorService {
    /**
     * @Author Jiacheng
     * @Description 请求生成用于开门操作的二维码
     * @Date 23:44 2019/6/7
     * @Param [userId] userId
     * @throws
     * @return java.lang.String
     */
    String openDoor(String userId);
}
