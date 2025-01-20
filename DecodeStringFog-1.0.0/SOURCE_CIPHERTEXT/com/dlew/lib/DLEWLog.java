package com.dlew.lib;

import com.elvishew.xlog.XLog;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWLog.class */
public class DLEWLog {
    public static void d(String str, Object... objArr) {
        XLog.d(str, objArr);
    }

    public static void e(String str, Object... objArr) {
        XLog.e(str, objArr);
    }

    public static void d(Object obj) {
        XLog.d(obj);
    }

    public static void e(Object obj) {
        XLog.e(obj);
    }
}