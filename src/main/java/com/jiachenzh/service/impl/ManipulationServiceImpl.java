package com.jiachenzh.service.impl;

import com.jiachenzh.dao.ManipulationDao;
import com.jiachenzh.dao.ToolDao;
import com.jiachenzh.entity.ManipulationDO;
import com.jiachenzh.entity.ManipulationTO;
import com.jiachenzh.entity.ToolDO;
import com.jiachenzh.service.ManipulationSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ManipulationServiceImpl
 * @Description TODO
 * @Author
 * @Date 2019/3/9 21:21
 * @Version 1.0
 */
@Service
public class ManipulationServiceImpl implements ManipulationSerice {

    @Autowired
    private ManipulationDao manipulationDao;
    @Autowired
    private ToolDao toolDao;


    @Override
    public List<ManipulationTO> findAllByUserId(String userId) {
        List<ManipulationDO> manipulationDOList = manipulationDao.findByUserId(userId);
        List<ManipulationTO> manipulationTOList = new ArrayList<>();
        for(int i = 0; i < manipulationDOList.size(); i++){
            if(manipulationDOList.get(i).getBoxId() != 0){
                ToolDO toolDO = toolDao.findById(manipulationDOList.get(i).getBoxId()).get();
                manipulationTOList.add(new ManipulationTO(manipulationDOList.get(i), toolDO));
            } else {
                //人为规定boxId为0时代表0
                ToolDO door = new ToolDO();
                door.setName("门");
                manipulationTOList.add(new ManipulationTO(manipulationDOList.get(i), door));
            }
        }
        return manipulationTOList;
    }
}
