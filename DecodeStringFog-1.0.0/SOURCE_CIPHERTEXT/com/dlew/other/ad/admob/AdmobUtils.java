package com.dlew.other.ad.admob;

import com.dlew.StringFog;
import com.dlew.common.SPUtil;
import com.dlew.lib.DLEWSDK;
import com.dlew.other.ad.DLEWAd;
import com.dlew.other.ad.DLEWAdInfo;
import com.dlew.other.ad.DLEWAdThrottle;
import com.dlew.other.ad.DLEWLimitConfig;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import java.math.BigDecimal;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/AdmobUtils.class */
public class AdmobUtils {
    public static final String TYPE_REWARD = StringFog.decrypt("0f19cPBArYs=\n", "o5gKEYIkyO8=\n");
    public static final String TYPE_APP_OPEN = StringFog.decrypt("xT0Sf5JJEhg=\n", "pE1iIP05d3Y=\n");
    public static final String TYPE_INS = StringFog.decrypt("L/ZT\n", "RpggWurIS2c=\n");
    public static final String REVENUE = StringFog.decrypt("7hpxjaNcCg==\n", "nH8H6M0pbww=\n");
    static final String ADMOB_DAILY_AD_COUNT = StringFog.decrypt("3OYsFbtdXznU7jgFuEZEO9L3Lw4=\n", "naJhWvkCG3g=\n");
    private static final String FIRST_ADMOB_SHOW = StringFog.decrypt("P2ImtiA/m8Y0ZDa6JyiV1Q==\n", "eSt05XRg2oI=\n");
    private static String networkName = StringFog.decrypt("dcvktMw1u8Fgxv++\n", "FK+J265q1aA=\n");

    public static boolean needShowAdmob(DLEWLimitConfig dLEWLimitConfig) {
        int i = dLEWLimitConfig.firstShow;
        int i2 = i;
        int i3 = dLEWLimitConfig.intervalShow;
        if (i <= 0) {
            XLog.d(StringFog.decrypt("ku+pVf9mT2TaPGzRFYmIvx0ve8gpg8eunfueWtZKTWjhuqwGlVckuBAwZ96eUyUxwe3hGuwNBHiR\nz4RV7V9BQ+C4uSmdTxIw9ObgAus=\n", "dF0IvHrrqNk=\n"));
            return false;
        }
        int dailyFirstAdmob = getDailyFirstAdmob();
        if (dailyFirstAdmob == 0 && DLEWAd.getAdDisplayDailyCount() > i2) {
            XLog.d(StringFog.decrypt("zQ0+HzWdJ0SIUwVvdpB7HpYx1Z78W6MZlTrVnvxboxGFEFFqGdJcV80NAg==\n", "Kba0+pE0wfY=\n"));
            return true;
        }
        if ((DLEWAd.getAdDisplayDailyCount() + 1) - i2 == 0) {
            XLog.d(StringFog.decrypt("KNlTLvMXfkusLbanDFE2Vv9Sgtt2XH4J8T3Y7TAVY3iuGYSnBkY0cfM=\n", "Sb0+QZHw0u0=\n"));
            return true;
        }
        if (dailyFirstAdmob > 0) {
            setAdmobFirstShow();
            i2 = dailyFirstAdmob;
        }
        if (i3 <= 0) {
            XLog.d(StringFog.decrypt("ljFRNPWA78be4pSwH2/h7MRqakmVvJ2c1DkfYfxsbBYf4RRl/eW9y5kURDTqme3K5WRUZ5mNs5PO\nEg==\n", "cIPw3XANCHs=\n"));
            return false;
        }
        if (DLEWAd.getAdDisplayDailyCount() - i2 <= 0 || ((DLEWAd.getAdDisplayDailyCount() + 1) - i2) % (i3 + 1) != 0) {
            return false;
        }
        XLog.d(StringFog.decrypt("6CGYnXcA/T5s1X0UiEa1Iz+qSWj8cOVxE9E=\n", "iUX18hXnUZg=\n") + i3 + StringFog.decrypt("SP4WCDvS5PgUtCBbbNu5\n", "rlK37YpHA1w=\n"));
        return true;
    }

    public static double checkRevenue(DLEWAdThrottle dLEWAdThrottle, DLEWAdInfo dLEWAdInfo, boolean z, String str, String str2) {
        XLog.d(StringFog.decrypt("2FS4XRRqqBSF2huLustx\n", "P+8ru4r2TKw=\n") + changeLongRevenueToDouble(5000L));
        if (dLEWAdThrottle.CheckNetworkNameControlled(dLEWAdInfo.networkName) && dLEWAdThrottle.CheckThrottle(str)) {
            return -1.0d;
        }
        if (!z) {
            XLog.d(StringFog.decrypt("vXfQXI6IAHm6cMlczYUoe6x01kqq/hp0u0Q=\n", "yBm5KPelcxo=\n") + dLEWAdInfo.unitID + StringFog.decrypt("Q2PQfuePzQHP7xjnvvbwbYXZXbm9gIRK+w==\n", "Y079XgETYec=\n"));
            return dLEWAdThrottle.CheckThrottle(str) ? -1.0d : 0.0d;
        }
        double d = dLEWAdThrottle.getConfig().defaultPrice;
        if (!needShowAdmob(dLEWAdThrottle.getConfig())) {
            if (dLEWAdThrottle.getConfig().firstShow > 0) {
                return -1.0d;
            }
            double d2 = dLEWAdInfo.revenue;
            if (d2 > 0.0d) {
                d = d2;
            }
            return d;
        }
        double d3 = dLEWAdInfo.revenue;
        if (d3 > 0.0d && d > 0.0d) {
            d = d3;
        }
        if (d <= 0.0d) {
            d = 100000.0d;
        }
        return d;
    }

    public static String getNetworkName() {
        return networkName;
    }

    public static void setAdmobFirstShow() {
        if (getDailyFirstAdmob() == 0) {
            setDailyFirstAdmob(DLEWAd.getAdDisplayDailyCount());
        }
    }

    public static double changeLongRevenueToDouble(long j) {
        return new BigDecimal(j).divide(new BigDecimal(1000000)).doubleValue();
    }

    private static double getDouble4Str(String str, double d) {
        try {
            return Double.parseDouble(SPUtil.getString(str, StringFog.decrypt("ag==\n", "WnhLmgi5cSQ=\n")));
        } catch (Exception unused) {
            return d;
        }
    }

    public static double getAdmobRewardLastRevenue() {
        return getDouble4Str(StringFog.decrypt("BhFmY/dUbKoGEWdn60Vs\n", "dHQRAoUwCc4=\n"), 0.0d);
    }

    public static void setAdmobRewardLastRevenue(double d) {
        SPUtil.putString(StringFog.decrypt("8F4BA3UzO+7wXgAHaSI7\n", "gjt2YgdXXoo=\n"), String.valueOf(d));
    }

    public static double getAdmobInterLastRevenue() {
        return getDouble4Str(StringFog.decrypt("e09aKh/ZdKJnRA==\n", "EiEpWHqvEcw=\n"), 0.0d);
    }

    public static void setAdmobInterLastRevenue(double d) {
        SPUtil.putString(StringFog.decrypt("vptu9RwmxN6ikA==\n", "1/Udh3lQobA=\n"), String.valueOf(d));
    }

    public static double getAdmobOpenLastRevenue() {
        return getDouble4Str(StringFog.decrypt("lu3OYWic1BeF+MhbaZnU\n", "952+PgfssXk=\n"), 0.0d);
    }

    public static void setAdmobOpenLastRevenue(double d) {
        SPUtil.putString(StringFog.decrypt("T48FbQ1fN7FcmgNXDFo3\n", "Lv91MmIvUt8=\n"), d + BuildConfig.VERSION_NAME);
    }

    public static int getDailyFirstAdmob() {
        int i = SPUtil.getInt(FIRST_ADMOB_SHOW + DLEWSDK.getUserInfo().huoyueDays, 0);
        if (i > 0) {
            XLog.d(StringFog.decrypt("7umWwylofKlnPX7BIW3ZdYq0sIdocKgqruj6viIkoWXv66PDHEvaYaY=\n", "ClIcJo3BPc0=\n") + i + StringFog.decrypt("JTKn6j18t8B5eJG5\n", "w54GD4zpUGQ=\n"));
        }
        return i;
    }

    public static void setDailyFirstAdmob(int i) {
        XLog.d(StringFog.decrypt("dQtA1ZGp2yH836jXmax+/RFWZpHQsQ+iNQosqJrlBu10CXXVpIp96T0=\n", "kbDKMDUAmkU=\n") + i + StringFog.decrypt("lKd0Gv+w74zI7UJJ\n", "cgvV/04lCCg=\n"));
        SPUtil.putInt(FIRST_ADMOB_SHOW + DLEWSDK.getUserInfo().huoyueDays, i);
    }
}