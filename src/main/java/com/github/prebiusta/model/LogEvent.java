package com.github.prebiusta.model;

public class LogEvent {
    private String message;
    private LogLevel logLevel;
    private String thread;
    private String timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "message='" + message + '\'' +
                ", logLevel=" + logLevel +
                ", thread='" + thread + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
