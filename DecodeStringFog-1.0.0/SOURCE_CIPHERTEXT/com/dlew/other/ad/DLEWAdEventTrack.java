package com.dlew.other.ad;

import android.text.TextUtils;
import com.dlew.StringFog;
import com.dlew.log.GGAnalysis;
import com.elvishew.xlog.BuildConfig;
import java.util.Hashtable;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAdEventTrack.class */
public class DLEWAdEventTrack {
    private static final String AD_SOURCE_ID = StringFog.decrypt("4kEs/wMetkmBIi7/AR8=\n", "shIezzEuhn0=\n");
    private static final String AD_TYPE = StringFog.decrypt("bA8eo652Ug==\n", "DWtB19cGN+Q=\n");
    private static final String REQUEST_CODE = StringFog.decrypt("12VrKbzxJZXKZH8=\n", "pQAaXNmCUdY=\n");
    private static final String ADS_SOURCE = StringFog.decrypt("ydzmLoToXO/L3Q==\n", "qLiVcfeHKZ0=\n");
    private static final String AD_ID = StringFog.decrypt("fawhhXw=\n", "HMh+7Bj1VpM=\n");
    private static final String EMPTY = null;

    public static void OnRequest(String str, String str2, String str3, String str4) {
        String decrypt = StringFog.decrypt("LrFWxHUU4qIvsEyDfQLylD2gTt5+\n", "XNQnsRBnlv0=\n");
        String decrypt2 = StringFog.decrypt("p7K2\n", "lIKHxPSdKk4=\n");
        Hashtable hashtable = new Hashtable();
        hashtable.put(AD_TYPE, str);
        hashtable.put(REQUEST_CODE, str2);
        String str5 = ADS_SOURCE;
        if (TextUtils.isEmpty(str4)) {
            str4 = StringFog.decrypt("NETlelklrA==\n", "QSqOFDZSwtM=\n");
        }
        hashtable.put(str5, str4);
        String str6 = AD_ID;
        if (TextUtils.isEmpty(str3)) {
            str3 = StringFog.decrypt("kpQIrQ==\n", "/OFkwcRlaLU=\n");
        }
        hashtable.put(str6, str3);
        GGAnalysis.track(decrypt, decrypt2, 0, 1, BuildConfig.VERSION_NAME, hashtable, false);
    }

    public static void OnLoaded(String str, String str2, String str3, String str4) {
        String decrypt = StringFog.decrypt("2C6hfVv36CPXJrl4a/S/NNos\n", "vkfNEQSajUc=\n");
        String decrypt2 = StringFog.decrypt("yZmb\n", "+qmt0Ys6jzI=\n");
        Hashtable hashtable = new Hashtable();
        hashtable.put(AD_TYPE, str);
        hashtable.put(REQUEST_CODE, str2);
        String str5 = ADS_SOURCE;
        if (TextUtils.isEmpty(str4)) {
            str4 = StringFog.decrypt("U4At8kmI9w==\n", "Ju5GnCb/mUo=\n");
        }
        hashtable.put(str5, str4);
        String str6 = AD_ID;
        if (TextUtils.isEmpty(str3)) {
            str3 = StringFog.decrypt("LZjZ4g==\n", "Q+21jqUAD4I=\n");
        }
        hashtable.put(str6, str3);
        GGAnalysis.track(decrypt, decrypt2, 0, 1, BuildConfig.VERSION_NAME, hashtable, false);
    }

    public static void OnLoadFailure(String str, String str2, String str3, String str4, String str5) {
        String decrypt = StringFog.decrypt("tG0PkJMMVNy7ZReVow8Dy7Zv\n", "0gRj/MxhMbg=\n");
        String decrypt2 = StringFog.decrypt("LCbI\n", "Hxb+S6Fimm4=\n");
        Hashtable hashtable = new Hashtable();
        hashtable.put(AD_TYPE, str);
        hashtable.put(REQUEST_CODE, str2);
        String str6 = ADS_SOURCE;
        if (TextUtils.isEmpty(str5)) {
            str5 = StringFog.decrypt("nF81m1Ue1w==\n", "6TFe9TppucI=\n");
        }
        hashtable.put(str6, str5);
        String str7 = AD_ID;
        if (TextUtils.isEmpty(str3)) {
            str3 = StringFog.decrypt("AQUa4g==\n", "b3B2jrhilww=\n");
        }
        hashtable.put(str7, str3);
        GGAnalysis.track(decrypt, decrypt2, 0, 0, str4, hashtable, false);
    }

    public static void OnImpression(String str, String str2, String str3, String str4) {
        String decrypt = StringFog.decrypt("Andbub8zYBkCd2ujvD5nEQV2W6DrKWob\n", "cR80ztlaDnA=\n");
        String decrypt2 = StringFog.decrypt("Xgvm\n", "bTrUgJW1d78=\n");
        Hashtable hashtable = new Hashtable();
        hashtable.put(AD_TYPE, str);
        hashtable.put(REQUEST_CODE, str2);
        String str5 = ADS_SOURCE;
        if (TextUtils.isEmpty(str4)) {
            str4 = StringFog.decrypt("NzTywsjVNw==\n", "QlqZrKeiWRU=\n");
        }
        hashtable.put(str5, str4);
        String str6 = AD_ID;
        if (TextUtils.isEmpty(str3)) {
            str3 = StringFog.decrypt("spNYAg==\n", "3OY0bmXu/Hk=\n");
        }
        hashtable.put(str6, str3);
        GGAnalysis.track(decrypt, decrypt2, 0, 1, BuildConfig.VERSION_NAME, hashtable, true);
    }

    public static void OnShowFailure(String str, String str2, String str3, String str4, String str5) {
        String decrypt = StringFog.decrypt("X3O8URZC9aFfc4xLFU/yqVhyvEhCWP+j\n", "LBvTJnArm8g=\n");
        String decrypt2 = StringFog.decrypt("BlGm\n", "NWCU+2+hB3A=\n");
        Hashtable hashtable = new Hashtable();
        hashtable.put(AD_TYPE, str);
        hashtable.put(REQUEST_CODE, str2);
        String str6 = ADS_SOURCE;
        if (TextUtils.isEmpty(str5)) {
            str5 = StringFog.decrypt("W7L1jJAWnQ==\n", "Ltye4v9h8/E=\n");
        }
        hashtable.put(str6, str5);
        String str7 = AD_ID;
        if (TextUtils.isEmpty(str3)) {
            str3 = StringFog.decrypt("mhp6yg==\n", "9G8Wpi1Klbg=\n");
        }
        hashtable.put(str7, str3);
        GGAnalysis.track(decrypt, decrypt2, 0, 0, str4, hashtable, false);
    }

    public static void OnClicked(String str, String str2, String str3, String str4) {
        String decrypt = StringFog.decrypt("dxxbBg/yx616GUEN\n", "FHAyZWStocQ=\n");
        String decrypt2 = StringFog.decrypt("Y33K\n", "UEz5e/JYkuQ=\n");
        Hashtable hashtable = new Hashtable();
        hashtable.put(AD_TYPE, str);
        hashtable.put(REQUEST_CODE, str2);
        String str5 = ADS_SOURCE;
        if (TextUtils.isEmpty(str4)) {
            str4 = StringFog.decrypt("At4+MX/d7A==\n", "d7BVXxCqggo=\n");
        }
        hashtable.put(str5, str4);
        String str6 = AD_ID;
        if (TextUtils.isEmpty(str3)) {
            str3 = StringFog.decrypt("L98F0w==\n", "Qappv9WLeZA=\n");
        }
        hashtable.put(str6, str3);
        GGAnalysis.track(decrypt, decrypt2, 0, 1, BuildConfig.VERSION_NAME, hashtable, true);
    }

    public static void OnRewarded(String str, String str2, String str3, String str4) {
        String decrypt = StringFog.decrypt("Gf7lgi8r6qQC9fuQNQ==\n", "a5uS411PtcI=\n");
        String decrypt2 = StringFog.decrypt("CH7m\n", "O0/So52SA5g=\n");
        Hashtable hashtable = new Hashtable();
        hashtable.put(AD_TYPE, str);
        hashtable.put(REQUEST_CODE, str2);
        String str5 = ADS_SOURCE;
        if (TextUtils.isEmpty(str4)) {
            str4 = StringFog.decrypt("HLmvXp55cA==\n", "adfEMPEOHuc=\n");
        }
        hashtable.put(str5, str4);
        String str6 = AD_ID;
        if (TextUtils.isEmpty(str3)) {
            str3 = StringFog.decrypt("JGbvFQ==\n", "ShODeRna2v4=\n");
        }
        hashtable.put(str6, str3);
        GGAnalysis.track(decrypt, decrypt2, 0, 1, BuildConfig.VERSION_NAME, hashtable, false);
    }

    public static void OnTriggeredSuccess(String str, String str2, String str3, String str4) {
        String decrypt = StringFog.decrypt("iewY7mWg1HOJ4BzGSbbHIp/3BA==\n", "+oR3mTrDpEE=\n");
        String decrypt2 = StringFog.decrypt("3+r6\n", "7NrNTdIqsLw=\n");
        Hashtable hashtable = new Hashtable();
        String str5 = AD_TYPE;
        if (TextUtils.isEmpty(str)) {
            str = StringFog.decrypt("VF0SyA==\n", "Oih+pD9hlzA=\n");
        }
        hashtable.put(str5, str);
        String str6 = REQUEST_CODE;
        if (TextUtils.isEmpty(str2)) {
            str2 = StringFog.decrypt("Dj8buQ==\n", "YEp31Xy/n90=\n");
        }
        hashtable.put(str6, str2);
        String str7 = ADS_SOURCE;
        if (TextUtils.isEmpty(str4)) {
            str4 = StringFog.decrypt("FOzcAg==\n", "epmwbomPutU=\n");
        }
        hashtable.put(str7, str4);
        String str8 = AD_ID;
        if (TextUtils.isEmpty(str3)) {
            str3 = StringFog.decrypt("VezfJw==\n", "O5mzS2ECL4E=\n");
        }
        hashtable.put(str8, str3);
        GGAnalysis.track(decrypt, decrypt2, 0, 1, BuildConfig.VERSION_NAME, hashtable, false);
    }

    public static void OnTriggeredFail(String str, String str2, String str3, String str4, String str5) {
        String decrypt = StringFog.decrypt("FEZN6IQRWCMUSknAvRNBfQ==\n", "Zy4in9tyKBE=\n");
        String decrypt2 = StringFog.decrypt("5+oQ\n", "1NonWvR7h9k=\n");
        Hashtable hashtable = new Hashtable();
        String str6 = AD_TYPE;
        if (TextUtils.isEmpty(str)) {
            str = StringFog.decrypt("O21UhQ==\n", "VRg46bhsVkg=\n");
        }
        hashtable.put(str6, str);
        String str7 = REQUEST_CODE;
        if (TextUtils.isEmpty(str2)) {
            str2 = StringFog.decrypt("Rmk6Jw==\n", "KBxWS4t7CDA=\n");
        }
        hashtable.put(str7, str2);
        String str8 = ADS_SOURCE;
        if (TextUtils.isEmpty(str5)) {
            str5 = StringFog.decrypt("4/D4TQ==\n", "jYWUIUfUWUs=\n");
        }
        hashtable.put(str8, str5);
        String str9 = AD_ID;
        if (TextUtils.isEmpty(str3)) {
            str3 = StringFog.decrypt("JR4TlQ==\n", "S2t/+Sm0KyM=\n");
        }
        hashtable.put(str9, str3);
        GGAnalysis.track(decrypt, decrypt2, 0, 0, str4, hashtable, false);
    }
}