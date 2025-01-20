package com.elvishew.xlog.interceptor;

import com.elvishew.xlog.LogItem;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/interceptor/AbstractFilterInterceptor.class */
public abstract class AbstractFilterInterceptor implements Interceptor {
    @Override // com.elvishew.xlog.interceptor.Interceptor
    public LogItem intercept(LogItem logItem) {
        if (reject(logItem)) {
            return null;
        }
        return logItem;
    }

    public abstract boolean reject(LogItem logItem);
}