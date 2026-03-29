package com.example.resume.service;

import com.example.resume.common.ApiResponse;
import com.example.resume.service.dto.ServiceItemRequest;
import com.example.resume.service.dto.ServiceItemResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 服务项 API / Service item API
 */
@RestController
@RequestMapping("/api/services")
public class ServiceItemController {

    private final ServiceItemService service;

    public ServiceItemController(ServiceItemService service) {
        this.service = service;
    }

    /** 公共列表 / Public list */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ServiceItemResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.ok(service.listPublished()));
    }

    /** 管理员列表 / Admin list */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<ServiceItemResponse>>> listAll() {
        return ResponseEntity.ok(ApiResponse.ok(service.listAll()));
    }

    /** 公共详情 / Public detail */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceItemResponse>> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.getById(id)));
    }

    /** 创建 / Create (ADMIN) */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ServiceItemResponse>> create(@Valid @RequestBody ServiceItemRequest req) {
        return ResponseEntity.status(201).body(ApiResponse.created(service.create(req)));
    }

    /** 更新 / Update (ADMIN) */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ServiceItemResponse>> update(@PathVariable("id") Long id,
                                                                   @Valid @RequestBody ServiceItemRequest req) {
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
