package com.github.prebiusta.logger.console;

import com.github.prebiusta.logger.SimpleLogger;
import com.github.prebiusta.model.LogEvent;
import com.github.prebiusta.model.LogLevel;

public class ConsoleLogger extends SimpleLogger {
    // ANSI color codes for formatting
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private final LogLevel minLogLevel;

    public ConsoleLogger(LogLevel minLogLevel) {
        this.minLogLevel = minLogLevel;
    }

    @Override
    protected void log(LogEvent logEvent) {
        String logEntry = String.format("%s[%s] [%s] [%s]%s %s",
                getLogLevelColor(logEvent.getLogLevel()),
                logEvent.getTimestamp(),
                logEvent.getThread(),
                logEvent.getLogLevel(),
                ANSI_RESET,
                logEvent.getMessage());
        System.out.println(logEntry);
    }

    @Override
    protected LogLevel getMinLogLevel() {
        return this.minLogLevel;
    }

    private String getLogLevelColor(LogLevel level) {
        return switch (level) {
            case VERBOSE -> ANSI_PURPLE;
            case DEBUG -> ANSI_BLUE;
            case INFO -> ANSI_GREEN;
            case WARNING -> ANSI_YELLOW;
            case ERROR, FATAL -> ANSI_RED;
        };
    }
}
