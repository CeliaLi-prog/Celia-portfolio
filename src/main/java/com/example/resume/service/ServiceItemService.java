package com.example.resume.service;

import com.example.resume.common.BusinessException;
import com.example.resume.common.ErrorCode;
import com.example.resume.service.dto.ServiceItemRequest;
import com.example.resume.service.dto.ServiceItemResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * 服务项业务 / Service item service
 */
@Service
public class ServiceItemService {

    private final ServiceItemRepository repository;

    public ServiceItemService(ServiceItemRepository repository) {
        this.repository = repository;
    }

    public List<ServiceItemResponse> listPublished() {
        return repository.findAllByPublishedTrueOrderBySortOrderAscIdAsc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ServiceItemResponse> listAll() {
        return repository.findAllByOrderBySortOrderAscIdAsc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ServiceItemResponse getById(Long id) {
        ServiceItem item = repository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.SERVICE_NOT_FOUND, NOT_FOUND.value(), "Service not found"));
        return toResponse(item);
    }

    @Transactional
    public ServiceItemResponse create(ServiceItemRequest req) {
        ServiceItem item = new ServiceItem();
        apply(item, req);
        return toResponse(repository.save(item));
    }

    @Transactional
    public ServiceItemResponse update(Long id, ServiceItemRequest req) {
        ServiceItem item = repository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.SERVICE_NOT_FOUND, NOT_FOUND.value(), "Service not found"));
        apply(item, req);
        return toResponse(item);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(ErrorCode.SERVICE_NOT_FOUND, NOT_FOUND.value(), "Service not found");
        }
        repository.deleteById(id);
    }

    private void apply(ServiceItem item, ServiceItemRequest req) {
        item.setTitle(req.getTitle());
        item.setSummary(req.getSummary());
        item.setIconClass(req.getIconClass());
        item.setImageUrl(req.getImageUrl());
        item.setContent(req.getContent());
        item.setSortOrder(req.getSortOrder());
        item.setPublished(req.getPublished() == null || req.getPublished());
    }

    private ServiceItemResponse toResponse(ServiceItem item) {
        return new ServiceItemResponse(
                item.getId(),
                item.getTitle(),
                item.getSummary(),
                item.getIconClass(),
                item.getImageUrl(),
                item.getContent(),
                item.getSortOrder(),
                item.isPublished(),
                item.getCreatedAt()
        );
    }
}
