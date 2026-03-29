package com.example.resume.contact;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

/**
 * 联系表单消息 / Contact form message
 */
@Entity
@Table(name = "contact_messages")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 姓名 / Name */
    @Column(nullable = false, length = 120)
    private String name;

    /** 邮箱 / Email */
    @Column(nullable = false, length = 200)
    private String email;

    /** 主题 / Subject */
    @Column(nullable = false, length = 200)
    private String subject;

    /** 内容 / Message */
    @Column(nullable = false, columnDefinition = "text")
    private String message;

    /** 创建时间 / Created time */
    @Column(nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
