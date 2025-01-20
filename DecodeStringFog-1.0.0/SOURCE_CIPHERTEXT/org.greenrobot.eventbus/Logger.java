package org.greenrobot.eventbus;

import java.util.logging.Level;
import org.greenrobot.eventbus.android.AndroidComponents;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/Logger.class */
public interface Logger {

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/Logger$Default.class */
    public static class Default {
        public static Logger get() {
            return AndroidComponents.areAvailable() ? AndroidComponents.get().logger : new SystemOutLogger();
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/Logger$JavaLogger.class */
    public static class JavaLogger implements Logger {
        protected final java.util.logging.Logger logger;

        public JavaLogger(String str) {
            this.logger = java.util.logging.Logger.getLogger(str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str) {
            this.logger.log(level, str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str, Throwable th) {
            this.logger.log(level, str, th);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/Logger$SystemOutLogger.class */
    public static class SystemOutLogger implements Logger {
        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str) {
            System.out.println("[" + level + "] " + str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str, Throwable th) {
            System.out.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }

    void log(Level level, String str);

    void log(Level level, String str, Throwable th);
}