package com.github.hrozhek.noizalyzerserver.log;

import org.slf4j.LoggerFactory;

public class Logger {

    private final org.slf4j.Logger logger;

    public Logger(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public void debug(String format, Object... args) {
        logger.debug(format, args);
    }
}
