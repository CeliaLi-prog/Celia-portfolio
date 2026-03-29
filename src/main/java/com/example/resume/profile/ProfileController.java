package com.example.resume.profile;

import com.example.resume.common.ApiResponse;
import com.example.resume.common.BusinessException;
import com.example.resume.common.ErrorCode;
import com.example.resume.profile.dto.ProfileUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * 简历接口 / Profile API
 * - GET 公开 / public
 * - 写操作建议 ADMIN / write operations are ADMIN-only
 */
@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileRepository profileRepository;

    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    /** 获取Profile / Get profile by id (public) */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Profile>> get(@PathVariable("id") Long id) {
        Profile p = profileRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.PROFILE_NOT_FOUND, NOT_FOUND.value(),
                        "Profile not found / 简历不存在"));
        return ResponseEntity.ok(ApiResponse.ok(p));
    }

    /** 创建Profile（ADMIN）/ Create profile (ADMIN) */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Profile>> create(@Valid @RequestBody ProfileUpdateRequest req) {
        Profile p = new Profile();
        p.setFullName(req.getFullName());
        p.setBio(req.getBio());
        p.setEmail(req.getEmail());
        p.setAvatarUrl(req.getAvatarUrl());
        Profile saved = profileRepository.save(p);
        return ResponseEntity.status(201).body(ApiResponse.created(saved));
    }

    /** 更新Profile（ADMIN）/ Update profile (ADMIN) */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Profile>> update(@PathVariable("id") Long id,
                                                      @Valid @RequestBody ProfileUpdateRequest req) {
        Profile existing = profileRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.PROFILE_NOT_FOUND, NOT_FOUND.value(),
                        "Profile not found / 简历不存在"));

        existing.setFullName(req.getFullName());
        existing.setBio(req.getBio());
        existing.setEmail(req.getEmail());
        existing.setAvatarUrl(req.getAvatarUrl());

        Profile saved = profileRepository.save(existing);
        return ResponseEntity.ok(ApiResponse.ok(saved));
    }

    /** 删除Profile（ADMIN）/ Delete profile (ADMIN) */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!profileRepository.existsById(id)) {
            throw new BusinessException(ErrorCode.PROFILE_NOT_FOUND, NOT_FOUND.value(),
                    "Profile not found / 简历不存在");
        }
        profileRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
