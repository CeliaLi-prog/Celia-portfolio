package com.example.resume.media;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

/**
 * 上传文件实体 / Uploaded media entity
 */
@Entity
@Table(name = "media_files")
public class MediaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 原始文件名 / Original filename */
    @Column(nullable = false, length = 300)
    private String originalName;

    /** 存储文件名 / Stored filename */
    @Column(nullable = false, length = 300)
    private String storedName;

    /** 可访问URL / Public URL */
    @Column(nullable = false, length = 600)
    private String publicUrl;

    /** 媒体类型 / Media type */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MediaType mediaType;

    /** 上传时间 / Uploaded time */
    @Column(nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Long getId() { return id; }

    public String getOriginalName() { return originalName; }
    public void setOriginalName(String originalName) { this.originalName = originalName; }

    public String getStoredName() { return storedName; }
    public void setStoredName(String storedName) { this.storedName = storedName; }

    public String getPublicUrl() { return publicUrl; }
    public void setPublicUrl(String publicUrl) { this.publicUrl = publicUrl; }

    public MediaType getMediaType() { return mediaType; }
    public void setMediaType(MediaType mediaType) { this.mediaType = mediaType; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
}
