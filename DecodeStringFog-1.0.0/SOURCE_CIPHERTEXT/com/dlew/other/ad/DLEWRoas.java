package com.dlew.other.ad;

import android.os.Bundle;
import com.appsflyer.AppsFlyerLib;
import com.dlew.StringFog;
import com.dlew.common.Arith;
import com.dlew.common.NumberUtil;
import com.dlew.common.SPUtil;
import com.dlew.lib.DLEWSDK;
import com.dlew.log.GGAnalysis;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWRoas.class */
public class DLEWRoas {
    private static double[] AdsLTVThresholds;
    private static final String CURRENT_ADS_LTV = StringFog.decrypt("6JYNyyghX/jqhwzGITtd\n", "q8NfmW1vC6c=\n");
    private static final String PREVIOUS_ADS_LTV = StringFog.decrypt("w75ETjFso2DMrUVLJ2+iZQ==\n", "k+wBGHgj9jM=\n");
    private static final String TAICHI_TROAS_CACHE = StringFog.decrypt("cVHouEbL7tl3X+CoUcHwzm1V\n", "JRCh+w6CsY0=\n");
    private static FirebaseAnalytics firebaseAnalytics;

    private static double getCurrentAdsLTV() {
        return Double.longBitsToDouble(SPUtil.getLong(CURRENT_ADS_LTV + DLEWSDK.getUserInfo().huoyueDays, 0L));
    }

    private static void setCurrentAdsLTV(long j) {
        SPUtil.putLong(CURRENT_ADS_LTV + DLEWSDK.getUserInfo().huoyueDays, j);
    }

    private static double getPreviousAdsLTV() {
        return Double.longBitsToDouble(SPUtil.getLong(PREVIOUS_ADS_LTV + DLEWSDK.getUserInfo().huoyueDays, 0L));
    }

    private static void setPreviousAdsLTV(long j) {
        SPUtil.putLong(PREVIOUS_ADS_LTV + DLEWSDK.getUserInfo().huoyueDays, j);
    }

    private static double getRoasCache() {
        return Double.longBitsToDouble(SPUtil.getLong(TAICHI_TROAS_CACHE, 0L));
    }

    private static void setRoasCache(long j) {
        SPUtil.putLong(TAICHI_TROAS_CACHE, j);
    }

    private static FirebaseAnalytics getFirebaseAnalytics() {
        if (firebaseAnalytics == null) {
            firebaseAnalytics = FirebaseAnalytics.getInstance(DLEWSDK.getApplication());
        }
        return firebaseAnalytics;
    }

    public static void SendMaxRevenueToFirebase(DLEWAdInfoExtra dLEWAdInfoExtra) {
        GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(DLEWSDK.getApplication());
    }

    public static void sendRevenueToAF(DLEWAdInfoExtra dLEWAdInfoExtra) {
        HashMap hashMap = new HashMap();
        hashMap.put(StringFog.decrypt("FDabqhHJ8jsbM70=\n", "dVDEyWS7gF4=\n"), StringFog.decrypt("xDns\n", "kWqoJfEtgMQ=\n"));
        hashMap.put(StringFog.decrypt("YqGC2Vpmsox2og==\n", "A8fdqz8Q1+I=\n"), Double.valueOf(dLEWAdInfoExtra.Revenue));
        GGAnalysis.afTrack(StringFog.decrypt("9r3CdtBWZI7EqvRQ03lEjsG880rY\n", "t9mdP70mFus=\n"), hashMap);
        trackKwaiArpuEvent(dLEWAdInfoExtra);
        trackKwaiEcpmEvent(dLEWAdInfoExtra);
    }

    private static void trackKwaiEcpmEvent(DLEWAdInfoExtra dLEWAdInfoExtra) {
        double mul = Arith.mul(dLEWAdInfoExtra.Revenue, 1000.0d);
        if (mul < 1.0d) {
            return;
        }
        double mul2 = Arith.mul(Math.floor(mul), 100.0d);
        HashMap hashMap = new HashMap();
        hashMap.put(StringFog.decrypt("efQPFPv76uZN5hgYyuTQ/nH3BxLKz/vmYuY=\n", "EoNufaSQj58=\n"), StringFog.decrypt("Uw==\n", "YQpdPfnHtUQ=\n"));
        hashMap.put(StringFog.decrypt("AnpgTdB2db82aHdB4WlPpwp5aEvhQmanBXhk\n", "aQ0BJI8dEMY=\n"), Double.valueOf(mul2));
        AppsFlyerLib.getInstance().logEvent(DLEWSDK.getApplication(), StringFog.decrypt("01mAYuI=\n", "tjrwD73iXt4=\n") + NumberUtil.getBestFitString(Math.floor(mul)), hashMap);
    }

    private static void trackKwaiArpuEvent(DLEWAdInfoExtra dLEWAdInfoExtra) {
        if (DLEWSDK.getUserInfo().huoyueDays == 1) {
            double d = SPUtil.getDouble(StringFog.decrypt("WBoTAK6L7nZHDAABqoE=\n", "HlNBU/rUqjc=\n"), 0.0d) + dLEWAdInfoExtra.Revenue;
            SPUtil.putDouble(StringFog.decrypt("1+YHsuyttrnI8BSz6Kc=\n", "ka9V4bjy8vg=\n"), d);
            if (d < 0.01d) {
                return;
            }
            double doubleValue = new BigDecimal(d).setScale(2, RoundingMode.DOWN).doubleValue();
            HashMap hashMap = new HashMap();
            hashMap.put(StringFog.decrypt("PuOBsuLjvBYK8Za+0/yGDjbgibTT160WJfE=\n", "VZTg272I2W8=\n"), StringFog.decrypt("6A==\n", "3PXDaAownpE=\n"));
            hashMap.put(StringFog.decrypt("tcj/e9lzYcqB2uh36Gxb0r3L933oR3LSssr7\n", "3r+eEoYYBLM=\n"), Double.valueOf(Arith.mul(doubleValue, 100.0d)));
            AppsFlyerLib.getInstance().logEvent(DLEWSDK.getApplication(), StringFog.decrypt("K9QIucTLo0Y9yCU=\n", "Tb16yrCUwjQ=\n") + NumberUtil.getBestFitString(doubleValue), hashMap);
        }
    }

    public static void CheckTaiChiTcpaAdRevenueEvent(double d) {
        String decrypt;
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(DLEWSDK.getApplication()) != 0) {
            return;
        }
        if (AdsLTVThresholds == null) {
            AdsLTVThresholds = DLEWSDK.getLTVThresholds();
        }
        double[] dArr = AdsLTVThresholds;
        if (dArr == null || dArr.length == 0) {
            return;
        }
        setCurrentAdsLTV(Double.doubleToLongBits(Double.valueOf(getCurrentAdsLTV() + d).doubleValue()));
        for (int i = 0; i < AdsLTVThresholds.length; i++) {
            if (getPreviousAdsLTV() < AdsLTVThresholds[i]) {
                double currentAdsLTV = getCurrentAdsLTV();
                if (currentAdsLTV >= AdsLTVThresholds[i]) {
                    int i2 = i;
                    setPreviousAdsLTV(Double.doubleToLongBits(currentAdsLTV));
                    Bundle bundle = new Bundle();
                    bundle.putDouble(StringFog.decrypt("UwLrh7A=\n", "JWOH8tXeKqQ=\n"), AdsLTVThresholds[i]);
                    bundle.putString(StringFog.decrypt("dqnC0GY8ZQI=\n", "FdywogNSBns=\n"), StringFog.decrypt("fN9s\n", "KYwoBTE8zuo=\n"));
                    StringFog.decrypt("XpT4v0STEEN6tNWSTZgwXSrA5I5grzpDaw==\n", "H/C06xLMXy0=\n");
                    switch (i2) {
                        case 0:
                            decrypt = StringFog.decrypt("Cw215QtwC9IvLZjIAnsrzHtZqdQvTCHSPg==\n", "Smn5sV0vRLw=\n");
                            break;
                        case 1:
                            decrypt = StringFog.decrypt("FZ4C43hZUHQxvi/OcVJwambKHtJcZXp0IA==\n", "VPpOty4GHxo=\n");
                            break;
                        case 2:
                            decrypt = StringFog.decrypt("9aTB/oXt34LRhOzTjOb/nIfw3c+h0fWCwA==\n", "tMCNqtOykOw=\n");
                            break;
                        case LogLevel.DEBUG /* 3 */:
                            decrypt = StringFog.decrypt("cE63wYkEdilUbprsgA9WNwUaq/CtOFwpRQ==\n", "MSr7ld9bOUc=\n");
                            break;
                        default:
                            decrypt = StringFog.decrypt("XHiYxTjsd4V4WLXoMedXmygshPQc0F2FaQ==\n", "HRzUkW6zOOs=\n");
                            break;
                    }
                    getFirebaseAnalytics().logEvent(decrypt, bundle);
                    GGAnalysis.afTrack(decrypt, null);
                    XLog.d(StringFog.decrypt("CNTYi/ig+8oM9Is=\n", "XLWxyJDJpIk=\n") + decrypt + StringFog.decrypt("hA==\n", "22WA0rc+6/8=\n") + AdsLTVThresholds[i]);
                }
            }
        }
    }

    public static void CheckTaichiTroasFirebaseAdRevenueEvent(double d) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(DLEWSDK.getApplication()) != 0) {
            return;
        }
        Bundle bundle = new Bundle();
        BigDecimal bigDecimal = new BigDecimal(d);
        Double valueOf = Double.valueOf(getRoasCache() + bigDecimal.doubleValue());
        setRoasCache(Double.doubleToLongBits(valueOf.doubleValue()));
        bundle.putDouble(StringFog.decrypt("oPtr2E4=\n", "1poHrStY/gc=\n"), bigDecimal.doubleValue());
        bundle.putString(StringFog.decrypt("XS53vIvEC94=\n", "PlsFzu6qaKc=\n"), StringFog.decrypt("mp2b\n", "z87f2a1EvwI=\n"));
        getFirebaseAnalytics().logEvent(StringFog.decrypt("YOswuguHB6Zy/Aa8CA==\n", "AY9v02b3dcM=\n"), bundle);
        getFirebaseAnalytics().logEvent(StringFog.decrypt("x6+OiY/kXLb1uLivjMt8tvCuv7WH\n", "hsvRwOKULtM=\n"), bundle);
        if (valueOf.doubleValue() >= 0.01d) {
            if (valueOf.doubleValue() > 1.0d) {
                setRoasCache(0L);
                XLog.d(StringFog.decrypt("ktfvg4Jo55SJ99X6Ar4/IH0SYE5j6TxJICM2Jmev\n", "xraGwOoBuMY=\n"));
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putDouble(StringFog.decrypt("nN67Plg=\n", "6r/XSz047Us=\n"), valueOf.doubleValue());
            bundle2.putString(StringFog.decrypt("h4oqa0xspwo=\n", "5P9YGSkCxHM=\n"), StringFog.decrypt("sVZV\n", "5AURbYTaqCc=\n"));
            getFirebaseAnalytics().logEvent(StringFog.decrypt("AGP1zJL4mDknU9PIiMK3KDFTsZ3P\n", "VAyBrf6n2V0=\n"), bundle2);
            setRoasCache(0L);
        }
    }

    public static void trackKwaiValueToAF(double d) {
    }
}