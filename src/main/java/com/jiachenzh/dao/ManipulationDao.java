package com.jiachenzh.dao;


import com.jiachenzh.entity.ManipulationDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.validation.constraints.Null;
import java.util.List;

public interface ManipulationDao extends JpaRepository<ManipulationDO,Integer>, JpaSpecificationExecutor<ManipulationDO> {

    List<ManipulationDO> findByUserId(String userId);



}
