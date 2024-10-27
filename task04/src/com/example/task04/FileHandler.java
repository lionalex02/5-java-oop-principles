package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileHandler implements MessageHandler {
    private final ExecutorService executor;
    private final FileWriter writer;

    public FileHandler(String filename) throws IOException {
        this.executor = Executors.newSingleThreadExecutor();
        this.writer = new FileWriter(filename, true);
    }

    @Override
    public void handler(String message) {
        executor.submit(() -> writeMessageToFile(message));
    }

    private void writeMessageToFile(String message) {
        try {
            writer.write(message);
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Failed to write to file: " + e.getMessage());
        }
    }

    public void finish() {
        try {
            writer.close();
            executor.shutdownNow();
        } catch (IOException e) {
            System.out.println("Failed to close file: " + e.getMessage());
        }
    }
}
