package com.github.prebiusta;

import com.github.prebiusta.logger.console.ConsoleLogger;
import com.github.prebiusta.logger.LoggerManager;
import com.github.prebiusta.logger.SimpleLogger;
import com.github.prebiusta.model.LogLevel;
import org.junit.jupiter.api.Test;

public class MultiThreadTest {
    @Test
    public void testMultiThread() {
        SimpleLogger simpleLogger = new ConsoleLogger(LogLevel.INFO);
        LoggerManager loggerManager = new LoggerManager();
        loggerManager.addLogger(simpleLogger);


        for (int i = 0; i < 100; i++) {
            new Thread(() -> loggerManager.info("Hello")).start();
        }
    }
}
