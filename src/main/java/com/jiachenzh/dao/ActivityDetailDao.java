package com.jiachenzh.dao;

import com.jiachenzh.entity.ActivityDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ActivityDetailDao extends JpaRepository<ActivityDetailDO,Integer>, JpaSpecificationExecutor<ActivityDetailDO> {


    /**
     * @Author Jiacheng
     * @Description 根据activityId查询课程详情
     * @Date 23:41 2019/6/7
     * @Param [activityId] activityId
     * @throws
     * @return java.util.List<com.jiachenzh.entity.ActivityDetailDO>
     */
    List<ActivityDetailDO> findByActivityId(Integer activityId);

}
