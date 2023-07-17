package com.github.prebiusta.logger.file;

import com.github.prebiusta.logger.LoggerManager;
import com.github.prebiusta.model.LogEvent;
import com.github.prebiusta.model.LogLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

class SimpleFileLoggerTest {
    private LoggerManager loggerManager;

    @BeforeEach
    void setUp() {
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        String extension = "txt";
        Path folderPath = Path.of(new File("src/test/resources").getAbsolutePath());

        SimpleFileLogger simpleFileLogger = new SimpleFileLogger(fileFormat, extension, folderPath.resolve("log/txt")) {
            @Override
            public String parseLogEvent(LogEvent logEvent) {
                return logEvent.toString();
            }

            @Override
            protected LogLevel getMinLogLevel() {
                return LogLevel.VERBOSE;
            }
        };

        LoggerManager loggerManager = new LoggerManager();
        loggerManager.addLogger(simpleFileLogger);

        this.loggerManager = loggerManager;
    }

    @Test
    void testInfo() {
        loggerManager.info("Info message");
    }
}