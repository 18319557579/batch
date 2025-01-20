package com.dlew.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.dlew.StringFog;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/NetUtils.class */
public class NetUtils {
    private static final String NETWORK_NONE = StringFog.decrypt("QdIeTg==\n", "L71wKyIg2NY=\n");
    private static final String NETWORK_WIFI = StringFog.decrypt("f6qc2g==\n", "CMP6sysucLo=\n");
    private static final String NETWORK_2G = StringFog.decrypt("ClY=\n", "ODFYVw8R8A8=\n");
    private static final String NETWORK_3G = StringFog.decrypt("gwI=\n", "sGUmh751S+E=\n");
    private static final String NETWORK_4G = StringFog.decrypt("7l4=\n", "2jn8U9ybeNQ=\n");
    private static final String NETWORK_MOBILE = StringFog.decrypt("qZ+i61ej\n", "xPDAgjvGH40=\n");

    public static String getOperatorName(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(StringFog.decrypt("E2LKVZk=\n", "YwqlO/zWGi4=\n"));
        if (telephonyManager != null) {
            return telephonyManager.getSimOperatorName();
        }
        return null;
    }

    public static boolean isNetConnected(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        return (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService(StringFog.decrypt("tq7dNvbTkr6jqMch\n", "1cGzWJOw5tc=\n"))) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected() || activeNetworkInfo.getState() != NetworkInfo.State.CONNECTED) ? false : true;
    }

    public static synchronized boolean isWifiConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(StringFog.decrypt("JH2ffj9kibgxe4Vp\n", "RxLxEFoH/dE=\n"));
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return false;
        }
        int type = activeNetworkInfo.getType();
        if (type == 1 || type == 9) {
            return activeNetworkInfo.isConnected();
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [boolean] */
    public static boolean isNetworkOK(Context context) {
        ?? r0 = context;
        boolean z = false;
        if (r0 != 0) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(StringFog.decrypt("LTPbvruQ+uA4NcGp\n", "Tly10N7zjok=\n"));
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null) {
                        r0 = activeNetworkInfo.isConnected();
                        if (r0 != 0) {
                            z = true;
                        }
                    }
                }
            } catch (NoSuchFieldError unused) {
                r0.printStackTrace();
            }
        }
        return z;
    }
}