package com.dlew.lib;

import androidx.annotation.Nullable;
import com.github.megatronking.stringfog.annotation.StringFogIgnore;
import org.greenrobot.eventbus.EventBus;

@StringFogIgnore
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWBus.class */
public class DLEWBus {
    public static void register(@Nullable Object obj) {
        EventBus.getDefault().register(obj);
    }

    public static void unregister(@Nullable Object obj) {
        EventBus.getDefault().unregister(obj);
    }

    public static void post(Object obj) {
        EventBus.getDefault().post(obj);
    }
}