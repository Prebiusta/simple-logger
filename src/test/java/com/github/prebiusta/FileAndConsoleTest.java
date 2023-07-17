package com.github.prebiusta;

import com.github.prebiusta.logger.LoggerManager;
import com.github.prebiusta.logger.SimpleLogger;
import com.github.prebiusta.logger.console.ConsoleLogger;
import com.github.prebiusta.logger.file.JsonFileLogger;
import com.github.prebiusta.logger.file.XmlFileLogger;
import com.github.prebiusta.model.LogLevel;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

public class FileAndConsoleTest {
    @Test
    public void testSimple() {
        Path folderPath = Path.of(new File("src/test/resources/log").getAbsolutePath());

        SimpleLogger simpleLogger = new ConsoleLogger(LogLevel.INFO);
        SimpleLogger jsonFileLogger = new JsonFileLogger(folderPath.resolve("json"), LogLevel.INFO);
        SimpleLogger jsonFileLoggerError = new JsonFileLogger(folderPath.resolve("json/error"), LogLevel.ERROR);
        SimpleLogger xmlFileLogger = new XmlFileLogger(folderPath.resolve("xml"), LogLevel.VERBOSE);
        SimpleLogger xmlFileLoggerError = new XmlFileLogger(folderPath.resolve("xml/error"), LogLevel.ERROR);

        LoggerManager loggerManager = new LoggerManager();

        loggerManager.addLogger(simpleLogger);
        loggerManager.addLogger(jsonFileLogger);
        loggerManager.addLogger(jsonFileLoggerError);
        loggerManager.addLogger(xmlFileLogger);
        loggerManager.addLogger(xmlFileLoggerError);

        loggerManager.verbose("Simple verbose message");
        loggerManager.debug("Simple debug message");
        loggerManager.info("Simple info message");
        loggerManager.warning("Simple warning message");
        loggerManager.error("Simple error message");
        loggerManager.fatal("Simple fatal message");
    }
}
