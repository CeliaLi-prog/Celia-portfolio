package com.example.resume.portfolio.dto;

import java.time.OffsetDateTime;

/**
 * 作品集返回 / Portfolio response
 */
public class PortfolioItemResponse {

    private Long id;
    private String title;
    private String summary;
    private String category;
    private String coverImageUrl;
    private String content;
    private String demoUrl;
    private String repoUrl;
    private Integer sortOrder;
    private boolean published;
    private OffsetDateTime createdAt;

    public PortfolioItemResponse() {}

    public PortfolioItemResponse(Long id, String title, String summary, String category, String coverImageUrl,
                                 String content, String demoUrl, String repoUrl, Integer sortOrder,
                                 boolean published, OffsetDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.category = category;
        this.coverImageUrl = coverImageUrl;
        this.content = content;
        this.demoUrl = demoUrl;
        this.repoUrl = repoUrl;
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
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
