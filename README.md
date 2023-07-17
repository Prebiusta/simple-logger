## Simple Logger

***
To initiate logger, instantiate `LoggerManager` and add desired loggers. `LoggerManager` will then automatically handle
logs for all registered logger (file, console, etc.)

Example for initiating logger

```java
class Main {
    public static void main(String[] args) {
        Path folderPath = Path.of(new File("log/folder/path").getAbsolutePath());

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
```

Extending logger with your custom implementation is fairly simple, it is only necessary to extend `SimpleLogger` class
and implement required methods.

Example using anonymous class:

```java
public class Main {
    public static void main(String[] args) {
        LoggerManager loggerManager = new LoggerManager();
        loggerManager.addLogger(new SimpleLogger() {
            @Override
            protected void log(LogEvent logEvent) {
                System.out.println(logEvent.getMessage());
            }

            @Override
            protected LogLevel getMinLogLevel() {
                return LogLevel.VERBOSE;
            }
        });

        loggerManager.verbose("Simple verbose message");
        loggerManager.debug("Simple debug message");
        loggerManager.info("Simple info message");
        loggerManager.warning("Simple warning message");
        loggerManager.error("Simple error message");
        loggerManager.fatal("Simple fatal message");
    }
}
```
It is also possible to use wrapper with logic to log into file system. In that case, extend `SimpleFileLogger` instead:

```java
public class Main {
    public static void main(String[] args) {
        DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        String extension = "txt";
        Path folderPath = Path.of(new File("log/folder/path").getAbsolutePath());

        SimpleFileLogger simpleFileLogger = new SimpleFileLogger(fileFormat, extension, folderPath.resolve("log/txt")) {
            @Override
            public String parseLogEvent(LogEvent logEvent) {
                return logEvent.toString();
            }

            @Override
            protected LogLevel getMinLogLevel() {
                return LogLevel.VERBOSE;
            }
        };

        LoggerManager loggerManager = new LoggerManager();
        loggerManager.addLogger(simpleFileLogger);

        loggerManager.verbose("Simple verbose message");
        loggerManager.debug("Simple debug message");
        loggerManager.info("Simple info message");
        loggerManager.warning("Simple warning message");
        loggerManager.error("Simple error message");
        loggerManager.fatal("Simple fatal message");
    }
}
```