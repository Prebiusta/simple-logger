package com.github.prebiusta.logger;

import com.github.prebiusta.exception.SimpleLoggerException;
import com.github.prebiusta.model.LogEvent;
import com.github.prebiusta.model.LogLevel;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public abstract class SimpleLogger {
    protected String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    protected String getCurrentTimestamp() {
        return getTimestampFormat().format(OffsetDateTime.now());
    }

    public void log(LogLevel level, String message) {
        if (isLoggable(level)) {
            LogEvent logEvent = createLogEvent(level, message);
            log(logEvent);
        }
    }

    public void log(LogLevel level, String message, Exception exception) {
        log(level, parseExceptionToString(message, exception));
    }

    protected String parseExceptionToString(String originalMessage, Exception e) {
        String message = e.getMessage();
        String stackTrace = getStackTraceAsString(e);
        return originalMessage + "\n" + "[" + message + " " + stackTrace + "]";
    }

    protected DateTimeFormatter getTimestampFormat() {
        return DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    }

    protected abstract void log(LogEvent logEvent);

    protected abstract LogLevel getMinLogLevel();

    private String getStackTraceAsString(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    private LogEvent createLogEvent(LogLevel level, String message) {
        String thread = getCurrentThreadName();
        String timestamp = getCurrentTimestamp();

        LogEvent logEvent = new LogEvent();
        logEvent.setLogLevel(level);
        logEvent.setMessage(message);
        logEvent.setThread(thread);
        logEvent.setTimestamp(timestamp);

        return logEvent;
    }

    private boolean isLoggable(LogLevel logLevel) {
        LogLevel minLogLevel = getMinLogLevel();
        if (minLogLevel == null) {
            throw new SimpleLoggerException("Min log level cannot be null");
        }
        return logLevel.ordinal() >= minLogLevel.ordinal();
    }
}
