package com.example.resume.portfolio;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 默认作品集初始化 / Seed default portfolio items when empty
 */
@Configuration
public class PortfolioDefaultsInitializer {

    @Bean
    public ApplicationRunner initDefaultPortfolio(PortfolioItemRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                return;
            }

            List<PortfolioItem> defaults = List.of(
                    build(0, "App 1", "Lorem ipsum, dolor sit amet consectetur", "app", "assets/img/portfolio/app-1.jpg"),
                    build(1, "Product 1", "Lorem ipsum, dolor sit amet consectetur", "product", "assets/img/portfolio/product-1.jpg"),
                    build(2, "Branding 1", "Lorem ipsum, dolor sit amet consectetur", "branding", "assets/img/portfolio/branding-1.jpg"),
                    build(3, "Books 1", "Lorem ipsum, dolor sit amet consectetur", "books", "assets/img/portfolio/books-1.jpg"),
                    build(4, "App 2", "Lorem ipsum, dolor sit amet consectetur", "app", "assets/img/portfolio/app-2.jpg"),
                    build(5, "Product 2", "Lorem ipsum, dolor sit amet consectetur", "product", "assets/img/portfolio/product-2.jpg"),
                    build(6, "Branding 2", "Lorem ipsum, dolor sit amet consectetur", "branding", "assets/img/portfolio/branding-2.jpg"),
                    build(7, "Books 2", "Lorem ipsum, dolor sit amet consectetur", "books", "assets/img/portfolio/books-2.jpg"),
                    build(8, "App 3", "Lorem ipsum, dolor sit amet consectetur", "app", "assets/img/portfolio/app-3.jpg"),
                    build(9, "Product 3", "Lorem ipsum, dolor sit amet consectetur", "product", "assets/img/portfolio/product-3.jpg"),
                    build(10, "Branding 3", "Lorem ipsum, dolor sit amet consectetur", "branding", "assets/img/portfolio/branding-3.jpg"),
                    build(11, "Books 3", "Lorem ipsum, dolor sit amet consectetur", "books", "assets/img/portfolio/books-3.jpg")
            );

            repository.saveAll(defaults);
        };
    }

    private PortfolioItem build(int sort, String title, String summary, String category, String coverImageUrl) {
        PortfolioItem item = new PortfolioItem();
        item.setSortOrder(sort);
        item.setTitle(title);
        item.setSummary(summary);
        item.setCategory(category);
        item.setCoverImageUrl(coverImageUrl);
        item.setPublished(true);
        item.setContent("");
        return item;
    }
}
