package com.jiachenzh.service;

import com.jiachenzh.entity.ActivityDO;
import com.jiachenzh.entity.UserActivityDO;
import com.jiachenzh.entity.UserActivityTo;

import java.util.List;

public interface UserActivityService {

    void insertOneUserActivity(UserActivityDO userActivityDO);

    List<UserActivityTo> listFindMyActivity(String userId);
}
