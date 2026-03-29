package com.example.resume.user.dto;

import com.example.resume.user.Role;
import java.time.OffsetDateTime;

/**
 * 用户返回DTO / User response DTO
 */
public class UserResponse {

    private Long id;
    private String username;
    private Role role;
    private OffsetDateTime createdAt;

    public UserResponse() {}

    public UserResponse(Long id, String username, Role role, OffsetDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
