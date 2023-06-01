package com.jaga.config;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

import static com.jaga.core.Game.log;

public class ConfigLogger {

    public static void LoggerColor() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter() {
            private static final String COLOR_INFO = "\u001B[36m"; // blue
            private static final String COLOR_WARNING = "\u001B[33m"; // yellow
            private static final String COLOR_SEVERE = "\u001B[31m"; // red
            private static final String COLOR_BASE = "\u001B[37m"; // white
            private static final String COLOR_RESET = "\u001B[0m"; // reset


            @Override
            public synchronized String format(LogRecord record) {
                String time, level, path, message, separator;
                if (record.getLevel() == Level.INFO || record.getLevel() == Level.CONFIG) {
                    level = COLOR_INFO + "[" + record.getLevel() + "]" + COLOR_RESET;
                    path = COLOR_INFO + "[" + record.getSourceClassName() + "]" + COLOR_RESET;
                    message = COLOR_INFO + record.getMessage() + COLOR_RESET;
                } else if (record.getLevel() == Level.WARNING) {
                    level = COLOR_WARNING + "[" + record.getLevel() + "]" + COLOR_RESET;
                    path = COLOR_WARNING + "[" + record.getSourceClassName() + "]" + COLOR_RESET;
                    message = COLOR_WARNING + record.getMessage() + COLOR_RESET;
                } else if (record.getLevel() == Level.SEVERE) {
                    level = COLOR_SEVERE + "[" + record.getLevel() + "]" + COLOR_RESET;
                    path = COLOR_SEVERE + "[" + record.getSourceClassName() + "]" + COLOR_RESET;
                    message = COLOR_SEVERE + record.getMessage() + COLOR_RESET;
                } else {
                    level = "[" + record.getLevel() + "]";
                    path = "[" + record.getSourceClassName() + "]";
                    message = record.getMessage();
                }

                time = String.format(COLOR_BASE + "[%1$tF %1$tT]" + COLOR_RESET, record.getMillis());
                separator = String.format(COLOR_BASE + " : " + COLOR_RESET);

                return String.format(time + " %2$s %3$s " + separator + " %4$s%n",
                        record.getMillis(), level, path, message);
            }
        });

        log.addHandler(consoleHandler);
        log.setUseParentHandlers(false);
    }

}
