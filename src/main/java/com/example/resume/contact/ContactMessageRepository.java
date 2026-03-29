package com.example.resume.contact;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 联系消息存储 / Contact message repository
 */
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
