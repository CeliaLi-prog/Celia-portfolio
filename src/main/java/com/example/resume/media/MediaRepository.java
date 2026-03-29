package com.example.resume.media;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Media repository / 媒体仓库
 */
public interface MediaRepository extends JpaRepository<MediaFile, Long> {}
