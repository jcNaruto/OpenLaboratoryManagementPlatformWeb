package com.jiachenzh.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @ClassName ArticleDO
 * @Description TODO
 * @Author
 * @Date 2019/3/3 10:22
 * @Version 1.0
 */
@Entity
@Table(name="article_channel")
public class ArticleDO {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer articleId;
    @NotNull(message = "文章标题不能为null")
    private String title;
    @NotNull(message = "作者名称不能为null")
    private String authorName;
    @NotNull(message = "文章描述不能为null")
    private String description;
    @NotNull(message = "文章链接不能为null")
    private String articleLink;
    @NotNull(message = "作者链接不能为null")
    private String authorLink;
    @Column(name = "is_abled")
    private Integer abledFlag;
    @NotNull(message = "文章频道不能为null")
    private Integer channelId;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    public String getAuthorLink() {
        return authorLink;
    }

    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    public Integer getAbledFlag() {
        return abledFlag;
    }

    public void setAbledFlag(Integer abledFlag) {
        this.abledFlag = abledFlag;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "ArticleDO{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", description='" + description + '\'' +
                ", articleLink='" + articleLink + '\'' +
                ", authorLink='" + authorLink + '\'' +
                ", abledFlag=" + abledFlag +
                ", channelId=" + channelId +
                '}';
    }
}
