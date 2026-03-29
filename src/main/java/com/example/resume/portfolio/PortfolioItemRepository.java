package com.example.resume.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 作品集仓库 / Portfolio repository
 */
public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
    List<PortfolioItem> findAllByPublishedTrueOrderBySortOrderAscIdAsc();
    List<PortfolioItem> findAllByOrderBySortOrderAscIdAsc();
}
