package com.elvishew.xlog.formatter.message.throwable;

import com.elvishew.xlog.internal.util.StackTraceUtil;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/formatter/message/throwable/DefaultThrowableFormatter.class */
public class DefaultThrowableFormatter implements ThrowableFormatter {
    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(Throwable th) {
        return StackTraceUtil.getStackTraceString(th);
    }
}