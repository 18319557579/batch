package com.elvishew.xlog.interceptor;

import com.elvishew.xlog.LogItem;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/interceptor/Interceptor.class */
public interface Interceptor {
    LogItem intercept(LogItem logItem);
}