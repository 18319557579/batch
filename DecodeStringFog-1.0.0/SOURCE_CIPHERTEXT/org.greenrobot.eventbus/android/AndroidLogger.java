package org.greenrobot.eventbus.android;

import android.util.Log;
import java.util.logging.Level;
import org.greenrobot.eventbus.Logger;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/android/AndroidLogger.class */
public class AndroidLogger implements Logger {
    private final String tag;

    public AndroidLogger(String str) {
        this.tag = str;
    }

    private int mapLevel(Level level) {
        int intValue = level.intValue();
        if (intValue < 800) {
            return intValue < 500 ? 2 : 3;
        }
        if (intValue < 900) {
            return 4;
        }
        return intValue < 1000 ? 5 : 6;
    }

    @Override // org.greenrobot.eventbus.Logger
    public void log(Level level, String str) {
        if (level != Level.OFF) {
            Log.println(mapLevel(level), this.tag, str);
        }
    }

    @Override // org.greenrobot.eventbus.Logger
    public void log(Level level, String str, Throwable th) {
        if (level != Level.OFF) {
            Log.println(mapLevel(level), this.tag, str + "\n" + Log.getStackTraceString(th));
        }
    }
}