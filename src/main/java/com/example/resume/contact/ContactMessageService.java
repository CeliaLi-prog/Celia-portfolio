package com.example.resume.contact;

import com.example.resume.contact.dto.ContactMessageRequest;
import com.example.resume.contact.dto.ContactMessageResponse;
import org.springframework.stereotype.Service;

/**
 * 联系表单服务 / Contact form service
 */
@Service
public class ContactMessageService {

    private final ContactMessageRepository repository;

    public ContactMessageService(ContactMessageRepository repository) {
        this.repository = repository;
    }

    public ContactMessageResponse create(ContactMessageRequest req) {
        ContactMessage message = new ContactMessage();
        message.setName(req.getName());
        message.setEmail(req.getEmail());
        message.setSubject(req.getSubject());
        message.setMessage(req.getMessage());

        ContactMessage saved = repository.save(message);
        return new ContactMessageResponse(saved.getId(), saved.getCreatedAt());
    }
}
