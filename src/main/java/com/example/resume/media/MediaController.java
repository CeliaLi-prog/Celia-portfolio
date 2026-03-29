package com.example.resume.media;

import com.example.resume.common.ApiResponse;
import com.example.resume.common.BusinessException;
import com.example.resume.common.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.UUID;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 文件上传接口 / File upload API
 */
@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${app.upload.publicPath}")
    private String publicPath;

    private final MediaRepository mediaRepository;

    public MediaController(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    /**
     * 上传文件（图片/视频）/ Upload image/video file
     */
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<MediaFile>> upload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.fail(ErrorCode.VALIDATION_ERROR.name(), "File is empty / 文件为空"));
            }

            String original = StringUtils.cleanPath(file.getOriginalFilename() == null ? "file" : file.getOriginalFilename());
            String ext = "";
            int dot = original.lastIndexOf('.');
            if (dot >= 0) ext = original.substring(dot);

            String newName = UUID.randomUUID() + ext;

            Path dir = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(dir);

            Path target = dir.resolve(newName);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            MediaType type = guessType(file.getContentType());

            MediaFile mf = new MediaFile();
            mf.setOriginalName(original);
            mf.setStoredName(newName);
            mf.setMediaType(type);
            mf.setPublicUrl(publicPath + "/" + newName);

            MediaFile saved = mediaRepository.save(mf);
            return ResponseEntity.status(201).body(ApiResponse.created(saved));

        } catch (Exception e) {
            throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILED, INTERNAL_SERVER_ERROR.value(),
                    "Upload failed / 上传失败");
        }
    }

    private MediaType guessType(String contentType) {
        // 简单判断 / Simple type detection
        if (contentType == null) return MediaType.OTHER;
        if (contentType.startsWith("image/")) return MediaType.IMAGE;
        if (contentType.startsWith("video/")) return MediaType.VIDEO;
        return MediaType.OTHER;
    }
}
