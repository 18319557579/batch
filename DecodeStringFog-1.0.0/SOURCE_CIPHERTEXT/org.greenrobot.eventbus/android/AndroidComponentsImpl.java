package org.greenrobot.eventbus.android;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/android/AndroidComponentsImpl.class */
public class AndroidComponentsImpl extends AndroidComponents {
    public AndroidComponentsImpl() {
        super(new AndroidLogger("EventBus"), new DefaultAndroidMainThreadSupport());
    }
}