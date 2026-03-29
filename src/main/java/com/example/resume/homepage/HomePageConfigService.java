package com.example.resume.homepage;

import com.example.resume.homepage.dto.HomePagePayload;
import com.example.resume.common.BusinessException;
import com.example.resume.common.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 首页配置服务 / Home page config service
 */
@Service
public class HomePageConfigService {

    private final HomePageConfigRepository repository;
    private final ObjectMapper objectMapper;

    public HomePageConfigService(HomePageConfigRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public HomePagePayload getOrInitPayload() {
        Optional<HomePageConfig> latest = repository.findTopByOrderByIdDesc();
        if (latest.isEmpty()) {
            HomePagePayload payload = createEmptyPayload();
            save(payload);
            return payload;
        }

        try {
            return objectMapper.readValue(latest.get().getContentJson(), HomePagePayload.class);
        } catch (Exception e) {
            HomePagePayload payload = createEmptyPayload();
            save(payload);
            return payload;
        }
    }

    private HomePagePayload createEmptyPayload() {
        HomePagePayload payload = new HomePagePayload();
        payload.setHero(new HomePagePayload.Hero());
        payload.setAbout(new HomePagePayload.About());
        payload.setContact(new HomePagePayload.Contact());
        payload.setFooter(new HomePagePayload.Footer());
        payload.setSkillsTools(new HomePagePayload.SkillsTools());
        payload.setPetGallery(new HomePagePayload.PetGallery());
        return payload;
    }

    public HomePagePayload save(HomePagePayload payload) {
        try {
            String json = objectMapper.writeValueAsString(payload);
            HomePageConfig config = repository.findTopByOrderByIdDesc().orElseGet(HomePageConfig::new);
            config.setContentJson(json);
            config.setUpdatedAt(OffsetDateTime.now());
            repository.save(config);
            return payload;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INTERNAL_ERROR, INTERNAL_SERVER_ERROR.value(),
                    "Failed to save home page config: " + e.getMessage());
        }
    }
}
