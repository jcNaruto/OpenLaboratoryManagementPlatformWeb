package com.jiachenzh.dao;


import com.jiachenzh.entity.ArticleChannelDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleChannelDao extends JpaRepository<ArticleChannelDO,Integer>, JpaSpecificationExecutor<ArticleChannelDO> {



}
