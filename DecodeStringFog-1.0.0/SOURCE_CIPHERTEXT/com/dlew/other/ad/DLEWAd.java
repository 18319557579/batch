package com.dlew.other.ad;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.appsflyer.AppsFlyerLib;
import com.dlew.StringFog;
import com.dlew.common.SPUtil;
import com.dlew.events.DLEWglEvent;
import com.dlew.hardware.PhoneInfoWrapper;
import com.dlew.lib.DLEWConfig;
import com.dlew.lib.DLEWSDK;
import com.dlew.log.GGAnalysis;
import com.dlew.net.GameNet;
import com.dlew.net.bean.DLEWSettingInfo;
import com.dlew.net.util.DLEWNetCallback;
import com.dlew.net.util.ThreadExecutorProxy;
import com.dlew.other.ad.admob.DLEWAdmobInterstitialAdapter;
import com.dlew.other.ad.admob.DLEWAdmobOpenAdAdapter;
import com.dlew.other.ad.admob.DLEWAdmobRewardVideoAdapter;
import com.dlew.other.ad.bigo.DLEWBigoIntersitialAdapter;
import com.dlew.other.ad.bigo.DLEWBigoOpenAdapter;
import com.dlew.other.ad.bigo.DLEWBigoRewardVideoAdapter;
import com.dlew.other.ad.core.DLEWAppOpenListener;
import com.dlew.other.ad.core.DLEWInterstitialListener;
import com.dlew.other.ad.core.DLEWNativeAd;
import com.dlew.other.ad.core.DLEWNativeAdListener;
import com.dlew.other.ad.core.DLEWRewardVideoListener;
import com.dlew.other.ad.core.DLEWiInterstitialAd;
import com.dlew.other.ad.core.DLEWiOpenAd;
import com.dlew.other.ad.core.DLEWiRewardVideoAd;
import com.dlew.other.ad.kwai.DLEWKwaiIntersitialAdapter;
import com.dlew.other.ad.kwai.DLEWKwaiRewardVideoAdapter;
import com.dlew.other.ad.max.DLEWMaxInterstitialAdapter;
import com.dlew.other.ad.max.DLEWMaxNativeAdapter;
import com.dlew.other.ad.max.DLEWMaxOpenAdapter;
import com.dlew.other.ad.max.DLEWMaxRewardVideoAdapter;
import com.dlew.other.ad.nm.DLEWNMIntersitialAdapter;
import com.dlew.other.ad.nm.DLEWNMRewardVideoAdapter;
import com.dlew.other.ad.typeBid.DLEWClientBidInfo;
import com.dlew.other.ad.typeBid.DLEWClientBidUnitInfo;
import com.dlew.other.ad.typeBid.DLEWTypeBiddingInfo;
import com.elvishew.xlog.XLog;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kwai.network.sdk.KwaiAdSDK;
import com.kwai.network.sdk.api.KwaiInitCallback;
import com.kwai.network.sdk.api.SdkConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.greenrobot.eventbus.EventBus;
import sg.bigo.ads.BigoAdSdk;
import sg.bigo.ads.api.AdConfig;
import z.k.api.NetAdSDK;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd.class */
public class DLEWAd {
    private static DLEWLimitConfig[] m_limitConfigs;
    private static DLEWTypeBiddingInfo m_typeBiddingInfo;
    private static boolean initialized;
    static final String KEY_AD_DISPLAY_COUNT = StringFog.decrypt("iD4gLui/N0qKKCk96KI3TYwuNyU=\n", "w3t5can7aA4=\n");
    static final String KEY_REWARD_AD_DISPLAY_COUNT = StringFog.decrypt("voPY1HmsdEqngt7Kb7ZnQqaWzcpytmBEoIjV\n", "9caBiyvpIws=\n");
    static final String KEY_AD_DAILY_DISPLAY_COUNT = StringFog.decrypt("3eEXFObW3BvX7QIS+NbKDMboDxL40cwK2PA=\n", "lqROS6eSg18=\n");
    private static List<DLEWiInterstitialAd> m_interstitialAds = new ArrayList();
    private static List<DLEWiRewardVideoAd> m_rewarVideoAds = new ArrayList();
    private static List<DLEWiOpenAd> m_openAds = new ArrayList();
    private static List<DLEWAdThrottle> m_nativeConfigs = new ArrayList();
    static String biddingCode = StringFog.decrypt("rJuU56aFAQ==\n", "yP7yhtPpdbY=\n");
    static boolean closed = false;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd$EeleE.class */
    public class EeleE implements KwaiInitCallback {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWLimitConfig f33EvLveE1;
        public final /* synthetic */ Activity v1vLvLE;

        public EeleE(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
            this.f33EvLveE1 = dLEWLimitConfig;
            this.v1vLvLE = activity;
        }

        public void onSuccess() {
            this.f33EvLveE1.isDefault = true;
            DLEWKwaiIntersitialAdapter dLEWKwaiIntersitialAdapter = new DLEWKwaiIntersitialAdapter();
            dLEWKwaiIntersitialAdapter.init(this.f33EvLveE1, this.v1vLvLE);
            DLEWAd.m_interstitialAds.add(dLEWKwaiIntersitialAdapter);
            DLEWKwaiRewardVideoAdapter dLEWKwaiRewardVideoAdapter = new DLEWKwaiRewardVideoAdapter();
            dLEWKwaiRewardVideoAdapter.init(this.f33EvLveE1, this.v1vLvLE);
            DLEWAd.m_rewarVideoAds.add(dLEWKwaiRewardVideoAdapter);
        }

        public void onFail(int i, String str) {
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd$EvLeE11LLE.class */
    public class EvLeE11LLE implements DLEWRewardVideoListener {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ String f34EvLveE1;
        public final /* synthetic */ DLEWRewardVideoListener v1vLvLE;

        /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd$EvLeE11LLE$EvLveE1.class */
        public class EvLveE1 implements DLEWInterstitialListener {
            public EvLveE1() {
            }

            @Override // com.dlew.other.ad.core.DLEWInterstitialListener
            public void onFialed() {
                DLEWRewardVideoListener dLEWRewardVideoListener = EvLeE11LLE.this.v1vLvLE;
                if (dLEWRewardVideoListener != null) {
                    dLEWRewardVideoListener.onFialed();
                }
            }

            @Override // com.dlew.other.ad.core.DLEWInterstitialListener
            public void onHide() {
                DLEWRewardVideoListener dLEWRewardVideoListener = EvLeE11LLE.this.v1vLvLE;
                if (dLEWRewardVideoListener != null) {
                    dLEWRewardVideoListener.onReward();
                }
            }
        }

        public EvLeE11LLE(String str, DLEWRewardVideoListener dLEWRewardVideoListener) {
            this.f34EvLveE1 = str;
            this.v1vLvLE = dLEWRewardVideoListener;
        }

        @Override // com.dlew.other.ad.core.DLEWRewardVideoListener
        public void onFialed() {
            if (DLEWAd.closed) {
                return;
            }
            DLEWAd.showInterstitial(this.f34EvLveE1 + StringFog.decrypt("WjyozQ==\n", "BVXGvkjsWW8=\n"), new EvLveE1());
        }

        @Override // com.dlew.other.ad.core.DLEWRewardVideoListener
        public void onReward() {
            DLEWAd.closed = true;
            DLEWRewardVideoListener dLEWRewardVideoListener = this.v1vLvLE;
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onReward();
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd$EvLveE1.class */
    public class EvLveE1 implements DLEWAppOpenListener {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWInterstitialListener f36EvLveE1;

        public EvLveE1(DLEWInterstitialListener dLEWInterstitialListener) {
            this.f36EvLveE1 = dLEWInterstitialListener;
        }

        @Override // com.dlew.other.ad.core.DLEWInterstitialListener
        public void onFialed() {
            DLEWInterstitialListener dLEWInterstitialListener = this.f36EvLveE1;
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onFialed();
            }
        }

        @Override // com.dlew.other.ad.core.DLEWInterstitialListener
        public void onHide() {
            DLEWAd.m_typeBiddingInfo.addOpenReplaceCount();
            DLEWInterstitialListener dLEWInterstitialListener = this.f36EvLveE1;
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onHide();
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd$EventType.class */
    public enum EventType {
        ON_INITIALIZED,
        ON_SHOW,
        ON_HIDE
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd$LvL11LLEl.class */
    public class LvL11LLEl implements BigoAdSdk.InitListener {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWLimitConfig f37EvLveE1;
        public final /* synthetic */ Activity v1vLvLE;

        public LvL11LLEl(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
            this.f37EvLveE1 = dLEWLimitConfig;
            this.v1vLvLE = activity;
        }

        public void onInitialized() {
            this.f37EvLveE1.isDefault = true;
            DLEWBigoIntersitialAdapter dLEWBigoIntersitialAdapter = new DLEWBigoIntersitialAdapter();
            dLEWBigoIntersitialAdapter.init(this.f37EvLveE1, this.v1vLvLE);
            DLEWAd.m_interstitialAds.add(dLEWBigoIntersitialAdapter);
            DLEWBigoRewardVideoAdapter dLEWBigoRewardVideoAdapter = new DLEWBigoRewardVideoAdapter();
            dLEWBigoRewardVideoAdapter.init(this.f37EvLveE1, this.v1vLvLE);
            DLEWAd.m_rewarVideoAds.add(dLEWBigoRewardVideoAdapter);
            if (TextUtils.isEmpty(this.f37EvLveE1.openID)) {
                return;
            }
            DLEWBigoOpenAdapter dLEWBigoOpenAdapter = new DLEWBigoOpenAdapter();
            dLEWBigoOpenAdapter.init(this.f37EvLveE1, this.v1vLvLE);
            DLEWAd.m_openAds.add(dLEWBigoOpenAdapter);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd$evleeEelE.class */
    public class evleeEelE implements DLEWNetCallback<List<DLEWLimitConfig>> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ Activity f38EvLveE1;

        public evleeEelE(Activity activity) {
            this.f38EvLveE1 = activity;
        }

        @Override // com.dlew.net.util.DLEWNetCallback
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, List<DLEWLimitConfig> list) {
            if (!z || list == null) {
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).networkNameStr.toLowerCase().contains(StringFog.decrypt("BByHDA==\n", "ZnXgYyvhztg=\n"))) {
                    DLEWAd.initBigoUnit(list.get(i), this.f38EvLveE1);
                } else if (list.get(i).networkNameStr.toLowerCase().contains(StringFog.decrypt("bk4S4Q==\n", "BTlziEV+/DE=\n"))) {
                    DLEWAd.initKwaiUnit(list.get(i), this.f38EvLveE1);
                } else if (list.get(i).networkNameStr.toLowerCase().contains(StringFog.decrypt("35h4RoM=\n", "vvwVKeG5Gjs=\n"))) {
                    DLEWAd.initAdmobUnit(list.get(i), this.f38EvLveE1);
                } else if (list.get(i).networkNameStr.toLowerCase().contains(StringFog.decrypt("OeFht73qZ587\n", "V4QV2tyYEfo=\n"))) {
                    DLEWAd.initNetMarvelUnit(list.get(i), this.f38EvLveE1);
                }
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd$lv1L11Eee1.class */
    public class lv1L11Eee1 implements DLEWAppOpenListener {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWRewardVideoListener f39EvLveE1;

        public lv1L11Eee1(DLEWRewardVideoListener dLEWRewardVideoListener) {
            this.f39EvLveE1 = dLEWRewardVideoListener;
        }

        @Override // com.dlew.other.ad.core.DLEWInterstitialListener
        public void onFialed() {
            DLEWRewardVideoListener dLEWRewardVideoListener = this.f39EvLveE1;
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
            }
        }

        @Override // com.dlew.other.ad.core.DLEWInterstitialListener
        public void onHide() {
            DLEWAd.m_typeBiddingInfo.addOpenReplaceCount();
            DLEWRewardVideoListener dLEWRewardVideoListener = this.f39EvLveE1;
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onReward();
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd$v1vLvLE.class */
    public class v1vLvLE implements AppLovinSdk.SdkInitializationListener {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWLimitConfig f40EvLveE1;
        public final /* synthetic */ Activity v1vLvLE;
        public final /* synthetic */ DLEWNetCallback evleeEelE;

        public v1vLvLE(DLEWLimitConfig dLEWLimitConfig, Activity activity, DLEWNetCallback dLEWNetCallback) {
            this.f40EvLveE1 = dLEWLimitConfig;
            this.v1vLvLE = activity;
            this.evleeEelE = dLEWNetCallback;
        }

        public void onSdkInitialized(AppLovinSdkConfiguration appLovinSdkConfiguration) {
            DLEWMaxInterstitialAdapter dLEWMaxInterstitialAdapter = new DLEWMaxInterstitialAdapter();
            dLEWMaxInterstitialAdapter.init(this.f40EvLveE1, this.v1vLvLE);
            DLEWAd.m_interstitialAds.add(dLEWMaxInterstitialAdapter);
            DLEWMaxRewardVideoAdapter dLEWMaxRewardVideoAdapter = new DLEWMaxRewardVideoAdapter();
            dLEWMaxRewardVideoAdapter.init(this.f40EvLveE1, this.v1vLvLE);
            DLEWAd.m_rewarVideoAds.add(dLEWMaxRewardVideoAdapter);
            if (!TextUtils.isEmpty(DLEWConfig.getOpenUnitId())) {
                DLEWMaxOpenAdapter dLEWMaxOpenAdapter = new DLEWMaxOpenAdapter();
                dLEWMaxOpenAdapter.init(this.f40EvLveE1, this.v1vLvLE);
                DLEWAd.m_openAds.add(dLEWMaxOpenAdapter);
            }
            if (!TextUtils.isEmpty(DLEWConfig.getNativeUnitId())) {
                DLEWAd.m_nativeConfigs.add(new DLEWAdThrottle(this.f40EvLveE1));
            }
            boolean unused = DLEWAd.initialized = true;
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_INITIALIZED));
            DLEWNetCallback dLEWNetCallback = this.evleeEelE;
            if (dLEWNetCallback != null) {
                dLEWNetCallback.OnCompleted(true, null);
            }
            if (DLEWConfig.isDebug()) {
                AppLovinSdk.getInstance(this.v1vLvLE).showMediationDebugger();
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd$vl1L.class */
    public class vl1L implements Runnable {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ String f41EvLveE1;
        public final /* synthetic */ Activity v1vLvLE;

        public vl1L(String str, Activity activity) {
            this.f41EvLveE1 = str;
            this.v1vLvLE = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            Gson gson = new Gson();
            DLEWLimitConfig[] unused = DLEWAd.m_limitConfigs = (DLEWLimitConfig[]) gson.fromJson(((JsonObject) gson.fromJson(this.f41EvLveE1, JsonObject.class)).get(StringFog.decrypt("8BdksbTWMg==\n", "k3gK192xQag=\n")).toString(), DLEWLimitConfig[].class);
            for (int i = 0; i < DLEWAd.m_limitConfigs.length; i++) {
                DLEWAd.m_limitConfigs[i].init();
                if (!TextUtils.isEmpty(DLEWAd.m_limitConfigs[i].insID)) {
                    DLEWMaxInterstitialAdapter dLEWMaxInterstitialAdapter = new DLEWMaxInterstitialAdapter();
                    dLEWMaxInterstitialAdapter.init(DLEWAd.m_limitConfigs[i], this.v1vLvLE);
                    DLEWAd.m_interstitialAds.add(dLEWMaxInterstitialAdapter);
                }
                if (!TextUtils.isEmpty(DLEWAd.m_limitConfigs[i].videoID)) {
                    DLEWMaxRewardVideoAdapter dLEWMaxRewardVideoAdapter = new DLEWMaxRewardVideoAdapter();
                    dLEWMaxRewardVideoAdapter.init(DLEWAd.m_limitConfigs[i], this.v1vLvLE);
                    DLEWAd.m_rewarVideoAds.add(dLEWMaxRewardVideoAdapter);
                }
                if (!TextUtils.isEmpty(DLEWAd.m_limitConfigs[i].nativeID)) {
                    DLEWAd.m_nativeConfigs.add(new DLEWAdThrottle(DLEWAd.m_limitConfigs[i]));
                }
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd$vv11e.class */
    public class vv11e implements DLEWInterstitialListener {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWRewardVideoListener f42EvLveE1;

        public vv11e(DLEWRewardVideoListener dLEWRewardVideoListener) {
            this.f42EvLveE1 = dLEWRewardVideoListener;
        }

        @Override // com.dlew.other.ad.core.DLEWInterstitialListener
        public void onFialed() {
            DLEWRewardVideoListener dLEWRewardVideoListener = this.f42EvLveE1;
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
            }
        }

        @Override // com.dlew.other.ad.core.DLEWInterstitialListener
        public void onHide() {
            DLEWAd.m_typeBiddingInfo.addInsReplaceCount();
            DLEWRewardVideoListener dLEWRewardVideoListener = this.f42EvLveE1;
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onReward();
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAd$vvlEEv1lv.class */
    public class vvlEEv1lv implements DLEWNetCallback<DLEWSettingInfo[]> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ Activity f43EvLveE1;

        public vvlEEv1lv(Activity activity) {
            this.f43EvLveE1 = activity;
        }

        @Override // com.dlew.net.util.DLEWNetCallback
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWSettingInfo[] dLEWSettingInfoArr) {
            if (!z || dLEWSettingInfoArr == null || dLEWSettingInfoArr.length == 0) {
                return;
            }
            for (DLEWSettingInfo dLEWSettingInfo : dLEWSettingInfoArr) {
                if (dLEWSettingInfo.code.equals(StringFog.decrypt("mL1ClzkhI9C1nA==\n", "2fkW/0tOV6Q=\n"))) {
                    DLEWAd.InitThrottle(dLEWSettingInfo.setting.toString(), this.f43EvLveE1);
                }
            }
        }
    }

    public static int getAdDisplayCount() {
        return SPUtil.getInt(KEY_AD_DISPLAY_COUNT, 0);
    }

    public static int getRewardAdDisplayCount() {
        return SPUtil.getInt(KEY_REWARD_AD_DISPLAY_COUNT, 0);
    }

    public static void addAdDdisplayCount(boolean z) {
        int adDisplayCount = getAdDisplayCount() + 1;
        SPUtil.putInt(KEY_AD_DISPLAY_COUNT, adDisplayCount);
        HashMap hashMap = new HashMap();
        hashMap.put(StringFog.decrypt("8KZKqXFxpLrEtF2lQG6eovilQq9ARbW667R08Q==\n", "m9ErwC4awcM=\n"), StringFog.decrypt("Lw==\n", "HphZGGDu+f0=\n"));
        hashMap.put(StringFog.decrypt("2HvnBNx3wCzsafAI7Wj6NNB47wLtQ9M033njMrI=\n", "swyGbYMcpVU=\n"), Integer.valueOf(adDisplayCount));
        AppsFlyerLib.getInstance().logEvent(DLEWSDK.getApplication(), StringFog.decrypt("fvPotVoj/pQ=\n", "H5e3xjJMics=\n") + adDisplayCount, hashMap);
        if (z) {
            int rewardAdDisplayCount = getRewardAdDisplayCount() + 1;
            SPUtil.putInt(KEY_REWARD_AD_DISPLAY_COUNT, rewardAdDisplayCount);
            HashMap hashMap2 = new HashMap();
            hashMap2.put(StringFog.decrypt("tYvUd73rMd2BmcN7jPQLxb2I3HGM3yDdrpnqLw==\n", "3vy1HuKAVKQ=\n"), StringFog.decrypt("FQ==\n", "JkjTSBx68Os=\n"));
            hashMap2.put(StringFog.decrypt("TZESJBNMXD15gwUoIlNmJUWSGiIieE8lSpMWEn0=\n", "JuZzTUwnOUQ=\n"), Integer.valueOf(rewardAdDisplayCount));
            AppsFlyerLib.getInstance().logEvent(DLEWSDK.getApplication(), StringFog.decrypt("snGV4mqyIe6hcL3wcLkz1Yw=\n", "0xXKkQLdVrE=\n") + rewardAdDisplayCount, hashMap2);
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static int getAdDisplayDailyCount() {
        return SPUtil.getInt(KEY_AD_DAILY_DISPLAY_COUNT + DLEWSDK.getUserInfo().huoyueDays, 0);
    }

    public static void addAdDdisplayDailyCount() {
        SPUtil.putInt(KEY_AD_DAILY_DISPLAY_COUNT + DLEWSDK.getUserInfo().huoyueDays, getAdDisplayDailyCount() + 1);
    }

    public static void init(Activity activity, DLEWNetCallback<Object> dLEWNetCallback) {
        if (TextUtils.isEmpty(DLEWConfig.getVideoUnitId()) && TextUtils.isEmpty(DLEWConfig.getInsUnitId())) {
            return;
        }
        if (m_interstitialAds.size() != 0) {
            m_interstitialAds.clear();
        }
        if (m_rewarVideoAds.size() != 0) {
            m_rewarVideoAds.clear();
        }
        initMediation(activity);
        DLEWLimitConfig dLEWLimitConfig = new DLEWLimitConfig();
        dLEWLimitConfig.videoID = DLEWConfig.getVideoUnitId();
        dLEWLimitConfig.insID = DLEWConfig.getInsUnitId();
        dLEWLimitConfig.banID = DLEWConfig.getBannerUnitId();
        dLEWLimitConfig.nativeID = DLEWConfig.getNativeUnitId();
        dLEWLimitConfig.openID = DLEWConfig.getOpenUnitId();
        dLEWLimitConfig.showLoading = 0;
        dLEWLimitConfig.illegalClickRate = StringFog.decrypt("aj6yjVTNgKBqPg==\n", "UweLtHf0uZk=\n");
        dLEWLimitConfig.illegalMultiClick = StringFog.decrypt("Ju4gguVFi1km7g==\n", "H9cZu8Z8smA=\n");
        dLEWLimitConfig.isDefault = true;
        dLEWLimitConfig.maxDisplay = -1;
        AppLovinSdk.getInstance(activity).setMediationProvider(StringFog.decrypt("sJmn\n", "3fjfVLrW2zA=\n"));
        if (DLEWConfig.isDebug()) {
            AppLovinSdk.getInstance(activity).getSettings().setVerboseLogging(true);
        }
        AppLovinSdk.initializeSdk(activity, new v1vLvLE(dLEWLimitConfig, activity, dLEWNetCallback));
    }

    private static void initMediation(Activity activity) {
        DLEWMediation.fetchMediationConfigs(new evleeEelE(activity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initAdmobUnit(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        MobileAds.initialize(activity);
        dLEWLimitConfig.isDefault = true;
        if (!TextUtils.isEmpty(dLEWLimitConfig.insID)) {
            DLEWAdmobInterstitialAdapter dLEWAdmobInterstitialAdapter = new DLEWAdmobInterstitialAdapter();
            dLEWAdmobInterstitialAdapter.init(dLEWLimitConfig, activity);
            m_interstitialAds.add(dLEWAdmobInterstitialAdapter);
        }
        if (!TextUtils.isEmpty(dLEWLimitConfig.openID)) {
            DLEWAdmobOpenAdAdapter dLEWAdmobOpenAdAdapter = new DLEWAdmobOpenAdAdapter();
            dLEWAdmobOpenAdAdapter.init(dLEWLimitConfig, activity);
            m_openAds.add(dLEWAdmobOpenAdAdapter);
        }
        if (TextUtils.isEmpty(dLEWLimitConfig.videoID)) {
            return;
        }
        DLEWAdmobRewardVideoAdapter dLEWAdmobRewardVideoAdapter = new DLEWAdmobRewardVideoAdapter();
        dLEWAdmobRewardVideoAdapter.init(dLEWLimitConfig, activity);
        m_rewarVideoAds.add(dLEWAdmobRewardVideoAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initKwaiUnit(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        KwaiAdSDK.init(activity, new SdkConfig.Builder().appId(dLEWLimitConfig.appkey).token(dLEWLimitConfig.token).debug(DLEWConfig.isDebug()).setInitCallback(new EeleE(dLEWLimitConfig, activity)).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initNetMarvelUnit(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        if (!TextUtils.isEmpty(dLEWLimitConfig.appkey)) {
            NetAdSDK.getInstance().initialize(PhoneInfoWrapper.getContext(), dLEWLimitConfig.appkey);
        }
        dLEWLimitConfig.isDefault = true;
        DLEWNMIntersitialAdapter dLEWNMIntersitialAdapter = new DLEWNMIntersitialAdapter();
        dLEWNMIntersitialAdapter.init(dLEWLimitConfig, activity);
        m_interstitialAds.add(dLEWNMIntersitialAdapter);
        DLEWNMRewardVideoAdapter dLEWNMRewardVideoAdapter = new DLEWNMRewardVideoAdapter();
        dLEWNMRewardVideoAdapter.init(dLEWLimitConfig, activity);
        m_rewarVideoAds.add(dLEWNMRewardVideoAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initBigoUnit(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        AdConfig.Builder debug = new AdConfig.Builder().setAppId(dLEWLimitConfig.appkey).setDebug(DLEWConfig.isDebug());
        if (!TextUtils.isEmpty(dLEWLimitConfig.extra)) {
            debug = debug.addExtra(StringFog.decrypt("mz9pS5CoW+SWIw==\n", "81AaP8/aLog=\n"), dLEWLimitConfig.extra);
        }
        BigoAdSdk.initialize(activity, debug.build(), new LvL11LLEl(dLEWLimitConfig, activity));
    }

    public static void showVideo(String str, DLEWRewardVideoListener dLEWRewardVideoListener) {
        biddingCode = UUID.randomUUID().toString();
        if (!isInitialized()) {
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
                return;
            }
            return;
        }
        DLEWiRewardVideoAd GetRewardVideoAd = GetRewardVideoAd(str);
        if (GetRewardVideoAd == null) {
            XLog.e(StringFog.decrypt("Mmn9KIwj2QF2Nd9a9RanT0t6p0WATJgiMlzU\n", "19BCzR2pP6k=\n"));
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
                return;
            }
            return;
        }
        if (tryTypeBiddingForVideo(str, GetRewardVideoAd, dLEWRewardVideoListener)) {
            return;
        }
        closed = false;
        GetRewardVideoAd.show(str, new EvLeE11LLE(str, dLEWRewardVideoListener));
    }

    public static void showInterstitial(String str) {
        showInterstitial(str, null);
    }

    public static void showAppOpen(String str, DLEWAppOpenListener dLEWAppOpenListener) {
        biddingCode = UUID.randomUUID().toString();
        if (!isInitialized()) {
            if (dLEWAppOpenListener != null) {
                dLEWAppOpenListener.onFialed();
                return;
            }
            return;
        }
        DLEWiOpenAd openAd = getOpenAd(str);
        if (openAd != null) {
            openAd.show(str, dLEWAppOpenListener);
            return;
        }
        XLog.e(StringFog.decrypt("r2Wfhdr7FWbrOb33o85rKNZ2xejWlFRFr1C2\n", "StwgYEtx884=\n"));
        if (dLEWAppOpenListener != null) {
            dLEWAppOpenListener.onFialed();
        }
    }

    public static DLEWNativeAd showNative(Context context, String str, FrameLayout frameLayout, DLEWNativeAdListener dLEWNativeAdListener) {
        biddingCode = UUID.randomUUID().toString();
        DLEWAdThrottle nativeAd = getNativeAd(str);
        if (nativeAd != null) {
            DLEWMaxNativeAdapter dLEWMaxNativeAdapter = new DLEWMaxNativeAdapter();
            dLEWMaxNativeAdapter.show(nativeAd, context, nativeAd.getConfig().nativeID, str, frameLayout, dLEWNativeAdListener);
            return dLEWMaxNativeAdapter;
        }
        XLog.d(StringFog.decrypt("Nfvj9JsjdFEy/Pr02C7ig9VyLjoHgJjV1ApvJFPms5evKQZnXrTigtHg5OmWR0M=\n", "QJWKgOIOBzI=\n"));
        if (dLEWNativeAdListener == null) {
            return null;
        }
        dLEWNativeAdListener.onFialed();
        return null;
    }

    public static void startThrottle(String str, Activity activity) {
        if (!TextUtils.isEmpty(DLEWSDK.getTypeBiddingConfig())) {
            m_typeBiddingInfo = (DLEWTypeBiddingInfo) new Gson().fromJson(DLEWSDK.getTypeBiddingConfig(), DLEWTypeBiddingInfo.class);
        }
        if (TextUtils.isEmpty(str)) {
            GameNet.getInstance().FetchConfig(StringFog.decrypt("3hU1v2BWZtPzNA==\n", "n1Fh1xI5Eqc=\n"), new vvlEEv1lv(activity));
        } else {
            InitThrottle(str, activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void InitThrottle(String str, Activity activity) {
        ThreadExecutorProxy.getInstance().runOnMainThread(new vl1L(str, activity));
    }

    private static DLEWiInterstitialAd getInterstitialAd(String str) {
        if (m_interstitialAds.size() == 0) {
            return null;
        }
        if (m_interstitialAds.size() == 1) {
            return m_interstitialAds.get(0);
        }
        DLEWClientBidInfo dLEWClientBidInfo = new DLEWClientBidInfo();
        dLEWClientBidInfo.bidding = new ArrayList();
        dLEWClientBidInfo.ad_id = str;
        dLEWClientBidInfo.ad_type = StringFog.decrypt("WEgB\n", "MSZy1UFNQAI=\n");
        dLEWClientBidInfo.code = biddingCode;
        int i = 0;
        double checkRevenue = m_interstitialAds.get(0).checkRevenue(str);
        double d = checkRevenue;
        if (checkRevenue > 0.0d) {
            dLEWClientBidInfo.bidding.add(new DLEWClientBidUnitInfo(m_interstitialAds.get(0).getNetworkName(), d, StringFog.decrypt("ecfB\n", "EKmy4mZRnuc=\n")));
        }
        XLog.d(StringFog.decrypt("5UH8J5HwJEHiRuUn0v2+mQjHO/cOU/DHGJlw3n040qF0lCK2aGG4ngoP\n", "kC+VU+jdVyI=\n") + d);
        if (StringFog.decrypt("lky8oLf2zKCUTbg=\n", "/TvdyeiYrdQ=\n").equals(m_interstitialAds.get(0).getNetworkName()) && d > 0.0d) {
        }
        for (int i2 = 1; i2 < m_interstitialAds.size(); i2++) {
            DLEWiInterstitialAd dLEWiInterstitialAd = m_interstitialAds.get(i2);
            double checkRevenue2 = dLEWiInterstitialAd.checkRevenue(str);
            if (StringFog.decrypt("RfT3OoVdoqlH9fM=\n", "LoOWU9ozw90=\n").equals(m_interstitialAds.get(i2).getNetworkName()) && checkRevenue2 > 0.0d) {
            }
            if (checkRevenue2 > 0.0d) {
                dLEWClientBidInfo.bidding.add(new DLEWClientBidUnitInfo(dLEWiInterstitialAd.getNetworkName(), checkRevenue2, StringFog.decrypt("BWXV\n", "bAumlK7y5gU=\n")));
            }
            if (checkRevenue2 > d) {
                d = checkRevenue2;
                i = i2;
            }
        }
        XLog.d(StringFog.decrypt("E2huhlkrHmQUb3eGGiaLm+biukHGiMri7rDif7Xj6ISCvbAXoLqCu/wm\n", "ZgYH8iAGbQc=\n") + d);
        XLog.d(StringFog.decrypt("z4PsOqcsB7PIhPU65CGcXg0IO9k4juY1C2JjwHnk/GZfYBCrW4JU\n", "uu2FTt4BdNA=\n") + m_interstitialAds.get(i).getAdInfo().unitID);
        if (dLEWClientBidInfo.bidding.size() != 0) {
            GGAnalysis.track(StringFog.decrypt("lJOLqeEFsHCem4al4RY=\n", "9//izI9x7xI=\n"), dLEWClientBidInfo);
        }
        return m_interstitialAds.get(i);
    }

    private static DLEWAdThrottle getNativeAd(String str) {
        if (m_nativeConfigs.size() == 0) {
            return null;
        }
        if (m_nativeConfigs.size() == 1) {
            return m_nativeConfigs.get(0);
        }
        DLEWClientBidInfo dLEWClientBidInfo = new DLEWClientBidInfo();
        dLEWClientBidInfo.bidding = new ArrayList();
        dLEWClientBidInfo.ad_id = str;
        dLEWClientBidInfo.ad_type = StringFog.decrypt("/JtkLs8P\n", "kvoQR7lqhiI=\n");
        dLEWClientBidInfo.code = biddingCode;
        int i = 0;
        DLEWAdThrottle dLEWAdThrottle = m_nativeConfigs.get(0);
        double d = dLEWAdThrottle.CheckThrottle(str) ? -1.0d : dLEWAdThrottle.getConfig().priority;
        if (d > 0.0d) {
            dLEWClientBidInfo.bidding.add(new DLEWClientBidUnitInfo(dLEWAdThrottle.getConfig().nativeID, d, StringFog.decrypt("Vj+TN7Xw\n", "OF7nXsOVOVM=\n")));
        }
        XLog.d(StringFog.decrypt("EP0+fUsKDjMX+id9CAeU6/17+a3Uqdq17SWztarC+NiCKfDmjr1d\n", "ZZNXCTInfVA=\n") + String.format(StringFog.decrypt("e5x6QJ0=\n", "XrJLcPviL/0=\n"), Double.valueOf(d)));
        for (int i2 = 1; i2 < m_nativeConfigs.size(); i2++) {
            DLEWAdThrottle dLEWAdThrottle2 = m_nativeConfigs.get(i2);
            double d2 = dLEWAdThrottle2.CheckThrottle(str) ? -1.0d : dLEWAdThrottle2.getConfig().priority;
            if (d2 > 0.0d) {
                dLEWClientBidInfo.bidding.add(new DLEWClientBidUnitInfo(dLEWAdThrottle2.getConfig().nativeID, d2, StringFog.decrypt("Vf8POVB4\n", "O557UCYdBPw=\n")));
            }
            if (d2 > d) {
                d = d2;
                i = i2;
            }
        }
        XLog.d(StringFog.decrypt("vFHGolhg9eC7Vt+iG21gH0nbEmXHwyFmQYlLarmoAwsuhQg5ndem\n", "yT+v1iFNhoM=\n") + String.format(StringFog.decrypt("ue7Fxu8=\n", "nMD09omkinM=\n"), Double.valueOf(d)));
        XLog.d(StringFog.decrypt("2DFyqvuUVebfNmuquJnOCxq6pUlnN7liOcD9UCVcrjNI0o47BzoG\n", "rV8b3oK5JoU=\n") + m_nativeConfigs.get(i).getConfig().nativeID);
        if (dLEWClientBidInfo.bidding.size() != 0) {
            GGAnalysis.track(StringFog.decrypt("67GwCr3ArWnhub0GvdM=\n", "iN3Zb9O08gs=\n"), dLEWClientBidInfo);
        }
        return m_nativeConfigs.get(i);
    }

    private static DLEWiRewardVideoAd GetRewardVideoAd(String str) {
        if (m_rewarVideoAds.size() == 0) {
            return null;
        }
        if (m_rewarVideoAds.size() == 1) {
            return m_rewarVideoAds.get(0);
        }
        DLEWClientBidInfo dLEWClientBidInfo = new DLEWClientBidInfo();
        dLEWClientBidInfo.bidding = new ArrayList();
        dLEWClientBidInfo.ad_id = str;
        dLEWClientBidInfo.ad_type = StringFog.decrypt("3Z1ODy61YLc=\n", "r/g5blzRBdM=\n");
        dLEWClientBidInfo.code = biddingCode;
        int i = 0;
        double checkRevenue = m_rewarVideoAds.get(0).checkRevenue(str);
        double d = checkRevenue;
        if (checkRevenue > 0.0d) {
            dLEWClientBidInfo.bidding.add(new DLEWClientBidUnitInfo(m_rewarVideoAds.get(0).getNetworkName(), d, StringFog.decrypt("YCfgrLD19xw=\n", "EkKXzcKRkng=\n")));
        }
        XLog.d(StringFog.decrypt("g61sIDMA5ZyEqnUgcFv/m5OsJb3xtX5RUiWL86+lIBp7VuDRycktSBNDubv2t7Y=\n", "9sMFVEotlv8=\n") + d);
        DLEWKwaiRewardVideoAdapter dLEWKwaiRewardVideoAdapter = m_rewarVideoAds.get(0).getNetworkName() != null ? (!m_rewarVideoAds.get(0).getNetworkName().equals(StringFog.decrypt("6ui4NYJu+xro6bw=\n", "gZ/ZXN0Amm4=\n")) || d <= 0.0d) ? null : (DLEWKwaiRewardVideoAdapter) m_rewarVideoAds.get(0) : null;
        for (int i2 = 1; i2 < m_rewarVideoAds.size(); i2++) {
            DLEWKwaiRewardVideoAdapter dLEWKwaiRewardVideoAdapter2 = dLEWKwaiRewardVideoAdapter;
            DLEWiRewardVideoAd dLEWiRewardVideoAd = m_rewarVideoAds.get(i2);
            double checkRevenue2 = m_rewarVideoAds.get(i2).checkRevenue(str);
            if (dLEWKwaiRewardVideoAdapter2 != null && m_rewarVideoAds.get(i2).getNetworkName().equals(StringFog.decrypt("JjBwtfqz0gIkMXQ=\n", "TUcR3KXds3Y=\n")) && checkRevenue2 > 0.0d) {
                dLEWKwaiRewardVideoAdapter = (DLEWKwaiRewardVideoAdapter) m_rewarVideoAds.get(i2);
            }
            if (checkRevenue2 > 0.0d) {
                dLEWClientBidInfo.bidding.add(new DLEWClientBidUnitInfo(dLEWiRewardVideoAd.getNetworkName(), checkRevenue2, StringFog.decrypt("11g7m/Q0Y7w=\n", "pT1M+oZQBtg=\n")));
            }
            if (checkRevenue2 > d) {
                d = checkRevenue2;
                i = i2;
            }
        }
        DLEWKwaiRewardVideoAdapter dLEWKwaiRewardVideoAdapter3 = dLEWKwaiRewardVideoAdapter;
        XLog.d(StringFog.decrypt("OahNZCKkE5I+r1RkYf8JlSmpBPbHCYRM/yCqt74B1hTBU8GV2G3bRqlGmP/nE0A=\n", "TMYkEFuJYPE=\n") + d);
        if (dLEWKwaiRewardVideoAdapter3 != null) {
            KwaiVideoBiddingResultTrack(dLEWKwaiRewardVideoAdapter, Double.valueOf(d), str);
        }
        XLog.d(StringFog.decrypt("Aqbw5JfE71wFoenk1J/1WxKnuXhgXnmB4C4mEAtjLdn5b3wYWAwRqpJNGrA=\n", "d8iZkO7pnD8=\n") + m_rewarVideoAds.get(i).getAdInfo().unitID);
        if (dLEWClientBidInfo.bidding.size() != 0) {
            GGAnalysis.track(StringFog.decrypt("1Pt7fJE+Nnre83ZwkS0=\n", "t5cSGf9KaRg=\n"), dLEWClientBidInfo);
        }
        return m_rewarVideoAds.get(i);
    }

    private static DLEWiOpenAd getOpenAd(String str) {
        if (m_openAds.size() == 0) {
            return null;
        }
        if (m_openAds.size() == 1) {
            return m_openAds.get(0);
        }
        DLEWClientBidInfo dLEWClientBidInfo = new DLEWClientBidInfo();
        dLEWClientBidInfo.bidding = new ArrayList();
        dLEWClientBidInfo.ad_id = str;
        dLEWClientBidInfo.ad_type = StringFog.decrypt("QnPKALLOo/U=\n", "IwO6X92+xps=\n");
        dLEWClientBidInfo.code = biddingCode;
        int i = 0;
        double checkRevenue = m_openAds.get(0).checkRevenue(str);
        double d = checkRevenue;
        if (checkRevenue > 0.0d) {
            dLEWClientBidInfo.bidding.add(new DLEWClientBidUnitInfo(m_openAds.get(0).getNetworkName(), d, StringFog.decrypt("ysz7eV/J7c8=\n", "q7yLJjC5iKE=\n")));
        }
        XLog.d(StringFog.decrypt("TUXLkMwwg1lKQtKQj3KAX1ZKxsRcpmjSlo9EahL4eIzdpjcBMJ4UgY/OIlhaoWoa\n", "OCui5LUd8Do=\n") + d);
        for (int i2 = 1; i2 < m_openAds.size(); i2++) {
            DLEWiOpenAd dLEWiOpenAd = m_openAds.get(i2);
            double checkRevenue2 = m_openAds.get(i2).checkRevenue(str);
            if (checkRevenue2 > 0.0d) {
                dLEWClientBidInfo.bidding.add(new DLEWClientBidUnitInfo(dLEWiOpenAd.getNetworkName(), checkRevenue2, StringFog.decrypt("U0fXwa2PA18=\n", "MjennsL/ZjE=\n")));
            }
            if (checkRevenue2 > d) {
                d = checkRevenue2;
                i = i2;
            }
        }
        XLog.d(StringFog.decrypt("hhQ4vJl61tSBEyG82jjV0p0bNegGyyVTTsm3RkeyLQEW98QtZdRBDESf0XQP6z+X\n", "83pRyOBXpbc=\n") + d);
        XLog.d(StringFog.decrypt("es5MRMHHRoN9yVVEgoVFhWHBQRBQZIIFsTfAjDgPhG/pLoLVMFzQbZpFoLOY\n", "D6AlMLjqNeA=\n") + m_openAds.get(i).getAdInfo().unitID);
        if (dLEWClientBidInfo.bidding.size() != 0) {
            GGAnalysis.track(StringFog.decrypt("1VzXvJDTCT7fVNqwkMA=\n", "tjC+2f6nVlw=\n"), dLEWClientBidInfo);
        }
        return m_openAds.get(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0231 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean tryTypeBiddingForVideo(java.lang.String r9, com.dlew.other.ad.core.DLEWiRewardVideoAd r10, com.dlew.other.ad.core.DLEWRewardVideoListener r11) {
        /*
            Method dump skipped, instructions count: 563
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dlew.other.ad.DLEWAd.tryTypeBiddingForVideo(java.lang.String, com.dlew.other.ad.core.DLEWiRewardVideoAd, com.dlew.other.ad.core.DLEWRewardVideoListener):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0190 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean tryTypeBiddingForIns(java.lang.String r9, com.dlew.other.ad.core.DLEWiInterstitialAd r10, com.dlew.other.ad.core.DLEWInterstitialListener r11) {
        /*
            Method dump skipped, instructions count: 402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dlew.other.ad.DLEWAd.tryTypeBiddingForIns(java.lang.String, com.dlew.other.ad.core.DLEWiInterstitialAd, com.dlew.other.ad.core.DLEWInterstitialListener):boolean");
    }

    private static void KwaiVideoBiddingResultTrack(DLEWKwaiRewardVideoAdapter dLEWKwaiRewardVideoAdapter, Double d, String str) {
        if (dLEWKwaiRewardVideoAdapter != null) {
            if (dLEWKwaiRewardVideoAdapter.checkRevenue(str) >= d.doubleValue()) {
                dLEWKwaiRewardVideoAdapter.sendBinWin();
            } else {
                dLEWKwaiRewardVideoAdapter.sendBinLose();
            }
        }
    }

    private static void KwaiInsBiddingResultTrack(DLEWKwaiIntersitialAdapter dLEWKwaiIntersitialAdapter, Double d, String str) {
        if (dLEWKwaiIntersitialAdapter != null) {
            if (dLEWKwaiIntersitialAdapter.checkRevenue(str) >= d.doubleValue()) {
                dLEWKwaiIntersitialAdapter.sendBinWin();
            } else {
                dLEWKwaiIntersitialAdapter.sendBinLose();
            }
        }
    }

    public static void showInterstitial(String str, DLEWInterstitialListener dLEWInterstitialListener) {
        biddingCode = UUID.randomUUID().toString();
        if (!isInitialized()) {
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onFialed();
                return;
            }
            return;
        }
        DLEWiInterstitialAd interstitialAd = getInterstitialAd(str);
        if (interstitialAd != null) {
            if (tryTypeBiddingForIns(str, interstitialAd, dLEWInterstitialListener)) {
                return;
            }
            interstitialAd.show(str, dLEWInterstitialListener);
        } else {
            XLog.e(StringFog.decrypt("5y6Y736vYXejcrqdB5ofOZ49woJywCBU5xux\n", "ApcnCu8lh98=\n"));
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onFialed();
            }
        }
    }
}