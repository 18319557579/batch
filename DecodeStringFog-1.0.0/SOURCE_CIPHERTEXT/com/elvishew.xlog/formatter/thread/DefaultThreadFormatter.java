package com.elvishew.xlog.formatter.thread;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/formatter/thread/DefaultThreadFormatter.class */
public class DefaultThreadFormatter implements ThreadFormatter {
    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(Thread thread) {
        return "Thread: " + thread.getName();
    }
}