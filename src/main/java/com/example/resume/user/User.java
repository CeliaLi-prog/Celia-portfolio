package com.example.resume.user;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

/**
 * 用户实体 / User entity
 */
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "uk_users_username", columnNames = "username")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 用户名（唯一）/ Unique username */
    @Column(nullable = false, length = 50)
    private String username;

    /** 密码（加密存储）/ Encrypted password hash */
    @Column(nullable = false, length = 200)
    private String passwordHash;

    /** 角色 / Role */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role = Role.USER;

    /** 创建时间 / Created time */
    @Column(nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
}
