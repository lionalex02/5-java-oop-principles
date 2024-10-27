package com.example.task01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class Logger {
    private static final ConcurrentHashMap<String, Logger> LOGGER_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");


    private final String name;
    private Level currentLevel = Level.DEBUG;

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger (String name) {

        Logger logger = LOGGER_CONCURRENT_HASH_MAP.getOrDefault(name, null);

        if(logger == null) {
            logger = new Logger(name);
            LOGGER_CONCURRENT_HASH_MAP.put(name, logger);
        }

        return logger;
    }

    public String getName() {
        return name;
    }

    public void setLevel(Level level) {
        this.currentLevel = level;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String template, Object... args) {
        log(Level.DEBUG, String.format(template, args));
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String template, Object... args) {
        log(Level.INFO, String.format(template, args));
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String template, Object... args) {
        log(Level.WARNING, String.format(template, args));
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String template, Object... args) {
        log(Level.ERROR, String.format(template, args));
    }

    public void log(Level level, String message) {
        if (level.ordinal() >= currentLevel.ordinal()) {
            String formattedMessage = String.format("[%s] %s %s - %s", level, dateFormat.format(new Date()), name, message);
            System.out.println(formattedMessage);
        }
    }

    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }
}