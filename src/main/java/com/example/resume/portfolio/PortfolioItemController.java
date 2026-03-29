package com.example.resume.portfolio;

import com.example.resume.common.ApiResponse;
import com.example.resume.portfolio.dto.PortfolioItemRequest;
import com.example.resume.portfolio.dto.PortfolioItemResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 作品集 API / Portfolio API
 */
@RestController
@RequestMapping("/api/portfolio")
public class PortfolioItemController {

    private final PortfolioItemService service;

    public PortfolioItemController(PortfolioItemService service) {
        this.service = service;
    }

    /** 公共列表 / Public list */
    @GetMapping
    public ResponseEntity<ApiResponse<List<PortfolioItemResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.ok(service.listPublished()));
    }

    /** 管理员列表 / Admin list */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<PortfolioItemResponse>>> listAll() {
        return ResponseEntity.ok(ApiResponse.ok(service.listAll()));
    }

    /** 公共详情 / Public detail */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PortfolioItemResponse>> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.getById(id)));
    }

    /** 创建 / Create (ADMIN) */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PortfolioItemResponse>> create(@Valid @RequestBody PortfolioItemRequest req) {
        return ResponseEntity.status(201).body(ApiResponse.created(service.create(req)));
    }

    /** 更新 / Update (ADMIN) */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PortfolioItemResponse>> update(@PathVariable("id") Long id,
                                                                     @Valid @RequestBody PortfolioItemRequest req) {
        return ResponseEntity.ok(ApiResponse.ok(service.update(id, req)));
    }

    /** 删除 / Delete (ADMIN) */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
