package com.github.prebiusta.logger;

import com.github.prebiusta.model.LogLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoggerManagerTest {
    private LoggerManager loggerManager;
    @Mock
    private SimpleLogger simpleLoggerMock;

    @BeforeEach
    void setUp() {
        this.loggerManager = new LoggerManager();
        this.loggerManager.addLogger(simpleLoggerMock);
    }

    @Test
    void testVerboseLogMessage_correctLogLevel() {
        String message = "Message";

        loggerManager.verbose(message);

        verify(simpleLoggerMock).log(LogLevel.VERBOSE, message);
    }

    @Test
    void testVerboseLogMessageWithException_correctLogLevel() {
        String message = "Message";
        Exception exception = new Exception("Exception message");

        loggerManager.verbose(message, exception);

        verify(simpleLoggerMock).log(LogLevel.VERBOSE, message, exception);
    }

    @Test
    void testDebugLogMessage_correctLogLevel() {
        String message = "Message";

        loggerManager.debug(message);

        verify(simpleLoggerMock).log(LogLevel.DEBUG, message);
    }

    @Test
    void testDebugLogMessageWithException_correctLogLevel() {
        String message = "Message";
        Exception exception = new Exception("Exception message");

        loggerManager.debug(message, exception);

        verify(simpleLoggerMock).log(LogLevel.DEBUG, message, exception);
    }

    @Test
    void testInfoLogMessage_correctLogLevel() {
        String message = "Message";

        loggerManager.info(message);

        verify(simpleLoggerMock).log(LogLevel.INFO, message);
    }

    @Test
    void testInfoLogMessageWithException_correctLogLevel() {
        String message = "Message";
        Exception exception = new Exception("Exception message");

        loggerManager.info(message, exception);

        verify(simpleLoggerMock).log(LogLevel.INFO, message, exception);
    }

    @Test
    void testWarningLogMessage_correctLogLevel() {
        String message = "Message";

        loggerManager.warning(message);

        verify(simpleLoggerMock).log(LogLevel.WARNING, message);
    }

    @Test
    void testWarningLogMessageWithException_correctLogLevel() {
        String message = "Message";
        Exception exception = new Exception("Exception message");

        loggerManager.warning(message, exception);

        verify(simpleLoggerMock).log(LogLevel.WARNING, message, exception);
    }

    @Test
    void testErrorLogMessage_correctLogLevel() {
        String message = "Message";

        loggerManager.error(message);

        verify(simpleLoggerMock).log(LogLevel.ERROR, message);
    }

    @Test
    void testErrorLogMessageWithException_correctLogLevel() {
        String message = "Message";
        Exception exception = new Exception("Exception message");

        loggerManager.error(message, exception);

        verify(simpleLoggerMock).log(LogLevel.ERROR, message, exception);
    }

    @Test
    void testFatalLogMessage_correctLogLevel() {
        String message = "Message";

        loggerManager.fatal(message);

        verify(simpleLoggerMock).log(LogLevel.FATAL, message);
    }

    @Test
    void testFatalLogMessageWithException_correctLogLevel() {
        String message = "Message";
        Exception exception = new Exception("Exception message");

        loggerManager.fatal(message, exception);

        verify(simpleLoggerMock).log(LogLevel.FATAL, message, exception);
    }

    @Test
    void testMultipleLoggers() {
        String message = "Message";
        SimpleLogger anotherLoggerMock = mock(SimpleLogger.class);
        this.loggerManager.addLogger(anotherLoggerMock);

        loggerManager.info(message);

        verify(simpleLoggerMock).log(LogLevel.INFO, message);
        verify(anotherLoggerMock).log(LogLevel.INFO, message);
    }
}