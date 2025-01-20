package com.elvishew.xlog.internal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.elvishew.xlog.formatter.message.object.BundleFormatter;
import com.elvishew.xlog.formatter.message.object.IntentFormatter;
import com.elvishew.xlog.formatter.message.object.ObjectFormatter;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.Printer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/internal/Platform.class */
public class Platform {
    private static final Platform PLATFORM = findPlatform();

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/internal/Platform$EvLveE1.class */
    public static class EvLveE1 extends Platform {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public static final Map<Class<?>, ObjectFormatter<?>> f72EvLveE1;

        static {
            HashMap hashMap = new HashMap();
            hashMap.put(Bundle.class, new BundleFormatter());
            hashMap.put(Intent.class, new IntentFormatter());
            f72EvLveE1 = Collections.unmodifiableMap(hashMap);
        }

        @Override // com.elvishew.xlog.internal.Platform
        public String lineSeparator() {
            return Build.VERSION.SDK_INT < 19 ? "\n" : System.lineSeparator();
        }

        @Override // com.elvishew.xlog.internal.Platform
        public Printer defaultPrinter() {
            return new AndroidPrinter();
        }

        @Override // com.elvishew.xlog.internal.Platform
        public Map<Class<?>, ObjectFormatter<?>> builtinObjectFormatters() {
            return f72EvLveE1;
        }

        @Override // com.elvishew.xlog.internal.Platform
        public void warn(String str) {
            Log.w("XLog", str);
        }

        @Override // com.elvishew.xlog.internal.Platform
        public void error(String str) {
            Log.e("XLog", str);
        }
    }

    public static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new EvLveE1();
            }
        } catch (ClassNotFoundException unused) {
        }
        return new Platform();
    }

    @SuppressLint({"NewApi"})
    public String lineSeparator() {
        return System.lineSeparator();
    }

    public Printer defaultPrinter() {
        return new ConsolePrinter();
    }

    public Map<Class<?>, ObjectFormatter<?>> builtinObjectFormatters() {
        return Collections.emptyMap();
    }

    public void warn(String str) {
        System.out.println(str);
    }

    public void error(String str) {
        System.out.println(str);
    }
}