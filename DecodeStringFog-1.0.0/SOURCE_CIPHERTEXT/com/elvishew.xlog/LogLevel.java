package com.elvishew.xlog;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/LogLevel.class */
public class LogLevel {
    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ALL = Integer.MIN_VALUE;
    public static final int NONE = Integer.MAX_VALUE;

    public static String getLevelName(int i) {
        switch (i) {
            case 2:
                return "VERBOSE";
            case DEBUG /* 3 */:
                return "DEBUG";
            case 4:
                return "INFO";
            case WARN /* 5 */:
                return "WARN";
            case ERROR /* 6 */:
                return "ERROR";
            default:
                return i < 2 ? "VERBOSE-" + (2 - i) : "ERROR+" + (i - 6);
        }
    }

    public static String getShortLevelName(int i) {
        switch (i) {
            case 2:
                return "V";
            case DEBUG /* 3 */:
                return "D";
            case 4:
                return "I";
            case WARN /* 5 */:
                return "W";
            case ERROR /* 6 */:
                return "E";
            default:
                return i < 2 ? "V-" + (2 - i) : "E+" + (i - 6);
        }
    }
}