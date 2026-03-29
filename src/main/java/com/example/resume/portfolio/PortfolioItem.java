package com.example.resume.portfolio;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

/**
 * 作品集实体 / Portfolio item entity
 */
@Entity
@Table(name = "portfolio_items")
public class PortfolioItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 标题 / Title */
    @Column(nullable = false, length = 120)
    private String title;

    /** 简短描述 / Summary */
    @Column(nullable = false, length = 500)
    private String summary;

    /** 分类 / Category (e.g. app/product/branding/books) */
    @Column(nullable = false, length = 50)
    private String category;

    /** 封面图 / Cover image URL */
    @Column(nullable = false, length = 500)
    private String coverImageUrl;

    /** 详情内容 / Detail content (HTML) */
    @Column(columnDefinition = "text")
    private String content;

    /** 预览链接 / Demo URL */
    @Column(length = 500)
    private String demoUrl;

    /** 仓库链接 / Repo URL */
    @Column(length = 500)
    private String repoUrl;

    /** 排序 / Sort order */
    @Column(nullable = false)
    private Integer sortOrder = 0;

    /** 是否发布 / Published */
    @Column(nullable = false)
    private boolean published = true;

    /** 创建时间 / Created time */
    @Column(nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getCoverImageUrl() { return coverImageUrl; }
    public void setCoverImageUrl(String coverImageUrl) { this.coverImageUrl = coverImageUrl; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getDemoUrl() { return demoUrl; }
    public void setDemoUrl(String demoUrl) { this.demoUrl = demoUrl; }

    public String getRepoUrl() { return repoUrl; }
    public void setRepoUrl(String repoUrl) { this.repoUrl = repoUrl; }

    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }

    public boolean isPublished() { return published; }
    public void setPublished(boolean published) { this.published = published; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
}
