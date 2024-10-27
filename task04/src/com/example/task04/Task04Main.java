package com.example.task04;

import java.nio.file.Paths;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class Task04Main {
    public static void main(String[] args) {
        try {
            Logger logger = Logger.getLogger("myLogger");
            logger.setLevel(Logger.Level.INFO);

            String currentFolder = Paths.get("").toAbsolutePath().toString();
            System.out.println(currentFolder);

            RotationFileHandler rotationHandler = new RotationFileHandler(currentFolder + "/log_{date}.txt", ChronoUnit.MINUTES);

            ConsoleHandler consoleHandler = new ConsoleHandler();

            MemoryHandler memoryHandler = new MemoryHandler(consoleHandler, 5);

            FileHandler fileHandler = new FileHandler(Paths.get(currentFolder, "test_file.txt").toString());

            logger.addHandler(consoleHandler);
            logger.addHandler(rotationHandler);
            logger.addHandler(memoryHandler);
            logger.addHandler(fileHandler);

            logger.info("1");
            logger.info("2");
            logger.info("3");
            logger.info("4");
            logger.info("5");
            logger.info("6");

            logger.info("i slep");
            TimeUnit.SECONDS.sleep(6);
            logger.info("i slept");

            logger.info("7");
            logger.info("8");

            logger.info("i slep agin");
            TimeUnit.SECONDS.sleep(6);
            logger.info("i slept agan");

            TimeUnit.SECONDS.sleep(2);



            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
