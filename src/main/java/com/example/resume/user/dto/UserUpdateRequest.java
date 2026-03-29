package com.example.resume.user.dto;

import com.example.resume.user.Role;
import jakarta.validation.constraints.NotNull;

/**
 * 更新用户请求（示例：更新角色）/ Update user request (example: update role)
 */
public class UserUpdateRequest {

    @NotNull(message = "must not be null / 不能为空")
    private Role role;

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
