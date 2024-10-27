package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final List<String> messages = new ArrayList<>();
    private final MessageHandler targetHandler;
    private final int bufferSize;

    public MemoryHandler(MessageHandler targetHandler, int bufferSize) {
        this.targetHandler = targetHandler;
        this.bufferSize = bufferSize;
    }

    @Override
    public void handler(String message) {
        messages.add(message);
        if (messages.size() >= bufferSize) {
            flush();
        }
    }

    public void flush() {
        for (String msg : messages) {
            targetHandler.handler(msg);
        }
        messages.clear();
    }


}
