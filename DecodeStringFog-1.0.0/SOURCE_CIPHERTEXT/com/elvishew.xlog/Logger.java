package com.elvishew.xlog;

import com.elvishew.xlog.LogConfiguration;
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
import com.elvishew.xlog.internal.SystemCompat;
import com.elvishew.xlog.internal.util.StackTraceUtil;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.PrinterSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/Logger.class */
public class Logger {
    private LogConfiguration logConfiguration;
    private Printer printer;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/Logger$Builder.class */
    public static class Builder {
        private int logLevel;
        private String tag;
        private boolean withThread;
        private boolean threadSet;
        private boolean withStackTrace;
        private String stackTraceOrigin;
        private int stackTraceDepth;
        private boolean stackTraceSet;
        private boolean withBorder;
        private boolean borderSet;
        private JsonFormatter jsonFormatter;
        private XmlFormatter xmlFormatter;
        private ThrowableFormatter throwableFormatter;
        private ThreadFormatter threadFormatter;
        private StackTraceFormatter stackTraceFormatter;
        private BorderFormatter borderFormatter;
        private Map<Class<?>, ObjectFormatter<?>> objectFormatters;
        private List<Interceptor> interceptors;
        private Printer printer;

        public Builder() {
            XLog.assertInitialization();
        }

        public Builder logLevel(int i) {
            this.logLevel = i;
            return this;
        }

        public Builder tag(String str) {
            this.tag = str;
            return this;
        }

        @Deprecated
        public Builder t() {
            return enableThreadInfo();
        }

        public Builder enableThreadInfo() {
            this.withThread = true;
            this.threadSet = true;
            return this;
        }

        @Deprecated
        public Builder nt() {
            return disableThreadInfo();
        }

        public Builder disableThreadInfo() {
            this.withThread = false;
            this.threadSet = true;
            return this;
        }

        @Deprecated
        public Builder st(int i) {
            return enableStackTrace(i);
        }

        public Builder enableStackTrace(int i) {
            this.withStackTrace = true;
            this.stackTraceDepth = i;
            this.stackTraceSet = true;
            return this;
        }

        @Deprecated
        public Builder nst() {
            return disableStackTrace();
        }

        public Builder disableStackTrace() {
            this.withStackTrace = false;
            this.stackTraceOrigin = null;
            this.stackTraceDepth = 0;
            this.stackTraceSet = true;
            return this;
        }

        @Deprecated
        public Builder b() {
            return enableBorder();
        }

        public Builder enableBorder() {
            this.withBorder = true;
            this.borderSet = true;
            return this;
        }

        @Deprecated
        public Builder nb() {
            return disableBorder();
        }

        public Builder disableBorder() {
            this.withBorder = false;
            this.borderSet = true;
            return this;
        }

        public Builder jsonFormatter(JsonFormatter jsonFormatter) {
            this.jsonFormatter = jsonFormatter;
            return this;
        }

        public Builder xmlFormatter(XmlFormatter xmlFormatter) {
            this.xmlFormatter = xmlFormatter;
            return this;
        }

        public Builder throwableFormatter(ThrowableFormatter throwableFormatter) {
            this.throwableFormatter = throwableFormatter;
            return this;
        }

        public Builder threadFormatter(ThreadFormatter threadFormatter) {
            this.threadFormatter = threadFormatter;
            return this;
        }

        public Builder stackTraceFormatter(StackTraceFormatter stackTraceFormatter) {
            this.stackTraceFormatter = stackTraceFormatter;
            return this;
        }

        public Builder borderFormatter(BorderFormatter borderFormatter) {
            this.borderFormatter = borderFormatter;
            return this;
        }

        public <T> Builder addObjectFormatter(Class<T> cls, ObjectFormatter<? super T> objectFormatter) {
            if (this.objectFormatters == null) {
                this.objectFormatters = new HashMap(DefaultsFactory.builtinObjectFormatters());
            }
            this.objectFormatters.put(cls, objectFormatter);
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (this.interceptors == null) {
                this.interceptors = new ArrayList();
            }
            this.interceptors.add(interceptor);
            return this;
        }

        public Builder printers(Printer... printerArr) {
            if (printerArr.length == 0) {
                this.printer = null;
            } else if (printerArr.length == 1) {
                this.printer = printerArr[0];
            } else {
                this.printer = new PrinterSet(printerArr);
            }
            return this;
        }

        public void v(Object obj) {
            build().v(obj);
        }

        public void d(Object obj) {
            build().d(obj);
        }

        public void i(Object obj) {
            build().i(obj);
        }

        public void w(Object obj) {
            build().w(obj);
        }

        public void e(Object obj) {
            build().e(obj);
        }

        public void log(int i, Object obj) {
            build().log(i, obj);
        }

        public void json(String str) {
            build().json(str);
        }

        public void xml(String str) {
            build().xml(str);
        }

        public Logger build() {
            return new Logger(this);
        }

        @Deprecated
        public Builder st(String str, int i) {
            return enableStackTrace(str, i);
        }

        public void v(Object[] objArr) {
            build().v(objArr);
        }

        public void d(Object[] objArr) {
            build().d(objArr);
        }

        public void i(Object[] objArr) {
            build().i(objArr);
        }

        public void w(Object[] objArr) {
            build().w(objArr);
        }

        public void e(Object[] objArr) {
            build().e(objArr);
        }

        public void log(int i, Object[] objArr) {
            build().log(i, objArr);
        }

        public void v(String str, Object... objArr) {
            build().v(str, objArr);
        }

        public void d(String str, Object... objArr) {
            build().d(str, objArr);
        }

        public void i(String str, Object... objArr) {
            build().i(str, objArr);
        }

        public void w(String str, Object... objArr) {
            build().w(str, objArr);
        }

        public void e(String str, Object... objArr) {
            build().e(str, objArr);
        }

        public void log(int i, String str, Object... objArr) {
            build().log(i, str, objArr);
        }

        public Builder enableStackTrace(String str, int i) {
            this.withStackTrace = true;
            this.stackTraceOrigin = str;
            this.stackTraceDepth = i;
            this.stackTraceSet = true;
            return this;
        }

        public void v(String str) {
            build().v(str);
        }

        public void d(String str) {
            build().d(str);
        }

        public void i(String str) {
            build().i(str);
        }

        public void w(String str) {
            build().w(str);
        }

        public void e(String str) {
            build().e(str);
        }

        public void log(int i, String str) {
            build().log(i, str);
        }

        public void v(String str, Throwable th) {
            build().v(str, th);
        }

        public void d(String str, Throwable th) {
            build().d(str, th);
        }

        public void i(String str, Throwable th) {
            build().i(str, th);
        }

        public void w(String str, Throwable th) {
            build().w(str, th);
        }

        public void e(String str, Throwable th) {
            build().e(str, th);
        }

        public void log(int i, String str, Throwable th) {
            build().log(i, str, th);
        }
    }

    public Logger(LogConfiguration logConfiguration, Printer printer) {
        this.logConfiguration = logConfiguration;
        this.printer = printer;
    }

    private <T> void println(int i, T t) {
        String str;
        LogConfiguration logConfiguration = this.logConfiguration;
        if (i < logConfiguration.logLevel) {
            return;
        }
        if (t != null) {
            ObjectFormatter<? super T> objectFormatter = logConfiguration.getObjectFormatter(t);
            str = objectFormatter != null ? objectFormatter.format(t) : t.toString();
        } else {
            str = "null";
        }
        printlnInternal(i, str);
    }

    private void printlnInternal(int i, String str) {
        String str2;
        String str3;
        LogConfiguration logConfiguration = this.logConfiguration;
        String str4 = logConfiguration.tag;
        String format = logConfiguration.withThread ? logConfiguration.threadFormatter.format(Thread.currentThread()) : null;
        LogConfiguration logConfiguration2 = this.logConfiguration;
        if (logConfiguration2.withStackTrace) {
            StackTraceFormatter stackTraceFormatter = logConfiguration2.stackTraceFormatter;
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            LogConfiguration logConfiguration3 = this.logConfiguration;
            str2 = stackTraceFormatter.format(StackTraceUtil.getCroppedRealStackTrack(stackTrace, logConfiguration3.stackTraceOrigin, logConfiguration3.stackTraceDepth));
        } else {
            str2 = null;
        }
        if (this.logConfiguration.interceptors != null) {
            LogItem logItem = r1;
            LogItem logItem2 = new LogItem(i, str4, format, str2, str);
            for (Interceptor interceptor : this.logConfiguration.interceptors) {
                LogItem intercept = interceptor.intercept(logItem);
                logItem = intercept;
                if (intercept == null) {
                    return;
                }
                if (logItem.tag == null || logItem.msg == null) {
                    Platform.get().error("Interceptor " + interceptor + " should not remove the tag or message of a log, if you don't want to print this log, just return a null when intercept.");
                    return;
                }
            }
            LogItem logItem3 = logItem;
            i = logItem3.level;
            str4 = logItem3.tag;
            format = logItem3.threadInfo;
            str2 = logItem3.stackTraceInfo;
            str = logItem3.msg;
        }
        Printer printer = this.printer;
        LogConfiguration logConfiguration4 = this.logConfiguration;
        if (logConfiguration4.withBorder) {
            str3 = logConfiguration4.borderFormatter.format(new String[]{format, str2, str});
        } else {
            str3 = (format != null ? format + SystemCompat.lineSeparator : BuildConfig.VERSION_NAME) + (str2 != null ? str2 + SystemCompat.lineSeparator : BuildConfig.VERSION_NAME) + str;
        }
        printer.println(i, str4, str3);
    }

    private String formatArgs(String str, Object... objArr) {
        if (str != null) {
            return String.format(str, objArr);
        }
        StringBuilder sb = new StringBuilder();
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(objArr[i]);
        }
        return sb.toString();
    }

    public void v(Object obj) {
        println(2, (int) obj);
    }

    public void d(Object obj) {
        println(3, (int) obj);
    }

    public void i(Object obj) {
        println(4, (int) obj);
    }

    public void w(Object obj) {
        println(5, (int) obj);
    }

    public void e(Object obj) {
        println(6, (int) obj);
    }

    public void log(int i, Object obj) {
        println(i, (int) obj);
    }

    public void json(String str) {
        LogConfiguration logConfiguration = this.logConfiguration;
        if (3 < logConfiguration.logLevel) {
            return;
        }
        printlnInternal(3, logConfiguration.jsonFormatter.format(str));
    }

    public void xml(String str) {
        LogConfiguration logConfiguration = this.logConfiguration;
        if (3 < logConfiguration.logLevel) {
            return;
        }
        printlnInternal(3, logConfiguration.xmlFormatter.format(str));
    }

    public void v(Object[] objArr) {
        println(2, objArr);
    }

    public void d(Object[] objArr) {
        println(3, objArr);
    }

    public void i(Object[] objArr) {
        println(4, objArr);
    }

    public void w(Object[] objArr) {
        println(5, objArr);
    }

    public void e(Object[] objArr) {
        println(6, objArr);
    }

    public void log(int i, Object[] objArr) {
        println(i, objArr);
    }

    public void v(String str, Object... objArr) {
        println(2, str, objArr);
    }

    public void d(String str, Object... objArr) {
        println(3, str, objArr);
    }

    public void i(String str, Object... objArr) {
        println(4, str, objArr);
    }

    public void w(String str, Object... objArr) {
        println(5, str, objArr);
    }

    public void e(String str, Object... objArr) {
        println(6, str, objArr);
    }

    public void log(int i, String str, Object... objArr) {
        println(i, str, objArr);
    }

    public Logger(Builder builder) {
        LogConfiguration.Builder builder2 = new LogConfiguration.Builder(XLog.sLogConfiguration);
        if (builder.logLevel != 0) {
            builder2.logLevel(builder.logLevel);
        }
        if (builder.tag != null) {
            builder2.tag(builder.tag);
        }
        if (builder.threadSet) {
            if (builder.withThread) {
                builder2.enableThreadInfo();
            } else {
                builder2.disableThreadInfo();
            }
        }
        if (builder.stackTraceSet) {
            if (builder.withStackTrace) {
                builder2.enableStackTrace(builder.stackTraceOrigin, builder.stackTraceDepth);
            } else {
                builder2.disableStackTrace();
            }
        }
        if (builder.borderSet) {
            if (builder.withBorder) {
                builder2.enableBorder();
            } else {
                builder2.disableBorder();
            }
        }
        if (builder.jsonFormatter != null) {
            builder2.jsonFormatter(builder.jsonFormatter);
        }
        if (builder.xmlFormatter != null) {
            builder2.xmlFormatter(builder.xmlFormatter);
        }
        if (builder.throwableFormatter != null) {
            builder2.throwableFormatter(builder.throwableFormatter);
        }
        if (builder.threadFormatter != null) {
            builder2.threadFormatter(builder.threadFormatter);
        }
        if (builder.stackTraceFormatter != null) {
            builder2.stackTraceFormatter(builder.stackTraceFormatter);
        }
        if (builder.borderFormatter != null) {
            builder2.borderFormatter(builder.borderFormatter);
        }
        if (builder.objectFormatters != null) {
            builder2.objectFormatters(builder.objectFormatters);
        }
        if (builder.interceptors != null) {
            builder2.interceptors(builder.interceptors);
        }
        this.logConfiguration = builder2.build();
        if (builder.printer != null) {
            this.printer = builder.printer;
        } else {
            this.printer = XLog.sPrinter;
        }
    }

    public void v(String str) {
        println(2, str);
    }

    public void d(String str) {
        println(3, str);
    }

    public void i(String str) {
        println(4, str);
    }

    public void w(String str) {
        println(5, str);
    }

    public void e(String str) {
        println(6, str);
    }

    public void log(int i, String str) {
        println(i, str);
    }

    public void v(String str, Throwable th) {
        println(2, str, th);
    }

    public void d(String str, Throwable th) {
        println(3, str, th);
    }

    public void i(String str, Throwable th) {
        println(4, str, th);
    }

    public void w(String str, Throwable th) {
        println(5, str, th);
    }

    public void e(String str, Throwable th) {
        println(6, str, th);
    }

    public void log(int i, String str, Throwable th) {
        println(i, str, th);
    }

    private void println(int i, Object[] objArr) {
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        printlnInternal(i, Arrays.deepToString(objArr));
    }

    private void println(int i, String str, Object... objArr) {
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        printlnInternal(i, formatArgs(str, objArr));
    }

    public void println(int i, String str) {
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        if (str == null) {
            str = BuildConfig.VERSION_NAME;
        }
        printlnInternal(i, str);
    }

    private void println(int i, String str, Throwable th) {
        if (i < this.logConfiguration.logLevel) {
            return;
        }
        printlnInternal(i, ((str == null || str.length() == 0) ? BuildConfig.VERSION_NAME : str + SystemCompat.lineSeparator) + this.logConfiguration.throwableFormatter.format(th));
    }
}