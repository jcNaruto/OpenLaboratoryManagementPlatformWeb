package com.jiachenzh.dao;

import com.jiachenzh.entity.UserActivityDO;
import com.jiachenzh.entity.UserActivityMultiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserActivityDao extends JpaRepository<UserActivityDO, UserActivityMultiKey>, JpaSpecificationExecutor<UserActivityDO> {

    /**
     * @Author
     * @Description 根据用户id查询所有的课程
     * @Date 11:01 2019/3/3
     * @Param [userId]
     * @throws
     * @return java.util.List<com.jiachenzh.entity.ActivityDO>
     */
    List<UserActivityDO> findByUserIdAndCompletedFlag(String userId, Integer completedFlag);

}
