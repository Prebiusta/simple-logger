package com.github.prebiusta.logger.file;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.github.prebiusta.exception.SimpleFileLoggerException;
import com.github.prebiusta.model.LogEvent;
import com.github.prebiusta.model.LogLevel;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class XmlFileLogger extends SimpleFileLogger {
    private final LogLevel minLogLevel;

    public XmlFileLogger(Path logFolder, LogLevel minLogLevel) {
        super(DateTimeFormatter.ofPattern("yyyyMMdd-HH"), "xml", logFolder);
        this.minLogLevel = minLogLevel;
    }

    @Override
    protected LogLevel getMinLogLevel() {
        return this.minLogLevel;
    }

    @Override
    public String parseLogEvent(LogEvent logEvent) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, false);
            return xmlMapper.writeValueAsString(logEvent);
        } catch (Exception e) {
            throw new SimpleFileLoggerException("Unable to convert log event to xml", e);
        }
    }
}
