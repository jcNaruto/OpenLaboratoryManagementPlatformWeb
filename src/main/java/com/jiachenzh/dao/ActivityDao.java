package com.jiachenzh.dao;


import com.jiachenzh.entity.ActivityDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActivityDao extends JpaRepository<ActivityDO,Integer>, JpaSpecificationExecutor<ActivityDO> {

    /**
     * @Author
     * @Description 根据id查找返回值只有一个实例对象
     * TODO 重构
     * @Date 20:06 2019/3/4
     * @Param [activityId]
     * @throws
     * @return com.jiachenzh.entity.ActivityDO
     */
    ActivityDO findByActivityId(Integer activityId);

    /**
     * @Author
     * @Description 根据传入的参数查询开放的activity
     * @Date 14:10 2019/3/4
     * @Param [abledFlag]
     * @throws
     * @return java.util.List<com.jiachenzh.entity.ActivityDO>
     */
    List<ActivityDO> findByAbledFlag(Integer abledFlag);

}
