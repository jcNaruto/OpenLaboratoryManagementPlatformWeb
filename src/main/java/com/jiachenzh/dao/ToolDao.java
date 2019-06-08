package com.jiachenzh.dao;


import com.jiachenzh.entity.ToolDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ToolDao extends JpaRepository<ToolDO,Integer>, JpaSpecificationExecutor<ToolDO> {

    List<ToolDO> findByBorrowedFlag(Integer borrowedFlag);

    /**
     * @Author
     * @Description 借用或者归还物品的sql
     * @Date 20:20 2019/3/9
     * @Param [borrowedFlag, id]
     * @throws
     * @return java.lang.Integer
     */
    @Modifying
    @Query("update ToolDO t set t.borrowedFlag=?1,t.updateTime=?2 where t.boxId=?3")
    Integer modifyBorrowedFlagByBoxId(Integer borrowedFlag, Date updateDate, Integer id);

}
