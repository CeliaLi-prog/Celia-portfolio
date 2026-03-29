package com.example.resume.profile.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * 更新Profile请求 / Update profile request
 */
public class ProfileUpdateRequest {

    @NotBlank(message = "must not be blank / 不能为空")
    private String fullName;

    @NotBlank(message = "must not be blank / 不能为空")
    private String bio;

    @Email(message = "must be email / 必须是邮箱")
    @NotBlank(message = "must not be blank / 不能为空")
    private String email;

    private String avatarUrl;

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
}
