package com.github.prebiusta.logger.console;

import com.github.prebiusta.logger.LoggerManager;
import com.github.prebiusta.model.LogLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsoleLoggerTest {
    private LoggerManager loggerManager;

    @BeforeEach
    void setUp() {
        LoggerManager loggerManager = new LoggerManager();
        loggerManager.addLogger(new ConsoleLogger(LogLevel.INFO));
        this.loggerManager = loggerManager;
    }

    @Test
    public void testConsoleLogger() {
        loggerManager.verbose("Verbose Message");
        loggerManager.debug("Debug Message");
        loggerManager.info("Info Message");
        loggerManager.warning("Warn Message");
        loggerManager.error("Error Message");
        loggerManager.fatal("Fatal Message");
    }
}