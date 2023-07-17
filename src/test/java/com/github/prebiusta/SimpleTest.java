package com.github.prebiusta;

import com.github.prebiusta.logger.console.ConsoleLogger;
import com.github.prebiusta.logger.LoggerManager;
import com.github.prebiusta.logger.SimpleLogger;
import com.github.prebiusta.model.LogLevel;
import org.junit.jupiter.api.Test;

public class SimpleTest {
    @Test
    public void testSimple() {
        SimpleLogger simpleLogger = new ConsoleLogger(LogLevel.INFO);
        SimpleLogger simpleLogger1 = new ConsoleLogger(LogLevel.FATAL);
        SimpleLogger simpleLogger2 = new ConsoleLogger(LogLevel.VERBOSE);

        LoggerManager loggerManager = new LoggerManager();

        loggerManager.addLogger(simpleLogger);
        loggerManager.addLogger(simpleLogger1);
        loggerManager.addLogger(simpleLogger2);

        loggerManager.verbose("Simple verbose message");
        loggerManager.debug("Simple debug message");
        loggerManager.info("Simple info message");
        loggerManager.warning("Simple warning message");
        loggerManager.error("Simple error message");
        loggerManager.fatal("Simple fatal message");
    }
}
