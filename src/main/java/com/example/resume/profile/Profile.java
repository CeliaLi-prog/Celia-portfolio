package com.example.resume.profile;

import jakarta.persistence.*;

/**
 * 简历/个人介绍实体 / Resume profile entity
 */
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 姓名 / Full name */
    @Column(nullable = false, length = 100)
    private String fullName;

    /** 个人简介（可Markdown）/ Bio (can be Markdown) */
    @Column(nullable = false, columnDefinition = "text")
    private String bio;

    /** 邮箱 / Email */
    @Column(nullable = false, length = 100)
    private String email;

    /** 头像URL / Avatar URL */
    @Column(length = 500)
    private String avatarUrl;

    @Column(length = 200)
    private String title;

    @Column(length = 300)
    private String tagline;

    public Long getId() { return id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
}
