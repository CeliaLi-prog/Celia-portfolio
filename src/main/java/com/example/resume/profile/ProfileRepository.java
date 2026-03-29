package com.example.resume.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
/**
 * Profile repository / 简历仓库
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    /**
     * 获取最新/唯一的 Profile（按 id 最大取一条）
     * Get the latest profile (by max id)
     */
    Optional<Profile> findTopByOrderByIdDesc();
}
