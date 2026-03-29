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
 * 初始化演示账号（只读）/ Optional demo user initialization (read-only)
 */
@Configuration
public class DemoUserInitializer {

    private static final Logger log = LoggerFactory.getLogger(DemoUserInitializer.class);

    @Value("${app.demo-user.enabled:false}")
    private boolean enabled;

    @Value("${app.demo-user.username:demo}")
    private String username;

    @Value("${app.demo-user.password:}")
    private String password;

    @Bean
    public ApplicationRunner initDemoUser(UserService userService, UserRepository userRepository) {
        return args -> {
            if (!enabled) {
                return;
            }
            if (!StringUtils.hasText(password)) {
                throw new IllegalStateException("app.demo-user.password must be set when enabled");
            }
            if (userRepository.existsByUsername(username)) {
                log.info("Init demo user skipped: user already exists.");
                return;
            }
            userService.createUser(username, password, Role.USER);
            log.info("Init demo user created: {}", username);
        };
    }
}
