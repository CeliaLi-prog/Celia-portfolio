package com.example.resume.homepage;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

/**
 * 首页配置实体 / Home page config entity
 */
@Entity
@Table(name = "home_page_config")
public class HomePageConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** JSON 配置内容 / JSON config content */
    @Column(nullable = false, columnDefinition = "text")
    private String contentJson;

    /** 更新时间 / Updated time */
    @Column(nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    public Long getId() { return id; }

    public String getContentJson() { return contentJson; }
    public void setContentJson(String contentJson) { this.contentJson = contentJson; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
