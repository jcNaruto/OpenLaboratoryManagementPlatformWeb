package com.jiachenzh.service;

import com.jiachenzh.entity.ManipulationDO;
import com.jiachenzh.entity.ManipulationTO;

import java.util.List;

public interface ManipulationSerice {

    List<ManipulationTO> findAllByUserId(String userId);
}
