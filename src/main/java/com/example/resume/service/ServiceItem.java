package com.example.resume.service;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

/**
 * 服务项实体 / Service item entity
 */
@Entity
@Table(name = "services")
public class ServiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 标题 / Title */
    @Column(nullable = false, length = 120)
    private String title;

    /** 简短描述 / Short description */
    @Column(nullable = false, length = 500)
    private String summary;

    /** 图标类名 / Icon class (e.g. "bi bi-briefcase") */
    @Column(nullable = false, length = 120)
    private String iconClass;

    /** 详情图 / Detail image URL */
    @Column(length = 500)
    private String imageUrl;

    /** 详情内容 / Detail content */
    @Column(columnDefinition = "text")
    private String content;

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

    public String getIconClass() { return iconClass; }
    public void setIconClass(String iconClass) { this.iconClass = iconClass; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }

    public boolean isPublished() { return published; }
    public void setPublished(boolean published) { this.published = published; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
}
