package com.elvishew.xlog;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.Logger;
import com.elvishew.xlog.formatter.border.BorderFormatter;
import com.elvishew.xlog.formatter.message.json.JsonFormatter;
import com.elvishew.xlog.formatter.message.object.ObjectFormatter;
import com.elvishew.xlog.formatter.message.throwable.ThrowableFormatter;
import com.elvishew.xlog.formatter.message.xml.XmlFormatter;
import com.elvishew.xlog.formatter.stacktrace.StackTraceFormatter;
import com.elvishew.xlog.formatter.thread.ThreadFormatter;
import com.elvishew.xlog.interceptor.Interceptor;
import com.elvishew.xlog.internal.DefaultsFactory;
import com.elvishew.xlog.internal.Platform;
import com.elvishew.xlog.internal.util.StackTraceUtil;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.PrinterSet;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/XLog.class */
public class XLog {
    private static Logger sLogger;
    static LogConfiguration sLogConfiguration;
    static Printer sPrinter;
    static boolean sIsInitialized;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/XLog$Log.class */
    public static class Log {
        public static void v(String str, String str2) {
            XLog.tag(str).build().v(str2);
        }

        public static void d(String str, String str2) {
            XLog.tag(str).build().d(str2);
        }

        public static void i(String str, String str2) {
            XLog.tag(str).build().i(str2);
        }

        public static void w(String str, String str2) {
            XLog.tag(str).build().w(str2);
        }

        public static void e(String str, String str2) {
            XLog.tag(str).build().e(str2);
        }

        public static void wtf(String str, String str2) {
            e(str, str2);
        }

        public static void println(int i, String str, String str2) {
            XLog.tag(str).build().println(i, str2);
        }

        public static boolean isLoggable(String str, int i) {
            return XLog.sLogConfiguration.isLoggable(i);
        }

        public static String getStackTraceString(Throwable th) {
            return StackTraceUtil.getStackTraceString(th);
        }

        public static void v(String str, String str2, Throwable th) {
            XLog.tag(str).build().v(str2, th);
        }

        public static void d(String str, String str2, Throwable th) {
            XLog.tag(str).build().d(str2, th);
        }

        public static void i(String str, String str2, Throwable th) {
            XLog.tag(str).build().i(str2, th);
        }

        public static void w(String str, String str2, Throwable th) {
            XLog.tag(str).build().w(str2, th);
        }

        public static void e(String str, String str2, Throwable th) {
            XLog.tag(str).build().e(str2, th);
        }

        public static void wtf(String str, Throwable th) {
            wtf(str, BuildConfig.VERSION_NAME, th);
        }

        public static void w(String str, Throwable th) {
            XLog.tag(str).build().w(BuildConfig.VERSION_NAME, th);
        }

        public static void wtf(String str, String str2, Throwable th) {
            e(str, str2, th);
        }
    }

    private XLog() {
    }

    public static void init() {
        init(new LogConfiguration.Builder().build(), DefaultsFactory.createPrinter());
    }

    public static void assertInitialization() {
        if (!sIsInitialized) {
            throw new IllegalStateException("Do you forget to initialize XLog?");
        }
    }

    public static Logger.Builder logLevel(int i) {
        return new Logger.Builder().logLevel(i);
    }

    public static Logger.Builder tag(String str) {
        return new Logger.Builder().tag(str);
    }

    @Deprecated
    public static Logger.Builder t() {
        return enableThreadInfo();
    }

    public static Logger.Builder enableThreadInfo() {
        return new Logger.Builder().enableThreadInfo();
    }

    @Deprecated
    public static Logger.Builder nt() {
        return disableThreadInfo();
    }

    public static Logger.Builder disableThreadInfo() {
        return new Logger.Builder().disableThreadInfo();
    }

    @Deprecated
    public static Logger.Builder st(int i) {
        return enableStackTrace(i);
    }

    public static Logger.Builder enableStackTrace(int i) {
        return new Logger.Builder().enableStackTrace(i);
    }

    @Deprecated
    public static Logger.Builder nst() {
        return disableStackTrace();
    }

    public static Logger.Builder disableStackTrace() {
        return new Logger.Builder().disableStackTrace();
    }

    @Deprecated
    public static Logger.Builder b() {
        return enableBorder();
    }

    public static Logger.Builder enableBorder() {
        return new Logger.Builder().enableBorder();
    }

    @Deprecated
    public static Logger.Builder nb() {
        return disableBorder();
    }

    public static Logger.Builder disableBorder() {
        return new Logger.Builder().disableBorder();
    }

    public static Logger.Builder jsonFormatter(JsonFormatter jsonFormatter) {
        return new Logger.Builder().jsonFormatter(jsonFormatter);
    }

    public static Logger.Builder xmlFormatter(XmlFormatter xmlFormatter) {
        return new Logger.Builder().xmlFormatter(xmlFormatter);
    }

    public static Logger.Builder throwableFormatter(ThrowableFormatter throwableFormatter) {
        return new Logger.Builder().throwableFormatter(throwableFormatter);
    }

    public static Logger.Builder threadFormatter(ThreadFormatter threadFormatter) {
        return new Logger.Builder().threadFormatter(threadFormatter);
    }

    public static Logger.Builder stackTraceFormatter(StackTraceFormatter stackTraceFormatter) {
        return new Logger.Builder().stackTraceFormatter(stackTraceFormatter);
    }

    public static Logger.Builder borderFormatter(BorderFormatter borderFormatter) {
        return new Logger.Builder().borderFormatter(borderFormatter);
    }

    public static <T> Logger.Builder addObjectFormatter(Class<T> cls, ObjectFormatter<? super T> objectFormatter) {
        return new Logger.Builder().addObjectFormatter(cls, objectFormatter);
    }

    public static Logger.Builder addInterceptor(Interceptor interceptor) {
        return new Logger.Builder().addInterceptor(interceptor);
    }

    public static Logger.Builder printers(Printer... printerArr) {
        return new Logger.Builder().printers(printerArr);
    }

    public static void v(Object obj) {
        assertInitialization();
        sLogger.v(obj);
    }

    public static void d(Object obj) {
        assertInitialization();
        sLogger.d(obj);
    }

    public static void i(Object obj) {
        assertInitialization();
        sLogger.i(obj);
    }

    public static void w(Object obj) {
        assertInitialization();
        sLogger.w(obj);
    }

    public static void e(Object obj) {
        assertInitialization();
        sLogger.e(obj);
    }

    public static void log(int i, Object obj) {
        assertInitialization();
        sLogger.log(i, obj);
    }

    public static void json(String str) {
        assertInitialization();
        sLogger.json(str);
    }

    public static void xml(String str) {
        assertInitialization();
        sLogger.xml(str);
    }

    public static void init(int i) {
        init(new LogConfiguration.Builder().logLevel(i).build(), DefaultsFactory.createPrinter());
    }

    @Deprecated
    public static Logger.Builder st(String str, int i) {
        return enableStackTrace(str, i);
    }

    public static Logger.Builder enableStackTrace(String str, int i) {
        return new Logger.Builder().enableStackTrace(str, i);
    }

    public static void v(Object[] objArr) {
        assertInitialization();
        sLogger.v(objArr);
    }

    public static void d(Object[] objArr) {
        assertInitialization();
        sLogger.d(objArr);
    }

    public static void i(Object[] objArr) {
        assertInitialization();
        sLogger.i(objArr);
    }

    public static void w(Object[] objArr) {
        assertInitialization();
        sLogger.w(objArr);
    }

    public static void e(Object[] objArr) {
        assertInitialization();
        sLogger.e(objArr);
    }

    public static void log(int i, Object[] objArr) {
        assertInitialization();
        sLogger.log(i, objArr);
    }

    @Deprecated
    public static void init(int i, LogConfiguration logConfiguration) {
        init(new LogConfiguration.Builder(logConfiguration).logLevel(i).build());
    }

    public static void v(String str, Object... objArr) {
        assertInitialization();
        sLogger.v(str, objArr);
    }

    public static void d(String str, Object... objArr) {
        assertInitialization();
        sLogger.d(str, objArr);
    }

    public static void i(String str, Object... objArr) {
        assertInitialization();
        sLogger.i(str, objArr);
    }

    public static void w(String str, Object... objArr) {
        assertInitialization();
        sLogger.w(str, objArr);
    }

    public static void e(String str, Object... objArr) {
        assertInitialization();
        sLogger.e(str, objArr);
    }

    public static void log(int i, String str, Object... objArr) {
        assertInitialization();
        sLogger.log(i, str, objArr);
    }

    public static void init(LogConfiguration logConfiguration) {
        init(logConfiguration, DefaultsFactory.createPrinter());
    }

    public static void init(Printer... printerArr) {
        init(new LogConfiguration.Builder().build(), printerArr);
    }

    public static void v(String str) {
        assertInitialization();
        sLogger.v(str);
    }

    public static void d(String str) {
        assertInitialization();
        sLogger.d(str);
    }

    public static void i(String str) {
        assertInitialization();
        sLogger.i(str);
    }

    public static void w(String str) {
        assertInitialization();
        sLogger.w(str);
    }

    public static void e(String str) {
        assertInitialization();
        sLogger.e(str);
    }

    public static void log(int i, String str) {
        assertInitialization();
        sLogger.log(i, str);
    }

    public static void init(int i, Printer... printerArr) {
        init(new LogConfiguration.Builder().logLevel(i).build(), printerArr);
    }

    @Deprecated
    public static void init(int i, LogConfiguration logConfiguration, Printer... printerArr) {
        init(new LogConfiguration.Builder(logConfiguration).logLevel(i).build(), printerArr);
    }

    public static void v(String str, Throwable th) {
        assertInitialization();
        sLogger.v(str, th);
    }

    public static void d(String str, Throwable th) {
        assertInitialization();
        sLogger.d(str, th);
    }

    public static void i(String str, Throwable th) {
        assertInitialization();
        sLogger.i(str, th);
    }

    public static void w(String str, Throwable th) {
        assertInitialization();
        sLogger.w(str, th);
    }

    public static void e(String str, Throwable th) {
        assertInitialization();
        sLogger.e(str, th);
    }

    public static void log(int i, String str, Throwable th) {
        assertInitialization();
        sLogger.log(i, str, th);
    }

    public static void init(LogConfiguration logConfiguration, Printer... printerArr) {
        if (sIsInitialized) {
            Platform.get().warn("XLog is already initialized, do not initialize again");
        }
        sIsInitialized = true;
        if (logConfiguration != null) {
            sLogConfiguration = logConfiguration;
            PrinterSet printerSet = new PrinterSet(printerArr);
            sPrinter = printerSet;
            sLogger = new Logger(sLogConfiguration, printerSet);
            return;
        }
        throw new IllegalArgumentException("Please specify a LogConfiguration");
    }
}