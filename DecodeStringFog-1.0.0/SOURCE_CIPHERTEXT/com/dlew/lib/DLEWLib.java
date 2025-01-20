package com.dlew.lib;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.appsflyer.AppsFlyerLib;
import com.dlew.common.ServerTimeUtil;
import com.dlew.hardware.PhoneInfoWrapper;
import com.dlew.log.EventTrackManager;
import com.dlew.log.GGAnalysis;
import com.dlew.net.GameNet;
import com.dlew.net.StrategyNet;
import com.dlew.net.UMKNet;
import com.dlew.net.bean.DLEWABData;
import com.dlew.net.bean.DLEWBaseRsp;
import com.dlew.net.bean.DLEWSettingInfo;
import com.dlew.net.bean.DLEWUInfo;
import com.dlew.net.util.DLEWNetCallback;
import com.dlew.net.util.DLEWRequestCompleted;
import com.dlew.other.af.AFWrapper;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWLib.class */
public class DLEWLib {
    public static void init(Activity activity, DLEWNetCallback<DLEWUInfo> dLEWNetCallback) {
        DLEWSDK.Init(activity, dLEWNetCallback);
    }

    public static void onApplicationCreate(Application application) {
        DLEWSDK.onApplicationCreate(application);
    }

    public static void onApplicationTerminate(Application application) {
        DLEWSDK.onApplicationTerminate(application);
    }

    public static void addNotification(String str, float f, String str2, String str3, String str4, @Nullable DLEWNetCallback<DLEWBaseRsp> dLEWNetCallback) {
        UMKNet.getInstance().AddNotification(str, f, str2, str3, str4, dLEWNetCallback);
    }

    public static void removeNotification(@NonNull String str, @Nullable DLEWNetCallback<DLEWBaseRsp> dLEWNetCallback) {
        UMKNet.getInstance().RemoveNotification(str, dLEWNetCallback);
    }

    public static void fetchRemoteConfig(String str, DLEWNetCallback<DLEWSettingInfo[]> dLEWNetCallback) {
        GameNet.getInstance().FetchConfig(str, dLEWNetCallback);
    }

    public static void fetchRemoteStrategy(String str, DLEWNetCallback<DLEWABData[]> dLEWNetCallback) {
        StrategyNet.getInstance().Fetch(str, dLEWNetCallback);
    }

    public static String generateInviteLink(String str) {
        return AFWrapper.generateInviteLink(DLEWConfig.getOneLinkID(), str);
    }

    public static void track(String str, @Nullable Object... objArr) {
        GGAnalysis.track(str, objArr);
    }

    public static void trackImmediately(String str, @Nullable Object... objArr) {
        GGAnalysis.trackImmediately(str, objArr);
    }

    public static void trackToAF(String str, Map<String, Object> map) {
        AppsFlyerLib.getInstance().logEvent(DLEWSDK.getApplication(), str, map);
    }

    public static void trackToFirebase(String str, @Nullable Bundle bundle) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(DLEWSDK.getApplication()) == 0) {
            FirebaseAnalytics.getInstance(DLEWSDK.getApplication()).logEvent(str, bundle);
        }
    }

    public static DLEWUInfo getUserInfo() {
        return DLEWSDK.getUserInfo();
    }

    public static String getChannel() {
        return PhoneInfoWrapper.getConversionData().channel;
    }

    public static String getGAID() {
        return PhoneInfoWrapper.getGAID();
    }

    public static long getRealServerTimestamp() {
        return ServerTimeUtil.getRealTimeStamp();
    }

    public static boolean isToday() {
        return ServerTimeUtil.isToday();
    }

    public static <T> void post(Object obj, String str, DLEWRequestCompleted<T> dLEWRequestCompleted) {
        GameNet.getInstance().PostAsync(obj, str, dLEWRequestCompleted);
    }

    public static String getStrategyId() {
        return EventTrackManager.getInstance().strategyId;
    }

    public static void tryRestartEva() {
    }
}