package com.dlew.common;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.dlew.StringFog;
import com.dlew.lib.DLEWSDK;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import com.google.gson.Gson;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/SPUtil.class */
public class SPUtil {
    private static SharedPreferences spInstance;
    private static String name = StringFog.decrypt("Da+B\n", "f8HgWR/WOaA=\n");

    public static void setName(String str) {
        name = str;
        DLEWSDK.getApplication().getSharedPreferences(StringFog.decrypt("xDOhcw==\n", "plLSFmWQ36A=\n"), 0).edit().putString(StringFog.decrypt("06BEfmb+b/rNtEt3\n", "o9UmEg+NB6U=\n"), name);
    }

    public static SharedPreferences getSharedPreferences() {
        if (DLEWSDK.getApplication() == null) {
            XLog.e(StringFog.decrypt("VPW3ciwsk3chv6ccTCjgjNgx\n", "vFoAl6mkdv8=\n"));
            return null;
        }
        if (spInstance == null) {
            if (TextUtils.isEmpty(name)) {
                name = DLEWSDK.getApplication().getSharedPreferences(StringFog.decrypt("tFc+Lg==\n", "1jZNSygqGMA=\n"), 0).getString(StringFog.decrypt("LXyVWJIFsIQzaJpR\n", "XQn3NPt22Ns=\n"), StringFog.decrypt("h7T7\n", "9dqaWozBRac=\n"));
            }
            spInstance = DLEWSDK.getApplication().getSharedPreferences(name, 0);
        }
        return spInstance;
    }

    public static String getString(String str, String str2) {
        return getSharedPreferences().getString(str, str2);
    }

    public static void putString(String str, String str2) {
        getSharedPreferences().edit().putString(str, str2).apply();
    }

    public static int getInt(String str, int i) {
        return getSharedPreferences().getInt(str, i);
    }

    public static void putInt(String str, int i) {
        getSharedPreferences().edit().putInt(str, i).apply();
    }

    public static float getFloat(String str, float f) {
        return getSharedPreferences().getFloat(str, f);
    }

    public static void putFloat(String str, float f) {
        getSharedPreferences().edit().putFloat(str, f).apply();
    }

    public static boolean getBoolean(String str, boolean z) {
        return getSharedPreferences().getBoolean(str, z);
    }

    public static void putBoolean(String str, boolean z) {
        getSharedPreferences().edit().putBoolean(str, z).apply();
    }

    public static long getLong(String str, long j) {
        return getSharedPreferences().getLong(str, j);
    }

    public static void putLong(String str, long j) {
        getSharedPreferences().edit().putLong(str, j).apply();
    }

    public static double getDouble(String str, double d) {
        return Double.longBitsToDouble(getSharedPreferences().getLong(str, Double.doubleToLongBits(d)));
    }

    public static void putDouble(String str, double d) {
        getSharedPreferences().edit().putLong(str, Double.doubleToLongBits(d)).apply();
    }

    public static <T> T getObject(String str, Class cls) {
        Gson gson = new Gson();
        String string = getString(str, BuildConfig.VERSION_NAME);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return (T) gson.fromJson(string, cls);
    }

    public static void putObject(String str, Object obj) {
        if (obj == null) {
            getSharedPreferences().edit().remove(str).apply();
        } else {
            getSharedPreferences().edit().putString(str, new Gson().toJson(obj)).apply();
        }
    }
}