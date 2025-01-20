package com.elvishew.xlog;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/LogItem.class */
public class LogItem {
    public int level;
    public String tag;
    public String msg;
    public String threadInfo;
    public String stackTraceInfo;

    public LogItem(int i, String str, String str2) {
        this.level = i;
        this.tag = str;
        this.msg = str2;
    }

    public LogItem(int i, String str, String str2, String str3, String str4) {
        this.level = i;
        this.tag = str;
        this.threadInfo = str2;
        this.stackTraceInfo = str3;
        this.msg = str4;
    }
}