package com.example.resume.portfolio;

import com.example.resume.common.BusinessException;
import com.example.resume.common.ErrorCode;
import com.example.resume.portfolio.dto.PortfolioItemRequest;
import com.example.resume.portfolio.dto.PortfolioItemResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * 作品集业务 / Portfolio service
 */
@Service
public class PortfolioItemService {

    private final PortfolioItemRepository repository;

    public PortfolioItemService(PortfolioItemRepository repository) {
        this.repository = repository;
    }

    public List<PortfolioItemResponse> listPublished() {
        return repository.findAllByPublishedTrueOrderBySortOrderAscIdAsc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<PortfolioItemResponse> listAll() {
        return repository.findAllByOrderBySortOrderAscIdAsc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PortfolioItemResponse getById(Long id) {
        PortfolioItem item = repository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.PORTFOLIO_NOT_FOUND, NOT_FOUND.value(), "Portfolio not found"));
        return toResponse(item);
    }

    @Transactional
    public PortfolioItemResponse create(PortfolioItemRequest req) {
        PortfolioItem item = new PortfolioItem();
        apply(item, req);
        return toResponse(repository.save(item));
    }

    @Transactional
    public PortfolioItemResponse update(Long id, PortfolioItemRequest req) {
        PortfolioItem item = repository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.PORTFOLIO_NOT_FOUND, NOT_FOUND.value(), "Portfolio not found"));
        apply(item, req);
        return toResponse(item);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(ErrorCode.PORTFOLIO_NOT_FOUND, NOT_FOUND.value(), "Portfolio not found");
        }
        repository.deleteById(id);
    }

    private void apply(PortfolioItem item, PortfolioItemRequest req) {
        item.setTitle(req.getTitle());
        item.setSummary(req.getSummary());
        item.setCategory(req.getCategory());
        item.setCoverImageUrl(req.getCoverImageUrl());
        item.setContent(req.getContent());
        item.setDemoUrl(req.getDemoUrl());
        item.setRepoUrl(req.getRepoUrl());
        item.setSortOrder(req.getSortOrder());
        item.setPublished(req.getPublished() == null || req.getPublished());
    }

    private PortfolioItemResponse toResponse(PortfolioItem item) {
        return new PortfolioItemResponse(
                item.getId(),
                item.getTitle(),
                item.getSummary(),
                item.getCategory(),
                item.getCoverImageUrl(),
                item.getContent(),
                item.getDemoUrl(),
                item.getRepoUrl(),
                item.getSortOrder(),
                item.isPublished(),
                item.getCreatedAt()
        );
    }
}
