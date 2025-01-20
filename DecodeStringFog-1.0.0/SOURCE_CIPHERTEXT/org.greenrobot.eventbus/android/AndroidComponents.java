package org.greenrobot.eventbus.android;

import org.greenrobot.eventbus.Logger;
import org.greenrobot.eventbus.MainThreadSupport;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/android/AndroidComponents.class */
public abstract class AndroidComponents {
    private static final AndroidComponents implementation;
    public final Logger logger;
    public final MainThreadSupport defaultMainThreadSupport;

    public static boolean areAvailable() {
        return implementation != null;
    }

    public static AndroidComponents get() {
        return implementation;
    }

    public AndroidComponents(Logger logger, MainThreadSupport mainThreadSupport) {
        this.logger = logger;
        this.defaultMainThreadSupport = mainThreadSupport;
    }

    static {
        implementation = AndroidDependenciesDetector.isAndroidSDKAvailable() ? AndroidDependenciesDetector.instantiateAndroidComponents() : null;
    }
}