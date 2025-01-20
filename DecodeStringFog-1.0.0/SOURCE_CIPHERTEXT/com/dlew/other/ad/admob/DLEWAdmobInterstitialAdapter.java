package com.dlew.other.ad.admob;

import android.app.Activity;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.dlew.StringFog;
import com.dlew.common.NetUtils;
import com.dlew.events.DLEWglEvent;
import com.dlew.lib.DLEWSDK;
import com.dlew.log.GGAnalysis;
import com.dlew.other.ad.DLEWAd;
import com.dlew.other.ad.DLEWAdEventTrack;
import com.dlew.other.ad.DLEWAdInfo;
import com.dlew.other.ad.DLEWAdInfoExtra;
import com.dlew.other.ad.DLEWAdThrottle;
import com.dlew.other.ad.DLEWLimitConfig;
import com.dlew.other.ad.DLEWRoas;
import com.dlew.other.ad.core.DLEWInterstitialListener;
import com.dlew.other.ad.core.DLEWiInterstitialAd;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.gson.Gson;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobInterstitialAdapter.class */
public class DLEWAdmobInterstitialAdapter implements DLEWiInterstitialAd {
    private InterstitialAd mInterstitialAd;
    private DLEWInterstitialListener listener;
    private boolean mLoading;
    private int retryAttempt;
    private DLEWAdThrottle mThrottle;
    private DLEWAdInfo mAdInfo;
    private String mCacheSceneType = StringFog.decrypt("hQvisQ==\n", "636O3V4L0YM=\n");
    private String mRequestCode = UUID.randomUUID().toString();
    private final String mAdType = StringFog.decrypt("Wz6W\n", "MlDlvF1GBBA=\n");
    private FullScreenContentCallback mFullScreenContentCallback = new EvLveE1();
    private OnPaidEventListener mOnPaidEventListener = new v1vLvLE();
    private InterstitialAdLoadCallback mInterstitialAdLoadCallback = new evleeEelE();
    private String m_lastRequestCode = BuildConfig.VERSION_NAME;
    private int mMultiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobInterstitialAdapter$EvLveE1.class */
    public class EvLveE1 extends FullScreenContentCallback {
        public EvLveE1() {
        }

        public void onAdClicked() {
            if (DLEWAdmobInterstitialAdapter.this.mThrottle.CheckNetworkNameControlled(DLEWAdmobInterstitialAdapter.this.getNetworkName())) {
                DLEWAdmobInterstitialAdapter.this.mThrottle.AddDailyClickCount();
                if (DLEWAdmobInterstitialAdapter.this.m_lastRequestCode.equals(DLEWAdmobInterstitialAdapter.this.mRequestCode)) {
                    DLEWAdmobInterstitialAdapter.access$308(DLEWAdmobInterstitialAdapter.this);
                } else {
                    DLEWAdmobInterstitialAdapter dLEWAdmobInterstitialAdapter = DLEWAdmobInterstitialAdapter.this;
                    dLEWAdmobInterstitialAdapter.m_lastRequestCode = dLEWAdmobInterstitialAdapter.mRequestCode;
                    DLEWAdmobInterstitialAdapter.this.mMultiClickCount = 1;
                }
            }
            XLog.d(StringFog.decrypt("e/vHSwaG1818/N5LRYvtjmn62h8+z8nBbLXnUQvO1t16/NpWHsflyk35x1wUzsDrePDASw==\n", "DpWuP3+rpK4=\n"));
            DLEWAdEventTrack.OnClicked(StringFog.decrypt("IgYN\n", "S2h+TDjtl8k=\n"), DLEWAdmobInterstitialAdapter.this.mRequestCode, DLEWAdmobInterstitialAdapter.this.mCacheSceneType, DLEWAdmobInterstitialAdapter.this.getNetworkName());
        }

        public void onAdDismissedFullScreenContent() {
            DLEWAdmobInterstitialAdapter.this.mInterstitialAd = null;
            XLog.d(StringFog.decrypt("aarQVMNjD79urclUgG41/HurzQD7KhGzfuTwTs4rDq9orc1J2yI9uF+o1lPfKjmqearN\n", "HMS5ILpOfNw=\n"));
            if (DLEWAdmobInterstitialAdapter.this.listener != null) {
                DLEWAdmobInterstitialAdapter.this.listener.onHide();
            }
            DLEWAdmobInterstitialAdapter.this.mThrottle.AddDailyMultiClickCount(DLEWAdmobInterstitialAdapter.this.getNetworkName(), DLEWAdmobInterstitialAdapter.this.mMultiClickCount);
            DLEWAdmobInterstitialAdapter.this.mMultiClickCount = 0;
            if (DLEWAdmobInterstitialAdapter.this.mThrottle.CheckThrottle(null)) {
                XLog.d(StringFog.decrypt("7sVBFa2r4YHpwlgV7qY=\n", "m6soYdSGkuI=\n") + DLEWAdmobInterstitialAdapter.this.mAdInfo.unitID + StringFog.decrypt("zCQpVTQ/ePtrhu3sQm9xpAO1iJFqBxyUYeyO1To3RPdVtuHkWA==\n", "7AkEddKK+RI=\n"));
            } else {
                DLEWAdmobInterstitialAdapter.this.load();
            }
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
        }

        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
            DLEWAdmobInterstitialAdapter.this.mInterstitialAd = null;
            XLog.d(StringFog.decrypt("7gkvpfOExifpDjalsIn8ZPwIMvHLzdgr+UcPv/7MxzfvDjK468X0IMgPKabMyNwo/gMDp+/HwWi7\n", "m2dG0YqptUQ=\n") + adError.getMessage());
            DLEWAdEventTrack.OnShowFailure(StringFog.decrypt("uNm/\n", "0bfMFd1gZkY=\n"), DLEWAdmobInterstitialAdapter.this.mRequestCode, DLEWAdmobInterstitialAdapter.this.mCacheSceneType, adError.getMessage(), DLEWAdmobInterstitialAdapter.this.getNetworkName());
            if (DLEWAdmobInterstitialAdapter.this.listener != null) {
                DLEWAdmobInterstitialAdapter.this.listener.onFialed();
            }
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
            DLEWAdmobInterstitialAdapter.this.load();
        }

        public void onAdShowedFullScreenContent() {
            XLog.d(StringFog.decrypt("rlAhbRhYCvqpVzhtW1UwubxRPDkgERT2uR4BdxUQC+qvVzxwABk4/YhWJ24yABr6vlssfAUwD/y1\nSmg0TFU=\n", "2z5IGWF1eZk=\n") + this + StringFog.decrypt("K0kgDw==\n", "C2QNL3BCn/U=\n") + DLEWAdmobInterstitialAdapter.this.mCacheSceneType);
            DLEWAdEventTrack.OnImpression(StringFog.decrypt("GbZG\n", "cNg1FeycF1A=\n"), DLEWAdmobInterstitialAdapter.this.mRequestCode, DLEWAdmobInterstitialAdapter.this.mCacheSceneType, DLEWAdmobInterstitialAdapter.this.getNetworkName());
            if (DLEWAdmobInterstitialAdapter.this.mThrottle.CheckNetworkNameControlled(DLEWAdmobInterstitialAdapter.this.getNetworkName())) {
                DLEWAdmobInterstitialAdapter.this.mThrottle.AddDailyShowCount();
            }
            DLEWAd.addAdDdisplayCount(false);
            DLEWAd.addAdDdisplayDailyCount();
            AdmobUtils.setAdmobFirstShow();
            GGAnalysis.afTrack(StringFog.decrypt("7XaUl8gN6DDtdqSNywDvOOp3lI6cF+Iy\n", "nh774K5khlk=\n"), null);
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobInterstitialAdapter$evleeEelE.class */
    public class evleeEelE extends InterstitialAdLoadCallback {

        /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobInterstitialAdapter$evleeEelE$EvLveE1.class */
        public class EvLveE1 implements Runnable {
            public EvLveE1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                DLEWAdmobInterstitialAdapter.this.load();
            }
        }

        public evleeEelE() {
        }

        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
            DLEWAdmobInterstitialAdapter.this.mLoading = false;
            XLog.d(StringFog.decrypt("mbwwVjnPWEyeuylWesJiD4u9LQIBhkZAjvIQTDSHWVyYuy1LIY5qS6C9OEYGg0JDibYcVCWMXwPM\nsTZGJdgL\n", "7NJZIkDiKy8=\n") + loadAdError.getCode() + StringFog.decrypt("n24U5GsFZsHDOhnudkYuiA==\n", "s05wgRhmFKg=\n") + loadAdError.getMessage());
            DLEWAdEventTrack.OnLoadFailure(StringFog.decrypt("Sb3J\n", "INO6/5uyOxY=\n"), DLEWAdmobInterstitialAdapter.this.mRequestCode, DLEWAdmobInterstitialAdapter.this.mCacheSceneType, StringFog.decrypt("AWi5cai++w==\n", "YgfdFJKe2/o=\n") + loadAdError.getCode() + StringFog.decrypt("0R7mdV0Uq3SNSut/QE35\n", "/T6CEC532R0=\n") + loadAdError.getMessage(), DLEWAdmobInterstitialAdapter.this.getNetworkName());
            DLEWAdmobInterstitialAdapter.access$908(DLEWAdmobInterstitialAdapter.this);
            new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, DLEWAdmobInterstitialAdapter.this.retryAttempt))));
        }

        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
            DLEWAdmobInterstitialAdapter.this.mInterstitialAd = interstitialAd;
            DLEWAdmobInterstitialAdapter.this.mLoading = false;
            DLEWAdmobInterstitialAdapter.this.mInterstitialAd.setFullScreenContentCallback(DLEWAdmobInterstitialAdapter.this.mFullScreenContentCallback);
            DLEWAdmobInterstitialAdapter.this.mInterstitialAd.setOnPaidEventListener(DLEWAdmobInterstitialAdapter.this.mOnPaidEventListener);
            XLog.d(StringFog.decrypt("tIA8PbUSxY+zhyU99h//zKaBIWmNW9uDo84cJ7haxJ+1hyEgrVP3iJOLNC21esCJr5p1aeESlg==\n", "we5VScw/tuw=\n") + DLEWAdmobInterstitialAdapter.this.mAdInfo.revenue);
            DLEWAdmobInterstitialAdapter.this.mAdInfo.networkName = DLEWAdmobInterstitialAdapter.this.getNetworkName();
            DLEWAdmobInterstitialAdapter.this.retryAttempt = 0;
            DLEWAdEventTrack.OnLoaded(StringFog.decrypt("5MGI\n", "ja/7dJpM1Ro=\n"), DLEWAdmobInterstitialAdapter.this.mRequestCode, DLEWAdmobInterstitialAdapter.this.mCacheSceneType, DLEWAdmobInterstitialAdapter.this.getNetworkName());
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobInterstitialAdapter$v1vLvLE.class */
    public class v1vLvLE implements OnPaidEventListener {
        public v1vLvLE() {
        }

        public void onPaidEvent(@NonNull AdValue adValue) {
            DLEWAdmobInterstitialAdapter.this.mAdInfo.revenue = AdmobUtils.changeLongRevenueToDouble(adValue.getValueMicros());
            AdmobUtils.setAdmobInterLastRevenue(DLEWAdmobInterstitialAdapter.this.mAdInfo.revenue);
            XLog.e(StringFog.decrypt("6PFH92Q4gBKwmGqWDxXSfKDDH65x\n", "AH/wEuuuZZo=\n") + DLEWAdmobInterstitialAdapter.this.mAdInfo.revenue);
            DLEWAdmobInterstitialAdapter.this.trackRevenue();
        }
    }

    public static /* synthetic */ int access$308(DLEWAdmobInterstitialAdapter dLEWAdmobInterstitialAdapter) {
        int i = dLEWAdmobInterstitialAdapter.mMultiClickCount;
        dLEWAdmobInterstitialAdapter.mMultiClickCount = i + 1;
        return i;
    }

    public static /* synthetic */ int access$908(DLEWAdmobInterstitialAdapter dLEWAdmobInterstitialAdapter) {
        int i = dLEWAdmobInterstitialAdapter.retryAttempt;
        dLEWAdmobInterstitialAdapter.retryAttempt = i + 1;
        return i;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getRequestCode() {
        return this.mRequestCode;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getNetworkName() {
        return StringFog.decrypt("V9zrdFDaVwdC0fB+\n", "NriGGzKFOWY=\n");
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public double checkRevenue(String str) {
        return AdmobUtils.checkRevenue(this.mThrottle, this.mAdInfo, isAvailable(), str, this.mAdType);
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public DLEWAdInfo getAdInfo() {
        return this.mAdInfo;
    }

    public void load() {
        if (this.mLoading) {
            return;
        }
        this.mLoading = true;
        XLog.d(StringFog.decrypt("xAKt0b3xoNvDBbTR/oey3NwDpviftb3L7EywwLeo/pWc\n", "sWzEpcTc07g=\n") + this.mAdInfo.unitID + StringFog.decrypt("9D4KiMbrH3NzmMIig78iKzGqmE2y3Q==\n", "1BMnqCNXn5Y=\n"));
        String uuid = UUID.randomUUID().toString();
        this.mRequestCode = uuid;
        DLEWAdEventTrack.OnRequest(this.mAdType, uuid, this.mCacheSceneType, getNetworkName());
        InterstitialAd.load(DLEWSDK.getActivity(), this.mAdInfo.unitID, new AdRequest.Builder().build(), this.mInterstitialAdLoadCallback);
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        this.mThrottle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.mAdInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.insID;
        dLEWAdInfo.revenue = AdmobUtils.getAdmobInterLastRevenue();
        XLog.d(StringFog.decrypt("er79DACqdTV9ueQMQ6dnMmK/9kVE\n", "D9CUeHmHBlY=\n") + new Gson().toJson(dLEWLimitConfig));
        if (this.mThrottle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("gx0cHrfXJjmEGgUe9No=\n", "9nN1as76VVo=\n") + this.mAdInfo.unitID + StringFog.decrypt("widt3thDQNBlhalnrhNJjw22zBqGeyS/b+/KXtZLfNxbtaVvtA==\n", "4gpA/j72wTk=\n"));
        } else {
            load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public boolean isAvailable() {
        return this.mInterstitialAd != null;
    }

    @Override // com.dlew.other.ad.core.DLEWiInterstitialAd
    public void show(String str, DLEWInterstitialListener dLEWInterstitialListener) {
        this.mCacheSceneType = str;
        this.listener = dLEWInterstitialListener;
        if (!NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("uY0u7yLCFNWFiQ==\n", "9+IOoUe2Y7o=\n"), getNetworkName());
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onFialed();
                return;
            }
            return;
        }
        if (isAvailable()) {
            this.mInterstitialAd.show(DLEWSDK.getActivity());
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
            return;
        }
        if (!this.mLoading) {
            load();
        }
        if (dLEWInterstitialListener != null) {
            dLEWInterstitialListener.onFialed();
        }
        DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("dkGyH9C0KYlZTP47\n", "OC6SXqbVQOU=\n"), getNetworkName());
    }

    public void trackRevenue() {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra();
        dLEWAdInfoExtra.requestCode = this.mRequestCode;
        dLEWAdInfoExtra.Revenue = this.mAdInfo.revenue;
        dLEWAdInfoExtra.NetworkName = getNetworkName();
        dLEWAdInfoExtra.AdFormat = StringFog.decrypt("/dvrQ/w=\n", "tJW/Bq4cweg=\n");
        GGAnalysis.trackImmediately(StringFog.decrypt("FnL2qf6CDVUeYP2f+IEiVR5l65jiig==\n", "exOO9pfvfSc=\n"), dLEWAdInfoExtra);
        DLEWRoas.SendMaxRevenueToFirebase(dLEWAdInfoExtra);
        DLEWRoas.CheckTaiChiTcpaAdRevenueEvent(this.mAdInfo.revenue);
        DLEWRoas.CheckTaichiTroasFirebaseAdRevenueEvent(this.mAdInfo.revenue);
        DLEWRoas.trackKwaiValueToAF(this.mAdInfo.revenue);
        DLEWRoas.sendRevenueToAF(dLEWAdInfoExtra);
    }
}