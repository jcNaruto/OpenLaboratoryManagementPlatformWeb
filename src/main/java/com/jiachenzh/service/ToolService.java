package com.jiachenzh.service;

import com.jiachenzh.entity.ToolDO;

import java.util.List;

public interface ToolService {

    List<ToolDO> listFindAll(Integer borrowedFlag);

    void insertTool(ToolDO toolDO);

    String borrowOrLend(ToolDO toolDO, String userId);
}
