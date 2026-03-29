package com.example.resume.service.dto;

import java.time.OffsetDateTime;

/**
 * 服务项返回 / Service item response
 */
public class ServiceItemResponse {

    private Long id;
    private String title;
    private String summary;
    private String iconClass;
    private String imageUrl;
    private String content;
    private Integer sortOrder;
    private boolean published;
    private OffsetDateTime createdAt;

    public ServiceItemResponse() {}

    public ServiceItemResponse(Long id, String title, String summary, String iconClass, String imageUrl, String content,
                               Integer sortOrder, boolean published, OffsetDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.iconClass = iconClass;
        this.imageUrl = imageUrl;
        this.content = content;
        this.sortOrder = sortOrder;
        this.published = published;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getIconClass() { return iconClass; }
    public void setIconClass(String iconClass) { this.iconClass = iconClass; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }

    public boolean isPublished() { return published; }
    public void setPublished(boolean published) { this.published = published; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
