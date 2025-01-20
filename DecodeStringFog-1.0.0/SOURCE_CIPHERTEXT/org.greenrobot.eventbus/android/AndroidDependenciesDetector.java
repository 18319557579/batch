package org.greenrobot.eventbus.android;

import java.lang.reflect.InvocationTargetException;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/android/AndroidDependenciesDetector.class */
public class AndroidDependenciesDetector {
    private static final String ANDROID_COMPONENTS_IMPLEMENTATION_CLASS_NAME = "org.greenrobot.eventbus.android.AndroidComponentsImpl";

    public static boolean isAndroidSDKAvailable() {
        try {
            return Class.forName("android.os.Looper").getDeclaredMethod("getMainLooper", new Class[0]).invoke(null, new Object[0]) != null;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (IllegalAccessException unused2) {
            return false;
        } catch (NoSuchMethodException unused3) {
            return false;
        } catch (InvocationTargetException unused4) {
            return false;
        }
    }

    public static boolean areAndroidComponentsAvailable() {
        try {
            Class.forName(ANDROID_COMPONENTS_IMPLEMENTATION_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static AndroidComponents instantiateAndroidComponents() {
        try {
            return (AndroidComponents) Class.forName(ANDROID_COMPONENTS_IMPLEMENTATION_CLASS_NAME).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }
}