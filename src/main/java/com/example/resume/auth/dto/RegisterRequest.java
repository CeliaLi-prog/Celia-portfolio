package com.example.resume.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 注册请求 / Register request
 */
public class RegisterRequest {

    @NotBlank(message = "must not be blank / 不能为空")
    @Size(min = 3, max = 50, message = "length 3-50 / 长度3-50")
    private String username;

    @NotBlank(message = "must not be blank / 不能为空")
    @Size(min = 6, max = 100, message = "length 6-100 / 长度6-100")
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
