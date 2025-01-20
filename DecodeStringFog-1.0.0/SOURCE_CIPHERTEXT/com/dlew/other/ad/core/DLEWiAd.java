package com.dlew.other.ad.core;

import android.app.Activity;
import com.dlew.other.ad.DLEWAdInfo;
import com.dlew.other.ad.DLEWLimitConfig;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/core/DLEWiAd.class */
public interface DLEWiAd {
    void init(DLEWLimitConfig dLEWLimitConfig, Activity activity);

    boolean isAvailable();

    String getRequestCode();

    String getNetworkName();

    double checkRevenue(String str);

    DLEWAdInfo getAdInfo();
}