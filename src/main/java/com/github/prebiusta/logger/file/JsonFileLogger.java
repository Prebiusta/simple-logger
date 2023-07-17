package com.github.prebiusta.logger.file;

import com.github.prebiusta.model.LogEvent;
import com.github.prebiusta.model.LogLevel;
import com.google.gson.Gson;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class JsonFileLogger extends SimpleFileLogger {
    private final LogLevel minLogLevel;

    public JsonFileLogger(Path logFolder, LogLevel minLogLevel) {
        super(DateTimeFormatter.ofPattern("yyyyMMdd"), "json", logFolder);
        this.minLogLevel = minLogLevel;
    }

    @Override
    protected LogLevel getMinLogLevel() {
        return this.minLogLevel;
    }

    @Override
    public String parseLogEvent(LogEvent logEvent) {
        return new Gson().toJson(logEvent);
    }
}
