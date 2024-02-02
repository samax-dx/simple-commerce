package com.samax.simpleCommerce.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class FileStorageService {

    private final Path fileStoragePath;


    public FileStorageService(@Value("${app.upload-dir}") String uploadDirectory) throws IOException {
        fileStoragePath = Paths.get(uploadDirectory).toAbsolutePath().normalize();
        Files.createDirectories(fileStoragePath);
    }

    public String uploadFile(MultipartFile file) throws IOException, NullPointerException {
        String fileName = Optional.ofNullable(file.getOriginalFilename())
                .map(v -> Instant.now().getEpochSecond() + "-" + StringUtils.cleanPath(v))
                .orElseThrow(NullPointerException::new);
        Files.copy(file.getInputStream(), fileStoragePath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    public Resource downloadFileResource(String fileName) throws IOException, NullPointerException {
        try (Stream<Path> files = Files.list(fileStoragePath)) {
            URI uri = files.filter(file -> file.getFileName().toString().equals(fileName))
                    .findFirst()
                    .map(Path::toUri)
                    .orElseThrow(NullPointerException::new);
            return new UrlResource(uri);
        }
    }

}
