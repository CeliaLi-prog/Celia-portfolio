package com.example.resume.homepage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 首页配置仓库 / Home page config repository
 */
public interface HomePageConfigRepository extends JpaRepository<HomePageConfig, Long> {
    Optional<HomePageConfig> findTopByOrderByIdDesc();
}
