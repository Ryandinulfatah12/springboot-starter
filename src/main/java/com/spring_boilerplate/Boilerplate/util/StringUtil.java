package com.spring_boilerplate.Boilerplate.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StringUtil {
    // Formatter untuk menambahkan timestamp dengan format yyyyMMddHHmmss
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    // Method untuk menghasilkan nama file yang diubah formatnya
    public String formatFileName(String originalFilename) {
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be empty.");
        }

        // Ganti spasi dengan underscore
        String fileNameWithoutSpaces = originalFilename.replaceAll("\\s+", "_");

        // Tambahkan timestamp ke nama file
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);

        // Menghasilkan nama file baru: timestamp + "_" + nama file
        return timestamp + "_" + fileNameWithoutSpaces;
    }
}
