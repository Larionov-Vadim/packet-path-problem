package com.larionov.packetpathproblem.util;

public class Utils {

    private Utils() {
    }

    public static int tryParseInteger(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ignore) {
            return defaultValue;
        }
    }
}
