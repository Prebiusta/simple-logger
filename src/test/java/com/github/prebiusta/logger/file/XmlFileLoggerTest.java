package com.github.prebiusta.logger.file;

import com.github.prebiusta.logger.LoggerManager;
import com.github.prebiusta.model.LogLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

class XmlFileLoggerTest {
    private LoggerManager loggerManager;

    @BeforeEach
    void setUp() {
        Path folderPath = Path.of(new File("src/test/resources/log/xml").getAbsolutePath());


        LoggerManager loggerManager = new LoggerManager();
        loggerManager.addLogger(new XmlFileLogger(folderPath, LogLevel.VERBOSE));
        this.loggerManager = loggerManager;
    }

    @Test
    public void testXmlLogger() {
        loggerManager.verbose("Verbose Message");
        loggerManager.debug("Debug Message");
        loggerManager.info("Info Message");
        loggerManager.warning("Warn Message");
        loggerManager.error("Error Message");
        loggerManager.fatal("Fatal Message");
    }

    @Test
    public void testXmlLoggerWithException() {
        Exception exception = new Exception("Exception message");

        loggerManager.verbose("Verbose Message", exception);
        loggerManager.debug("Debug Message", exception);
        loggerManager.info("Info Message", exception);
        loggerManager.warning("Warn Message", exception);
        loggerManager.error("Error Message", exception);
        loggerManager.fatal("Fatal Message", exception);
    }
}