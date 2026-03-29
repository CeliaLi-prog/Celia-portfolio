package com.example.resume.auth;

import com.example.resume.auth.dto.AuthResponse;
import com.example.resume.auth.dto.LoginRequest;
import com.example.resume.auth.dto.RegisterRequest;
import com.example.resume.common.ApiResponse;
import com.example.resume.common.BusinessException;
import com.example.resume.common.ErrorCode;
import com.example.resume.config.JwtService;
import com.example.resume.config.TokenBlacklistService;
import com.example.resume.user.Role;
import com.example.resume.user.User;
import com.example.resume.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

/**
 * 注册/登录/注销 / Register, login, logout
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          TokenBlacklistService tokenBlacklistService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    /**
     * 注册 / Register
     * 默认创建 USER 角色 / Default role is USER
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterRequest req) {
        userService.createUser(req.getUsername(), req.getPassword(), Role.USER);
        return ResponseEntity.status(CREATED).body(ApiResponse.created(null));
    }

    /**
     * 登录 / Login
     * 成功返回 JWT / Return JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        // 登录成功后生成token / Generate token after successful login
        User u = userService.getByUsername(req.getUsername());
        String token = jwtService.generateToken(u.getUsername(), u.getRole().name());

        return ResponseEntity.ok(ApiResponse.ok(new AuthResponse(token)));
    }

    /**
     * 注销 / Logout
     * 说明：
     * - JWT无状态，最简单是前端删除token
     * - 这里演示：把token加入黑名单 / Demo: add token to blacklist
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, UNAUTHORIZED.value(), "Missing token / 缺少token");
        }
        String token = authHeader.substring(7);
        tokenBlacklistService.blacklist(token);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

}
