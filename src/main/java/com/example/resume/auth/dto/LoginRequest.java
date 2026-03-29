package com.example.resume.auth.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * 登录请求 / Login request
 */
public class LoginRequest {

    @NotBlank(message = "must not be blank / 不能为空")
    private String username;

    @NotBlank(message = "must not be blank / 不能为空")
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
