package com.example.resume.user;

import com.example.resume.common.ApiResponse;
import com.example.resume.user.dto.UserCreateRequest;
import com.example.resume.user.dto.UserResponse;
import com.example.resume.user.dto.UserUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理接口（管理员）/ User admin API
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService,
                          UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /** 分页查询用户列表 / List users with pagination */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Page<UserResponse>>> list(Pageable pageable) {
        Page<UserResponse> page = userRepository.findAll(pageable)
                .map(u -> new UserResponse(u.getId(), u.getUsername(), u.getRole(), u.getCreatedAt()));
        return ResponseEntity.ok(ApiResponse.ok(page));
    }

    /** 创建用户（管理员）/ Create user (admin) */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody UserCreateRequest req) {
        User u = userService.createUser(req.getUsername(), req.getPassword(), Role.USER);
        UserResponse resp = new UserResponse(u.getId(), u.getUsername(), u.getRole(), u.getCreatedAt());
        return ResponseEntity.status(201).body(ApiResponse.created(resp));
    }

    /** 获取用户 / Get user */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> get(@PathVariable Long id) {
        User u = userService.getById(id);
        return ResponseEntity.ok(ApiResponse.ok(new UserResponse(u.getId(), u.getUsername(), u.getRole(), u.getCreatedAt())));
    }

    /** 更新角色 / Update role */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> update(@PathVariable Long id,
                                                           @Valid @RequestBody UserUpdateRequest req) {
        User u = userService.updateRole(id, req.getRole());
        return ResponseEntity.ok(ApiResponse.ok(new UserResponse(u.getId(), u.getUsername(), u.getRole(), u.getCreatedAt())));
    }

    /** 删除用户 / Delete user */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
