package com.jiachenzh.service.impl;

import com.jiachenzh.dao.WeekMaintenanceDao;
import com.jiachenzh.entity.WeekMaintenanceDO;
import com.jiachenzh.exception.LaboratoryException;
import com.jiachenzh.service.MaintenanceService;
import com.jiachenzh.util.QRCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName MaintenanceServiceImpl
 * @Description TODO
 * @Author
 * @Date 2019/3/7 19:39
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private WeekMaintenanceDao weekMaintenanceDao;


    /**
     * @Author
     * @Description 系统维护，每次放假期间进行维护，插入下一此的开学放假时间
     * @Date 19:51 2019/3/7
     * @Param [weekMaintenanceDO]
     * @throws
     * @return void
     */
    @Override
    public void insertweekMaintenance(WeekMaintenanceDO weekMaintenanceDO) {
        //利用QRCodeUtils中的getUUID生成32位的随机码
        weekMaintenanceDO.setQueryKey(QRCodeUtils.getUUID());
        WeekMaintenanceDO save = weekMaintenanceDao.save(weekMaintenanceDO);
        if(save == null){
            throw new LaboratoryException("系统维护失败：开学，放假时间插入错误");
        }
    }










}
