package com.spring_boilerplate.Boilerplate.service;

import com.spring_boilerplate.Boilerplate.exception.CustomException;
import com.spring_boilerplate.Boilerplate.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

@Service
public class UploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private StringUtil stringUtil;

    public String saveFile(MultipartFile file) throws IOException {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filename = stringUtil.formatFileName(file.getOriginalFilename());

        Path path = Paths.get(uploadDir, filename);

        Files.write(path, file.getBytes());

        return filename;
    }

    public byte[] getFile(String filename) throws IOException {
        Path path = Paths.get(uploadDir, filename);

        if (!Files.exists(path)) {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "File : " + filename + " not found on server", null);
        }

        return Files.readAllBytes(path);
    }
}
