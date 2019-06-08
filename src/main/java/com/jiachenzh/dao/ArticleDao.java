package com.jiachenzh.dao;


import com.jiachenzh.entity.ArticleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleDao extends JpaRepository<ArticleDO,Integer>, JpaSpecificationExecutor<ArticleDO> {



}
