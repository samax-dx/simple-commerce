package com.samax.simpleCommerce.common.controller;

import com.samax.simpleCommerce.common.excption.ScHttpException;
import com.samax.simpleCommerce.common.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileStorageController {

    private static final String MSG_FILE_READ_ERROR = "file read failure";
    private static final String MSG_FILE_NOT_FOUND = "file not found";
    private static final String MSG_SAVE_FAILURE = "file save failure";
    private static final String MSG_BAD_FILENAME = "invalid filename";

    private final FileStorageService storageService;


    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> download(@PathVariable String fileName) {
        Resource resource;

        try {
            resource = storageService.downloadFileResource(fileName);
        } catch (IOException e) {
            throw new ScHttpException(HttpStatus.SERVICE_UNAVAILABLE, MSG_FILE_READ_ERROR);
        } catch (NullPointerException e) {
            throw new ScHttpException(HttpStatus.NOT_FOUND, MSG_FILE_NOT_FOUND);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", resource.getFilename()))
                .body(resource);
    }

    @PostMapping("/upload")
    public String upload(@RequestPart("file") MultipartFile file) {
        try {
            return storageService.uploadFile(file);
        } catch (IOException e) {
            throw new ScHttpException(HttpStatus.SERVICE_UNAVAILABLE, MSG_SAVE_FAILURE);
        } catch (NullPointerException e) {
            throw new ScHttpException(HttpStatus.BAD_REQUEST, MSG_BAD_FILENAME);
        }
    }

}
