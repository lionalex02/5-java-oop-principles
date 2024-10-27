package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String filePathPattern;
    private final ChronoUnit rotationUnit;
    private String currentFilePath;
    private LocalDateTime lastRotationTime;

    public RotationFileHandler(String filePathPattern, ChronoUnit rotationUnit) {
        this.filePathPattern = filePathPattern;
        this.rotationUnit = rotationUnit;
        this.currentFilePath = getFilePathForCurrentTime();
        this.lastRotationTime = LocalDateTime.now().truncatedTo(rotationUnit);
    }

    @Override
    public void handler(String message) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(lastRotationTime.plus(1, rotationUnit))) {
            lastRotationTime = now.truncatedTo(rotationUnit);
            currentFilePath = getFilePathForCurrentTime();
        }
        try (FileWriter writer = new FileWriter(currentFilePath, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFilePathForCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
        String formattedDate = now.truncatedTo(rotationUnit).format(formatter);
        return filePathPattern.replace("{date}", formattedDate);
    }
}
