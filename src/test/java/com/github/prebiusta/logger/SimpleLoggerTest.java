package com.github.prebiusta.logger;

import com.github.prebiusta.exception.SimpleLoggerException;
import com.github.prebiusta.model.LogEvent;
import com.github.prebiusta.model.LogLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleLoggerTest {
    private SimpleLogger simpleLogger;

    @BeforeEach
    void setUp() {
        this.simpleLogger = spy(new SimpleLogger() {
            @Override
            protected void log(LogEvent logEvent) {
                System.out.print(logEvent.getMessage());
            }

            @Override
            protected LogLevel getMinLogLevel() {
                return LogLevel.INFO;
            }
        });
    }

    @Test
    public void testMinLogLevel_verbose() {
        doReturn(LogLevel.VERBOSE).when(simpleLogger).getMinLogLevel();

        performAllLogs();

        verifyNumberOfLogExecutions(12);
    }

    @Test
    public void testMinLogLevel_debug() {
        doReturn(LogLevel.DEBUG).when(simpleLogger).getMinLogLevel();

        performAllLogs();

        verifyNumberOfLogExecutions(10);
    }

    @Test
    public void testMinLogLevel_info() {
        doReturn(LogLevel.INFO).when(simpleLogger).getMinLogLevel();

        performAllLogs();

        verifyNumberOfLogExecutions(8);
    }

    @Test
    public void testMinLogLevel_warning() {
        doReturn(LogLevel.WARNING).when(simpleLogger).getMinLogLevel();

        performAllLogs();

        verifyNumberOfLogExecutions(6);
    }

    @Test
    public void testMinLogLevel_error() {
        doReturn(LogLevel.ERROR).when(simpleLogger).getMinLogLevel();

        performAllLogs();

        verifyNumberOfLogExecutions(4);
    }

    @Test
    public void testMinLogLevel_fatal() {
        doReturn(LogLevel.FATAL).when(simpleLogger).getMinLogLevel();

        performAllLogs();

        verifyNumberOfLogExecutions(2);
    }

    @Test
    public void testMinLogLevel_null() {
        doReturn(null).when(simpleLogger).getMinLogLevel();

        assertThrows(SimpleLoggerException.class, this::performAllLogs);
    }

    private void performAllLogs() {
        String message = "Message";
        Exception exception = new Exception("Exception");

        simpleLogger.log(LogLevel.VERBOSE, message);
        simpleLogger.log(LogLevel.VERBOSE, message, exception);

        simpleLogger.log(LogLevel.DEBUG, message);
        simpleLogger.log(LogLevel.DEBUG, message, exception);

        simpleLogger.log(LogLevel.INFO, message);
        simpleLogger.log(LogLevel.INFO, message, exception);

        simpleLogger.log(LogLevel.WARNING, message);
        simpleLogger.log(LogLevel.WARNING, message, exception);

        simpleLogger.log(LogLevel.ERROR, message);
        simpleLogger.log(LogLevel.ERROR, message, exception);

        simpleLogger.log(LogLevel.FATAL, message);
        simpleLogger.log(LogLevel.FATAL, message, exception);
    }

    public void verifyNumberOfLogExecutions(int expectedNumberOfActualExecution) {
        verify(simpleLogger, times(6)).log(any(), any(), any());
        verify(simpleLogger, times(12)).log(any(), any());
        verify(simpleLogger, times(expectedNumberOfActualExecution)).log(any());
    }
}