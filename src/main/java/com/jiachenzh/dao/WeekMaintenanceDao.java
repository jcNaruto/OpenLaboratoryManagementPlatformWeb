package com.jiachenzh.dao;

import com.jiachenzh.entity.WeekMaintenanceDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WeekMaintenanceDao extends JpaRepository<WeekMaintenanceDO,Integer>, JpaSpecificationExecutor<WeekMaintenanceDO> {

   /**
    * @Author
    * @Description 根据 queryKey查询WeekMaintenanceDO，以便获取开学时间和放假时间
    * @Date 19:53 2019/3/7
    * @Param [QueryKey]
    * @throws
    * @return com.jiachenzh.entity.WeekMaintenanceDO
    */
   public WeekMaintenanceDO findByQueryKey(String queryKey);

}
