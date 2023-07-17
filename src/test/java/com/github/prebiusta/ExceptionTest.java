package com.github.prebiusta;

import com.github.prebiusta.logger.LoggerManager;
import com.github.prebiusta.logger.SimpleLogger;
import com.github.prebiusta.logger.console.ConsoleLogger;
import com.github.prebiusta.model.LogLevel;
import org.junit.jupiter.api.Test;

public class ExceptionTest {
    @Test
    public void testSimple() {
        SimpleLogger simpleLogger = new ConsoleLogger(LogLevel.INFO);
        LoggerManager loggerManager = new LoggerManager();
        loggerManager.addLogger(simpleLogger);

        Exception exception = new Exception("Error has occurred");

        loggerManager.verbose("Simple verbose message", exception);
        loggerManager.debug("Simple debug message", exception);
        loggerManager.info("Simple info message", exception);
        loggerManager.warning("Simple warning message", exception);
        loggerManager.error("Simple error message", exception);
        loggerManager.fatal("Simple fatal message", exception);
    }
}
