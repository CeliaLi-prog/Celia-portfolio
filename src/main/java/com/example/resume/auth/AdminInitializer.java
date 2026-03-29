package com.example.resume.auth;

import com.example.resume.user.Role;
import com.example.resume.user.UserRepository;
import com.example.resume.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * 初始化管理员账号（可选）/ Optional admin initialization
 */
@Configuration
public class AdminInitializer {

    private static final Logger log = LoggerFactory.getLogger(AdminInitializer.class);

    @Value("${app.init-admin.enabled:false}")
    private boolean enabled;

    @Value("${app.init-admin.username:admin}")
    private String username;

    @Value("${app.init-admin.password:}")
    private String password;

    @Bean
    public ApplicationRunner initAdmin(UserService userService, UserRepository userRepository) {
        return args -> {
            if (!enabled) {
                return;
            }
            if (!StringUtils.hasText(password)) {
                throw new IllegalStateException("app.init-admin.password must be set when enabled");
            }
            if (userRepository.existsByUsername(username)) {
                log.info("Init admin skipped: user already exists.");
                return;
            }
            userService.createUser(username, password, Role.ADMIN);
            log.info("Init admin created: {}", username);
        };
    }
}
