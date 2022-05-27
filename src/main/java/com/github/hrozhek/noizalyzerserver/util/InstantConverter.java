package com.github.hrozhek.noizalyzerserver.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantConverter {

    private static final String PATTERN_FORMAT = "HH.mm_dd.MM";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
            .withZone(ZoneId.systemDefault());

    public static String format(Instant instant) {
        return formatter.format(instant);
    }
}
