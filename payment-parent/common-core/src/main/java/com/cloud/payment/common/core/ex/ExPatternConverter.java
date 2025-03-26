package com.cloud.payment.common.core.ex;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

/**
 * Custom Log4j2 Pattern Converter
 */
@ConverterKeys({"ex"}) // Defines the pattern key used in log4j2.xml
public class ExPatternConverter extends LogEventPatternConverter {

    protected ExPatternConverter(String name, String style) {
        super(name, style);
    }

    public static ExPatternConverter newInstance(String[] options) {
        return new ExPatternConverter("ex", "custom");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        toAppendTo.append("[CustomLog]: ").append(event.getMessage().getFormattedMessage());
    }
}