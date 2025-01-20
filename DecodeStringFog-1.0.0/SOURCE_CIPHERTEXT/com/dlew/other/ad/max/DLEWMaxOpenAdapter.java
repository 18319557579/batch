package com.dlew.other.ad.max;

import android.app.Activity;
import android.os.Handler;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAppOpenAd;
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
import com.dlew.other.ad.core.DLEWiOpenAd;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/max/DLEWMaxOpenAdapter.class */
public class DLEWMaxOpenAdapter implements MaxAdListener, MaxAdRevenueListener, DLEWiOpenAd {
    private MaxAppOpenAd maxAppOpenAd;
    private DLEWInterstitialListener listener;
    private boolean mLoading;
    int retryAttempt;
    private DLEWAdThrottle m_throttle;
    private DLEWAdInfo m_adInfo;
    private String mCacheSceneType = StringFog.decrypt("H7d6uQ==\n", "ccIW1fmt0Ck=\n");
    private String mRequestCode = UUID.randomUUID().toString();
    private String mAdType = StringFog.decrypt("0Gaf7gqXTDI=\n", "sRbvsWXnKVw=\n");
    boolean showAndroidActivity = false;
    private String m_lastRequestCode = BuildConfig.VERSION_NAME;
    private int m_multiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/max/DLEWMaxOpenAdapter$EvLveE1.class */
    public class EvLveE1 implements Runnable {
        public EvLveE1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DLEWMaxOpenAdapter.this.Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getRequestCode() {
        return this.mRequestCode;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getNetworkName() {
        return this.m_adInfo.networkName;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public double checkRevenue(String str) {
        if (this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName) && this.m_throttle.CheckThrottle(str)) {
            return -1.0d;
        }
        if (isAvailable()) {
            return this.m_adInfo.revenue;
        }
        XLog.d(StringFog.decrypt("YFKokdtkaalnVbGRmGl3q20crpXHJzo=\n", "FTzB5aJJGso=\n") + this.m_throttle.getConfig().openID + StringFog.decrypt("D1vTr5f2HhOD1xs2zo8jf8nhXmjN+VdYtw==\n", "L3b+j3FqsvU=\n"));
        return this.m_throttle.CheckThrottle(str) ? -1.0d : 0.0d;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public DLEWAdInfo getAdInfo() {
        return this.m_adInfo;
    }

    public void Load() {
        XLog.d(StringFog.decrypt("/9O7QHLE70T41KJAMbLxRvLgiVt7jPJ6qg==\n", "ir3SNAvpnCc=\n") + this.m_throttle.getConfig().openID + StringFog.decrypt("OLzqZt813BW/GiLMmmHhTf0oeKOrAw==\n", "GJHHRjqJXPA=\n"));
        String uuid = UUID.randomUUID().toString();
        this.mRequestCode = uuid;
        DLEWAdEventTrack.OnRequest(this.mAdType, uuid, this.mCacheSceneType, StringFog.decrypt("ESfp\n", "fEaRHHgLZEA=\n"));
        this.mLoading = true;
        this.maxAppOpenAd.loadAd();
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        this.m_throttle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.m_adInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.openID;
        MaxAppOpenAd maxAppOpenAd = new MaxAppOpenAd(dLEWLimitConfig.openID, activity);
        this.maxAppOpenAd = maxAppOpenAd;
        maxAppOpenAd.setListener(this);
        this.maxAppOpenAd.setRevenueListener(this);
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("6LJULC6VrqvvtU0sbZg=\n", "ndw9WFe43cg=\n") + this.m_throttle.getConfig().openID + StringFog.decrypt("Qu6MQYAvb87lTEj49n9mkY1/LYXeFwuh7yYrwY4nU8LbfETw7A==\n", "YsOhYWaa7ic=\n"));
        } else {
            Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public boolean isAvailable() {
        return this.maxAppOpenAd.isReady();
    }

    public void onAdLoaded(MaxAd maxAd) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("pvMBti0oeB6h9Bi2biVCXbTyHOI5ZHNdvO0NrBVhWRiy+RGHImBlCfO9Re90\n", "051owlQFC30=\n") + maxAd.getAdUnitId() + StringFog.decrypt("Alo=\n", "XQV1EUECt04=\n") + maxAd.getRevenue());
            DLEWAdEventTrack.OnLoaded(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
            this.m_adInfo.revenue = maxAd.getRevenue();
            this.m_adInfo.networkName = maxAd.getNetworkName();
            this.mLoading = false;
            this.retryAttempt = 0;
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_OPEN_READY));
        }
    }

    public void onAdDisplayed(MaxAd maxAd) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("9vJQJWPe7Enx9UklINPWCuTzTXF3kucK7OxcP1uXzELs62okeZD6T+f5XRRslvFeo7EUcQ==\n", "g5w5URrznyo=\n") + this + StringFog.decrypt("lebAlQ==\n", "tcvtta+2+5Q=\n") + this.mCacheSceneType);
            DLEWAdEventTrack.OnImpression(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
            if (this.m_throttle.CheckNetworkNameControlled(maxAd.getNetworkName())) {
                this.m_throttle.AddDailyShowCount();
            }
            DLEWAd.addAdDdisplayCount(false);
            DLEWAd.addAdDdisplayDailyCount();
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
            GGAnalysis.afTrack(StringFog.decrypt("tljDo67woTO2WPO5rf2mO7FZw7r66qsx\n", "xTCs1MiZz1o=\n"), null);
        }
    }

    public void onAdHidden(MaxAd maxAd) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("Uy9mrYYzjY9UKH+txT63zEEue/mSf4bMSTFqt756vYBJMmq9umibglI=\n", "JkEP2f8e/uw=\n"));
            DLEWInterstitialListener dLEWInterstitialListener = this.listener;
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onHide();
            }
            DLEWAdInfo dLEWAdInfo = this.m_adInfo;
            dLEWAdInfo.revenue = 0.0d;
            dLEWAdInfo.networkName = BuildConfig.VERSION_NAME;
            this.m_throttle.AddDailyMultiClickCount(maxAd.getNetworkName(), this.m_multiClickCount);
            this.m_multiClickCount = 0;
            if (this.m_throttle.CheckThrottle(null)) {
                XLog.d(StringFog.decrypt("hq0Y0OKxOJ2BqgHQobw=\n", "88NxpJucS/4=\n") + this.m_throttle.getConfig().openID + StringFog.decrypt("TOqwKO32CKrrSHSRm6YB9YN7EeyzzmzF4SIXqOP+NKbVeHiZgQ==\n", "bMedCAtDiUM=\n"));
            } else {
                Load();
            }
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
        }
    }

    public void onAdClicked(MaxAd maxAd) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            if (this.m_throttle.CheckNetworkNameControlled(maxAd.getNetworkName())) {
                this.m_throttle.AddDailyClickCount();
                if (this.m_lastRequestCode.equals(this.mRequestCode)) {
                    this.m_multiClickCount++;
                } else {
                    this.m_lastRequestCode = this.mRequestCode;
                    this.m_multiClickCount = 1;
                }
            }
            XLog.d(StringFog.decrypt("oQiKA938lpSmD5MDnvGs17MJl1fJsJ3XuxaGGeW1ppu9BYgSwJSTkroS\n", "1Gbjd6TR5fc=\n"));
            DLEWAdEventTrack.OnClicked(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
        }
    }

    public void onAdLoadFailed(String str, MaxError maxError) {
        if (str.equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("iVFrFZeZkRWOVnIV1JSrVptQdkGD1ZpWk09nD6/QrhmdW0QAh9iHErlJZw+amMIVk1tnW84=\n", "/D8CYe604nY=\n") + maxError.getCode() + StringFog.decrypt("pBoyimE9F3v4Tj+AfH5fMg==\n", "iDpW7xJeZRI=\n") + maxError.getMessage());
            DLEWAdEventTrack.OnLoadFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("HWxKql/lQQ==\n", "fgMuz2XFYdM=\n") + maxError.getCode() + StringFog.decrypt("IhoEnAM2n/Z+TgmWHm/N\n", "Djpg+XBV7Z8=\n") + maxError.getMessage(), StringFog.decrypt("voOX\n", "0+Lv2vZmCbM=\n"));
            this.mLoading = false;
            this.retryAttempt = this.retryAttempt + 1;
            new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, r1))));
        }
    }

    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("DTATwXrYf8oKNwrBOdVFiR8xDpVulHSJFy4f20KRX8EXKTzUaplpzT0oH9t32Sw=\n", "eF56tQP1DKk=\n") + maxError.toString());
            DLEWAdEventTrack.OnShowFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxError.toString(), maxAd.getNetworkName());
            DLEWInterstitialListener dLEWInterstitialListener = this.listener;
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onFialed();
            }
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
            Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiOpenAd
    public void show(String str, DLEWInterstitialListener dLEWInterstitialListener) {
        this.mCacheSceneType = str;
        this.listener = dLEWInterstitialListener;
        if (!NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("0ul5v3cZ7dbu7Q==\n", "nIZZ8RJtmrk=\n"), getNetworkName());
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onFialed();
                return;
            }
            return;
        }
        if (!isAvailable()) {
            if (!this.mLoading) {
                Load();
            }
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onFialed();
            }
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("A/XZpYMWaLUs+JWB\n", "TZr55PV3Adk=\n"), getNetworkName());
            return;
        }
        if (this.m_throttle.getConfig().showLoading <= 0 || !this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName)) {
            this.maxAppOpenAd.showAd();
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        } else {
            this.maxAppOpenAd.showAd();
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        }
    }

    public void onAdRevenuePaid(MaxAd maxAd) {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra(maxAd, this.mRequestCode);
        GGAnalysis.trackImmediately(StringFog.decrypt("gxlB8IHfTkmLC0rGh9xhSYsOXMGd1w==\n", "7ng5r+iyPjs=\n"), dLEWAdInfoExtra);
        DLEWRoas.SendMaxRevenueToFirebase(dLEWAdInfoExtra);
        DLEWRoas.CheckTaiChiTcpaAdRevenueEvent(maxAd.getRevenue());
        DLEWRoas.CheckTaichiTroasFirebaseAdRevenueEvent(maxAd.getRevenue());
        DLEWRoas.trackKwaiValueToAF(maxAd.getRevenue());
        DLEWRoas.sendRevenueToAF(dLEWAdInfoExtra);
    }
}