package com.github.prebiusta.logger.file;

import com.github.prebiusta.exception.SimpleFileLoggerException;
import com.github.prebiusta.logger.SimpleLogger;
import com.github.prebiusta.model.LogEvent;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public abstract class SimpleFileLogger extends SimpleLogger {
    private final DateTimeFormatter fileFormatter;
    private final String extension;
    private final Path logFolder;

    protected SimpleFileLogger(DateTimeFormatter fileFormatter, String extension, Path logFolder) {
        this.fileFormatter = fileFormatter;
        this.extension = extension;
        this.logFolder = logFolder;
    }

    @Override
    protected void log(LogEvent logEvent) {
        String message = parseLogEvent(logEvent);
        writeToFile(message);
    }

    private void writeToFile(String message) {
        try {
            Path filePath = getFilePath();
            if (!filePath.toFile().exists()) {
                Files.createDirectories(filePath.toFile().getParentFile().toPath());
                Files.createFile(filePath);
            }
            Files.writeString(filePath, message + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            throw new SimpleFileLoggerException("Unable to write message to the file", e);
        }
    }

    private Path getFilePath() {
        try {
            String logFileName = OffsetDateTime.now().format(this.fileFormatter) + "." + this.extension;
            return this.logFolder.resolve(Path.of(logFileName));
        } catch (Exception e) {
            throw new SimpleFileLoggerException("Unable to generate log file path", e);
        }
    }

    public abstract String parseLogEvent(LogEvent logEvent);
}
