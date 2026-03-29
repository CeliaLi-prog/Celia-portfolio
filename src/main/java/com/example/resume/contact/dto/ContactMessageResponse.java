package com.example.resume.contact.dto;

import java.time.OffsetDateTime;

/**
 * 联系表单响应 / Contact form response
 */
public class ContactMessageResponse {

    private Long id;
    private OffsetDateTime createdAt;

    public ContactMessageResponse(Long id, OffsetDateTime createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
}
