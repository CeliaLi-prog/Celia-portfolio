package com.example.resume.user;

import com.example.resume.common.BusinessException;
import com.example.resume.common.ErrorCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.http.HttpStatus.*;

/**
 * 用户业务层 / User service layer
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(String username, String rawPassword, Role role) {
        if (userRepository.existsByUsername(username)) {
            throw new BusinessException(
                    ErrorCode.USERNAME_ALREADY_EXISTS,
                    CONFLICT.value(),
                    "Username already exists / 用户名已存在"
            );
        }

        User u = new User();
        u.setUsername(username);
        u.setPasswordHash(passwordEncoder.encode(rawPassword));
        u.setRole(role);

        return userRepository.save(u);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_FOUND, NOT_FOUND.value(),
                        "User not found / 用户不存在"));
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_FOUND, NOT_FOUND.value(),
                        "User not found / 用户不存在"));
    }

    @Transactional
    public User updateRole(Long id, Role role) {
        User u = getById(id);
        u.setRole(role);
        return u;
    }

    @Transactional
    public void delete(Long id) {
        User u = getById(id);
        userRepository.delete(u);
    }
}
