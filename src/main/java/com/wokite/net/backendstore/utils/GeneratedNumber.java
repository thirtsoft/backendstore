package com.wokite.net.backendstore.utils;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Component;

public class GeneratedNumber {

    public static long generateCodeCommand() {
        final String FORMAT = "yyyyMddHHmmss";
        return Long.parseLong(DateTimeFormat.forPattern(FORMAT).print(LocalDateTime.now()));
    }


}
