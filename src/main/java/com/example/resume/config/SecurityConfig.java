package com.example.resume.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 安全配置 / Spring Security configuration
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    // 静态页面放行 / Allow static pages
                    .requestMatchers("/", "/index.html", "/cms.html", "/assets/**","/forms/**", "/favicon.ico","/*.html").permitAll()
                    // 上传文件公开访问 / Allow uploaded files to be served
                    .requestMatchers("/uploads/**").permitAll()
                    //公开API放行
                    .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/profile").permitAll()
                    .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/profile").hasRole("ADMIN") // 或 authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/homepage").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/homepage").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET,"/api/profiles/**").permitAll()
                    //projects/skills放行
                    .requestMatchers(HttpMethod.GET,"/api/projects/**").permitAll()
                    .requestMatchers(HttpMethod.GET,"/api/skills/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/portfolio/admin").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/portfolio/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/services/admin").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/services/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/contact").permitAll()

                    // Auth登录注册 API放行 / Allow auth endpoints
                    .requestMatchers("/api/auth/**").permitAll()
                    // Profile GET 放行 / Allow public profile read
                    .requestMatchers(HttpMethod.GET, "/api/profiles/**").permitAll()
                    // 其他需要认证 / Others require authentication
                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /** 密码加密器 / Password encoder */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** AuthenticationManager / 认证管理器 */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
