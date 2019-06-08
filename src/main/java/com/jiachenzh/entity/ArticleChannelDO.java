package com.jiachenzh.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * @ClassName ArticleChannelDO
 * @Description TODO
 * @Author
 * @Date 2019/3/3 10:23
 * @Version 1.0
 */
@Entity
@Table(name="article_channel")
public class ArticleChannelDO {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer channelId;
    @NotNull(message = "文章频道不能为空")
    private String name;
    @Column(name = "is_abled")
    private Integer abledFlag;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAbledFlag() {
        return abledFlag;
    }

    public void setAbledFlag(Integer abledFlag) {
        this.abledFlag = abledFlag;
    }

    @Override
    public String toString() {
        return "ArticleChannelDO{" +
                "channelId=" + channelId +
                ", name='" + name + '\'' +
                ", abledFlag=" + abledFlag +
                '}';
    }
}
