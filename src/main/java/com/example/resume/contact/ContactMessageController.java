package com.example.resume.contact;

import com.example.resume.common.ApiResponse;
import com.example.resume.contact.dto.ContactMessageRequest;
import com.example.resume.contact.dto.ContactMessageResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 联系表单 API / Contact form API
 */
@RestController
@RequestMapping("/api/contact")
public class ContactMessageController {

    private final ContactMessageService service;

    public ContactMessageController(ContactMessageService service) {
        this.service = service;
    }

    /** 提交联系表单 / Submit contact form */
    @PostMapping
    public ResponseEntity<ApiResponse<ContactMessageResponse>> create(@Valid @RequestBody ContactMessageRequest req) {
        return ResponseEntity.status(201).body(ApiResponse.created(service.create(req)));
    }
}
