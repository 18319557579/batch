package com.dlew.other.ad.core;

import android.content.Context;
import android.widget.FrameLayout;
import com.dlew.other.ad.DLEWAdThrottle;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/core/DLEWNativeAd.class */
public interface DLEWNativeAd {
    DLEWNativeAd show(DLEWAdThrottle dLEWAdThrottle, Context context, String str, String str2, FrameLayout frameLayout, DLEWNativeAdListener dLEWNativeAdListener);

    void destroy();
}