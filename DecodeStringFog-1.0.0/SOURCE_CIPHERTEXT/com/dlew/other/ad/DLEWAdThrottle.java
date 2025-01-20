package com.dlew.other.ad;

import android.text.TextUtils;
import com.dlew.StringFog;
import com.dlew.common.SPUtil;
import com.dlew.hardware.PhoneInfoWrapper;
import com.dlew.lib.DLEWSDK;
import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAdThrottle.class */
public class DLEWAdThrottle {
    private String m_storeKey;
    private DLEWLimitConfig m_limitConfig;
    final String THROTTLE_DAILY_AD_COUNT = StringFog.decrypt("TaT6Du78qqVDvPYK9eeoo1qr/Q==\n", "D+WpS7G46+w=\n");
    final String THROTTLE_DAILY_AD_CLICK_COUNT = StringFog.decrypt("KvrCm5F29jgx+Meei2LoPyHuxYM=\n", "bruL18gpt3w=\n");
    final String DAILY_AD_MULTI_CLICK_COUNT = StringFog.decrypt("VuRCgk6b3FRN6F6CQ43CU17sSIVIh9JFXPE=\n", "EqULzhfEnRA=\n");

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAdThrottle$EvLveE1.class */
    public class EvLveE1 extends TypeToken<List<Integer>> {
        public EvLveE1() {
        }
    }

    public DLEWAdThrottle(DLEWLimitConfig dLEWLimitConfig) {
        this.m_storeKey = dLEWLimitConfig.videoID + dLEWLimitConfig.insID;
        this.m_limitConfig = dLEWLimitConfig;
    }

    public int getThrottleDailyShowCount() {
        return SPUtil.getInt(this.m_storeKey + this.THROTTLE_DAILY_AD_COUNT + DLEWSDK.getUserInfo().huoyueDays, 0);
    }

    public int getThrottleDailyClickCount() {
        return SPUtil.getInt(this.m_storeKey + this.THROTTLE_DAILY_AD_CLICK_COUNT + DLEWSDK.getUserInfo().huoyueDays, 0);
    }

    public List<Integer> getDailyMultiClickCount() {
        String string = SPUtil.getString(this.m_storeKey + this.DAILY_AD_MULTI_CLICK_COUNT + DLEWSDK.getUserInfo().huoyueDays, null);
        if (!TextUtils.isEmpty(string)) {
            return (List) new Gson().fromJson(string, new EvLveE1().getType());
        }
        ArrayList arrayList = new ArrayList();
        SPUtil.putObject(this.m_storeKey + this.DAILY_AD_MULTI_CLICK_COUNT + DLEWSDK.getUserInfo().huoyueDays, arrayList);
        return arrayList;
    }

    public DLEWLimitConfig getConfig() {
        return this.m_limitConfig;
    }

    public boolean CheckThrottle(String str) {
        DLEWLimitConfig dLEWLimitConfig = this.m_limitConfig;
        if (dLEWLimitConfig.isDefault) {
            return false;
        }
        if (!TextUtils.isEmpty(dLEWLimitConfig.adSceneStr) && !TextUtils.isEmpty(str) && this.m_limitConfig.adSceneStr.toLowerCase().indexOf(str.toLowerCase()) < 0) {
            XLog.d(StringFog.decrypt("9ueOwqTa5kHx4JfC5w==\n", "g4nntt33lSI=\n") + this.m_limitConfig.videoID + StringFog.decrypt("ZvpfyMUF\n", "g2blLlyqOhA=\n") + str + StringFog.decrypt("QbwDcknf23Ao4zM5Mc+f\n", "pQSOl9V3MvU=\n"));
            return true;
        }
        if (this.m_limitConfig.maxDisplay >= 0 && getThrottleDailyShowCount() >= this.m_limitConfig.maxDisplay) {
            XLog.d(StringFog.decrypt("Tv7D4G45vuhJ+drgLfB2Ad0HD3Ggpig6rncOLg==\n", "O5CqlBcUzYs=\n") + getThrottleDailyShowCount() + StringFog.decrypt("0+iZziUpu0CLobCRcCDeGYjq3ZAMQvdE0fyyyAA1aQ==\n", "NUQ4IZmlU/4=\n") + this.m_limitConfig.maxDisplay);
            return true;
        }
        if (!TextUtils.isEmpty(this.m_limitConfig.invalidChannelStr) && this.m_limitConfig.invalidChannelStr.toLowerCase().indexOf(PhoneInfoWrapper.getConversionData().channel.toLowerCase()) >= 0) {
            XLog.d(StringFog.decrypt("0erWvZYNiWTW7c+91Q==\n", "pIS/ye8g+gc=\n") + this.m_limitConfig.videoID + StringFog.decrypt("4EErVG82AMRe\n", "zWwLsteW6UU=\n") + PhoneInfoWrapper.getConversionData().channel + StringFog.decrypt("xv1xFmm+tjKY\n", "Ll/a//AuU7o=\n"));
            return true;
        }
        if (getThrottleDailyShowCount() >= this.m_limitConfig.illegalClickRate_MinCount && ((getThrottleDailyClickCount() * 1.0f) / getThrottleDailyShowCount()) * 100.0f >= this.m_limitConfig.illegalClickRate_MaxRate) {
            XLog.d(StringFog.decrypt("K8ijEv46Dq4sz7oSvQ==\n", "XqbKZocXfc0=\n") + this.m_limitConfig.videoID + StringFog.decrypt("gRSymRF3rKIW3Sj6\n", "rDmSfKDiSwY=\n") + getThrottleDailyShowCount() + StringFog.decrypt("+wKjtdZW7SKkS4XhjVSN\n", "Ha4CWmraCqA=\n") + (((getThrottleDailyClickCount() * 1.0f) / getThrottleDailyShowCount()) * 100.0f) + StringFog.decrypt("Gg==\n", "P/WBEzAv/Mo=\n"));
            return true;
        }
        List<Integer> dailyMultiClickCount = getDailyMultiClickCount();
        int i = 0;
        for (int i2 = 0; i2 < dailyMultiClickCount.size(); i2++) {
            if (dailyMultiClickCount.get(i2).intValue() >= this.m_limitConfig.illegalMultiClick_MaxClickCount) {
                i++;
            }
        }
        if (i < this.m_limitConfig.illegalMultiClick_MinShowCount) {
            return false;
        }
        XLog.d(StringFog.decrypt("7bCXGou73pPqt44ayA==\n", "mN7+bvKWrfA=\n") + this.m_limitConfig.videoID + StringFog.decrypt("AZ7P\n", "LLPvgp2tnUE=\n") + i + StringFog.decrypt("hKtVfR/QrS/Y920jZuzPRd6V\n", "YRLqmI5aSq0=\n") + this.m_limitConfig.illegalMultiClick_MaxClickCount);
        return true;
    }

    public boolean CheckNetworkNameControlled(String str) {
        String[] strArr;
        if (TextUtils.isEmpty(str) || (strArr = this.m_limitConfig.networkNames) == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.toLowerCase().indexOf(str2.toLowerCase()) >= 0) {
                return true;
            }
        }
        return false;
    }

    public void AddDailyShowCount() {
        SPUtil.putInt(this.m_storeKey + this.THROTTLE_DAILY_AD_COUNT + DLEWSDK.getUserInfo().huoyueDays, getThrottleDailyShowCount() + 1);
    }

    public void AddDailyClickCount() {
        SPUtil.putInt(this.m_storeKey + this.THROTTLE_DAILY_AD_CLICK_COUNT + DLEWSDK.getUserInfo().huoyueDays, getThrottleDailyClickCount() + 1);
    }

    public void AddDailyMultiClickCount(String str, int i) {
        if (!CheckNetworkNameControlled(str) || i <= 0) {
            return;
        }
        List<Integer> dailyMultiClickCount = getDailyMultiClickCount();
        dailyMultiClickCount.add(Integer.valueOf(i));
        SPUtil.putObject(this.m_storeKey + this.DAILY_AD_MULTI_CLICK_COUNT + DLEWSDK.getUserInfo().huoyueDays, dailyMultiClickCount);
        XLog.d(StringFog.decrypt("dzYw1An4WDVwMSnUSjO3+uT0+EXJas7HiL/bGZVSkLK43g==\n", "AlhZoHDVK1Y=\n") + i + StringFog.decrypt("HovC\n", "+CdjhWU9Xa4=\n"));
    }
}