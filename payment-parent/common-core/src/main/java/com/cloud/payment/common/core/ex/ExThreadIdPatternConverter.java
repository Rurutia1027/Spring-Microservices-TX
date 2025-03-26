package com.cloud.payment.common.core.ex;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

/**
 * Custom Log4j2 Pattern Converter for Thread ID (%T).
 */
@ConverterKeys({"T"}) // Allows using %T in log pattern
public class ExThreadIdPatternConverter extends LogEventPatternConverter {

    protected ExThreadIdPatternConverter(String name, String style) {
        super(name, style);
    }

    public static ExThreadIdPatternConverter newInstance(String[] options) {
        return new ExThreadIdPatternConverter("T", "thread");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        toAppendTo.append(Thread.currentThread().getId());
    }
}