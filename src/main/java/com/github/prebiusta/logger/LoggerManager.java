package com.github.prebiusta.logger;

import com.github.prebiusta.model.LogLevel;

import java.util.ArrayList;
import java.util.List;

public class LoggerManager {
    private final List<SimpleLogger> loggers;

    public LoggerManager() {
        this.loggers = new ArrayList<>();
    }

    public void addLogger(SimpleLogger logger) {
        this.loggers.add(logger);
    }

    public void verbose(String message) {
        log(LogLevel.VERBOSE, message);
    }

    public void verbose(String message, Exception exception) {
        log(LogLevel.VERBOSE, message, exception);
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void debug(String message, Exception exception) {
        log(LogLevel.DEBUG, message, exception);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void info(String message, Exception exception) {
        log(LogLevel.INFO, message, exception);
    }

    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void warning(String message, Exception exception) {
        log(LogLevel.WARNING, message, exception);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void error(String message, Exception exception) {
        log(LogLevel.ERROR, message, exception);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    public void fatal(String message, Exception exception) {
        log(LogLevel.FATAL, message, exception);
    }

    private void log(LogLevel level, String message) {
        for (SimpleLogger logger : loggers) {
            logger.log(level, message);
        }
    }

    private void log(LogLevel level, String message, Exception exception) {
        for (SimpleLogger logger : loggers) {
            logger.log(level, message, exception);
        }
    }
}
