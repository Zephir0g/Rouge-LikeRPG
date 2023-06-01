package com.jaga.config;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import static com.jaga.core.Game.log;

public class ConfigLogger {

    public static void LoggerColor() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter() {
            private static final String COLOR_INFO = "\u001B[36m"; //blue
            private static final String COLOR_WARNING = "\u001B[31m"; //red
            private static final String COLOR_RESET = "\u001B[0m"; //reset

            @Override
            public synchronized String format(java.util.logging.LogRecord record) {
                if (record.getLevel() == Level.INFO || record.getLevel() == Level.CONFIG) {
                    return COLOR_INFO + super.format(record) + COLOR_RESET;
                }
                if (record.getLevel() == Level.WARNING) {
                    return COLOR_WARNING + super.format(record) + COLOR_RESET;
                }
                return super.format(record);
            }
        });

        log.addHandler(consoleHandler);
        log.setUseParentHandlers(false);
    }

}
