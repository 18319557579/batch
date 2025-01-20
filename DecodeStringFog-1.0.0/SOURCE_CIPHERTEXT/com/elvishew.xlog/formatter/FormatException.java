package com.elvishew.xlog.formatter;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/formatter/FormatException.class */
public class FormatException extends RuntimeException {
    private static final long serialVersionUID = -5365630128856068164L;

    public FormatException() {
    }

    public FormatException(String str) {
        super(str);
    }

    public FormatException(String str, Throwable th) {
        super(str, th);
    }

    public FormatException(Throwable th) {
        super(th);
    }
}