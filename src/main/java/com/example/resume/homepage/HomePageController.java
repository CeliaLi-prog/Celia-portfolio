package com.example.resume.homepage;

import com.example.resume.common.ApiResponse;
import com.example.resume.homepage.dto.HomePagePayload;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 首页内容配置 API / Home page content API
 */
@RestController
@RequestMapping("/api/homepage")
public class HomePageController {

    private final HomePageConfigService service;

    public HomePageController(HomePageConfigService service) {
        this.service = service;
    }

    /** 获取首页配置（公开）/ Get home page config (public) */
    @GetMapping
    public ResponseEntity<ApiResponse<HomePagePayload>> get() {
        return ResponseEntity.ok(ApiResponse.ok(service.getOrInitPayload()));
    }

    /** 保存首页配置（管理员）/ Save home page config (ADMIN) */
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<HomePagePayload>> save(@RequestBody HomePagePayload payload) {
        HomePagePayload saved = service.save(payload);
        return ResponseEntity.ok(ApiResponse.ok(saved));
    }
}
