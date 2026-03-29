package com.example.resume.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 服务项仓库 / Service item repository
 */
public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {
    List<ServiceItem> findAllByPublishedTrueOrderBySortOrderAscIdAsc();
    List<ServiceItem> findAllByOrderBySortOrderAscIdAsc();
}
