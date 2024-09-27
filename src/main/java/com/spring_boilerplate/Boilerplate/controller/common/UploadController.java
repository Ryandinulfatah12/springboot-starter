package com.spring_boilerplate.Boilerplate.controller.common;

import com.spring_boilerplate.Boilerplate.service.UploadService;
import com.spring_boilerplate.Boilerplate.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    // API untuk upload file
    @PostMapping
    public ApiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String response = uploadService.saveFile(file);
            return new ApiResponse<>(201, true, "File uploaded successfully", response, null);
        } catch (IOException e) {
            return new ApiResponse<>(500, false, "Failed to upload file: " + e.getMessage(), null, null);
        }
    }

    // API untuk render atau download file
    @GetMapping("/files/{filename}")
    public ResponseEntity<byte[]> renderFile(@PathVariable String filename) {
        try {
            byte[] fileData = uploadService.getFile(filename);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE));
            headers.setContentDispositionFormData("attachment", filename);

            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
}
