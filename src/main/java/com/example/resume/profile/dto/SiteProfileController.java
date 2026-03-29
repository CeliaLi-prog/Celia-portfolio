package com.example.resume.profile;

import com.example.resume.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 站点 Profile 单资源接口（更规范）
 * Site profile single-resource API (more standard than /api/profiles/{id})
 */
@RestController
@RequestMapping("/api/profile")
public class SiteProfileController {

    private final ProfileRepository profileRepository;

    public SiteProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /**
     * 获取站点 Profile（公开）
     * Get site profile (public)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Profile>> get() {
        return profileRepository.findTopByOrderByIdDesc()
                .map(p -> ResponseEntity.ok(ApiResponse.ok(p)))
                .orElseGet(() -> ResponseEntity.status(404)
                        .body(ApiResponse.fail("PROFILE_NOT_FOUND", "Profile not initialized / 资料未初始化")));
    }

    /**
     * 更新站点 Profile：存在则更新最新一条，不存在则创建（upsert）
     * Update site profile: update latest or create if missing (upsert)
     */
    @PutMapping
    public ResponseEntity<ApiResponse<Profile>> upsert(@RequestBody Profile req) {

        Profile target = profileRepository.findTopByOrderByIdDesc().orElseGet(Profile::new);

        target.setFullName(req.getFullName());
        target.setTitle(req.getTitle());
        target.setTagline(req.getTagline());
        target.setEmail(req.getEmail());
        target.setAvatarUrl(req.getAvatarUrl());
        target.setBio(req.getBio());

        Profile saved = profileRepository.save(target);
        return ResponseEntity.ok(ApiResponse.ok(saved));
    }
}