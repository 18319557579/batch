package com.dlew.log;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.appsflyer.AppsFlyerLib;
import com.dlew.StringFog;
import com.dlew.lib.DLEWSDK;
import com.elvishew.xlog.BuildConfig;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.JsonObject;
import java.util.Map;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/GGAnalysis.class */
public class GGAnalysis {
    public static void afTrack(String str, Map<String, Object> map) {
        AppsFlyerLib.getInstance().logEvent(DLEWSDK.getApplication(), str, map);
    }

    public static void firebaseTrack(String str, @Nullable Bundle bundle) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(DLEWSDK.getApplication()) == 0) {
            FirebaseAnalytics.getInstance(DLEWSDK.getApplication()).logEvent(str, bundle);
        }
    }

    public static void track(String str, String str2, int i, int i2, String str3, Map<String, Object> map, boolean z) {
        EventTrackManager.getInstance().SendUserEvent(str, str2, i, i2, str3, map, z);
    }

    public static void trackImmediately(String str) {
        EventTrackManager.getInstance().SendUserEvent(str, BuildConfig.VERSION_NAME, 0, 1, (String) null, (String) null, !DLEWSDK.isTrackThrottle());
    }

    public static void track(String str) {
        EventTrackManager.getInstance().SendUserEvent(str, BuildConfig.VERSION_NAME, 0, 1, (String) null, (String) null, false);
    }

    public static void trackImmediately(String str, Map<String, Object> map) {
        EventTrackManager.getInstance().SendUserEvent(str, BuildConfig.VERSION_NAME, 0, 1, (String) null, map, !DLEWSDK.isTrackThrottle());
    }

    public static void track(String str, Map<String, Object> map) {
        EventTrackManager.getInstance().SendUserEvent(str, BuildConfig.VERSION_NAME, 0, 1, (String) null, (Object) map, false);
    }

    public static void trackImmediately(String str, Object... objArr) {
        if (objArr != null) {
            if (objArr.length % 2 == 0) {
                JsonObject jsonObject = new JsonObject();
                for (int i = 0; i < objArr.length; i += 2) {
                    Object obj = objArr[i];
                    if (obj != null) {
                        String obj2 = obj.toString();
                        Object obj3 = objArr[i + 1];
                        jsonObject.addProperty(obj2, obj3 != null ? obj3.toString() : StringFog.decrypt("+eDIvg==\n", "l5Wk0hPGM7k=\n"));
                    }
                }
                EventTrackManager.getInstance().SendUserEvent(str, BuildConfig.VERSION_NAME, 0, 1, (String) null, jsonObject, !DLEWSDK.isTrackThrottle());
                return;
            }
            if (objArr.length != 1 || objArr[0] == null) {
                return;
            }
            EventTrackManager.getInstance().SendUserEvent(str, BuildConfig.VERSION_NAME, 0, 1, (String) null, objArr[0], !DLEWSDK.isTrackThrottle());
            return;
        }
        trackImmediately(str);
    }

    public static void track(String str, Object... objArr) {
        if (objArr != null) {
            if (objArr.length % 2 == 0) {
                JsonObject jsonObject = new JsonObject();
                for (int i = 0; i < objArr.length; i += 2) {
                    Object obj = objArr[i];
                    if (obj != null) {
                        String obj2 = obj.toString();
                        Object obj3 = objArr[i + 1];
                        jsonObject.addProperty(obj2, obj3 != null ? obj3.toString() : StringFog.decrypt("n/KwkA==\n", "8Yfc/Hol/qc=\n"));
                    }
                }
                EventTrackManager.getInstance().SendUserEvent(str, BuildConfig.VERSION_NAME, 0, 1, (String) null, (Object) jsonObject, false);
                return;
            }
            if (objArr.length != 1 || objArr[0] == null) {
                return;
            }
            EventTrackManager.getInstance().SendUserEvent(str, BuildConfig.VERSION_NAME, 0, 1, (String) null, objArr[0], false);
            return;
        }
        track(str);
    }
}