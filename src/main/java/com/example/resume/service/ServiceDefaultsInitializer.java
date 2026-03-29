package com.example.resume.service;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 默认服务初始化 / Seed default services when empty
 */
@Configuration
public class ServiceDefaultsInitializer {

    @Bean
    public ApplicationRunner initDefaultServices(ServiceItemRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                return;
            }

            List<ServiceItem> defaults = List.of(
                    build(0, "bi bi-briefcase", "Lorem Ipsum",
                            "Voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident"),
                    build(1, "bi bi-card-checklist", "Dolor Sitema",
                            "Minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat tarad limino ata"),
                    build(2, "bi bi-bar-chart", "Sed ut perspiciatis",
                            "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur"),
                    build(3, "bi bi-binoculars", "Magni Dolores",
                            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"),
                    build(4, "bi bi-brightness-high", "Nemo Enim",
                            "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque"),
                    build(5, "bi bi-calendar4-week", "Eiusmod Tempor",
                            "Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi")
            );

            repository.saveAll(defaults);
        };
    }

    private ServiceItem build(int sort, String icon, String title, String summary) {
        ServiceItem item = new ServiceItem();
        item.setSortOrder(sort);
        item.setIconClass(icon);
        item.setTitle(title);
        item.setSummary(summary);
        item.setPublished(true);
        item.setContent("");
        item.setImageUrl("");
        return item;
    }
}
