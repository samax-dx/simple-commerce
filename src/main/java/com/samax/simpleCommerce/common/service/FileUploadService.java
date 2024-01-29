package com.samax.simpleCommerce.common.service;

import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;


@Service
public class FileUploadService {

    private static final String MSG_UPLOAD_ERROR = "file lost";

    private final Path fileUploadLocation;


    public FileUploadService(@Value("${file.upload-dir}") String uploadDirectory) throws IOException {
        fileUploadLocation = Paths.get(uploadDirectory).toAbsolutePath().normalize();
        Files.createDirectories(fileUploadLocation);
    }

    public String uploadFile(MultipartFile file) throws Exception {
        try {
            String fileName = Optional.ofNullable(file.getOriginalFilename()).map(Strings::cleanPath).orElseThrow(Exception::new);
            Files.copy(file.getInputStream(), fileUploadLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception ignore) {
            throw new Exception(MSG_UPLOAD_ERROR);
        }
    }

}
