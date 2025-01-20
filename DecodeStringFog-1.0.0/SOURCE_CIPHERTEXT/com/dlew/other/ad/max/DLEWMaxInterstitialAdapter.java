package com.dlew.other.ad.max;

import android.app.Activity;
import android.os.Handler;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
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
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/max/DLEWMaxInterstitialAdapter.class */
public class DLEWMaxInterstitialAdapter implements MaxAdListener, DLEWiInterstitialAd, MaxAdRevenueListener {
    private MaxInterstitialAd interstitialAd;
    private DLEWInterstitialListener listener;
    private boolean mLoading;
    int retryAttempt;
    private DLEWAdThrottle m_throttle;
    private DLEWAdInfo m_adInfo;
    private String mCacheSceneType = StringFog.decrypt("Pj4FxA==\n", "UEtpqO/bjmY=\n");
    private String mRequestCode = UUID.randomUUID().toString();
    private String mAdType = StringFog.decrypt("ad/V\n", "ALGmt3KZLJQ=\n");
    boolean showAndroidActivity = false;
    private String m_lastRequestCode = BuildConfig.VERSION_NAME;
    private int m_multiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/max/DLEWMaxInterstitialAdapter$EvLveE1.class */
    public class EvLveE1 implements Runnable {
        public EvLveE1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DLEWMaxInterstitialAdapter.this.Load();
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
        XLog.d(StringFog.decrypt("7YQ0jr4b9d3qgy2O/RY=\n", "mOpd+sc2hr4=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("ZtsYHmspyiDqV9CHMlD3TKBhldkxJoNr3g==\n", "RvY1Po21ZsY=\n"));
        return this.m_throttle.CheckThrottle(str) ? -1.0d : 0.0d;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public DLEWAdInfo getAdInfo() {
        return this.m_adInfo;
    }

    public void Load() {
        XLog.d(StringFog.decrypt("g5Gn+0G7nVKElr77As2DUI6ileZW5bMR\n", "9v/OjziW7jE=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("diU3HVVmF1rxg/+3EDIqArOxpdghUA==\n", "VggaPbDal78=\n"));
        String uuid = UUID.randomUUID().toString();
        this.mRequestCode = uuid;
        DLEWAdEventTrack.OnRequest(this.mAdType, uuid, this.mCacheSceneType, StringFog.decrypt("nVqg\n", "8DvYEHXwQTQ=\n"));
        this.mLoading = true;
        this.interstitialAd.loadAd();
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        this.m_throttle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.m_adInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.insID;
        MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(dLEWLimitConfig.insID, activity);
        this.interstitialAd = maxInterstitialAd;
        maxInterstitialAd.setListener(this);
        this.interstitialAd.setRevenueListener(this);
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("If1XVSmhSaEm+k5Vaqw=\n", "VJM+IVCMOsI=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("MQkmU7On5CWWq+Lqxfftev6Yh5ftn4BKnMGB072v2Cmom+7i3w==\n", "ESQLc1USZcw=\n"));
        } else {
            Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public boolean isAvailable() {
        return this.interstitialAd.isReady();
    }

    public void onAdLoaded(MaxAd maxAd) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("LSyizKw7jrwqK7vM7za0/z8tv5i4d4X/ESy/3adlibYsK6rUlHKvujkmsv2jc5OreGLmlfU=\n", "WELLuNUW/d8=\n") + maxAd.getAdUnitId() + StringFog.decrypt("FDg=\n", "S2dpt0Thkxc=\n") + maxAd.getRevenue());
            DLEWAdEventTrack.OnLoaded(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
            this.m_adInfo.revenue = maxAd.getRevenue();
            this.m_adInfo.networkName = maxAd.getNetworkName();
            this.mLoading = false;
            this.retryAttempt = 0;
        }
    }

    public void onAdDisplayed(MaxAd maxAd) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("ulg0LWK7Rb+9Xy0tIbZ//KhZKXl29078hlgpPGnlQrW7Xzw1WvJltKBBDix49VO5q1M5HG3zWKjv\nG3B5\n", "zzZdWRuWNtw=\n") + this + StringFog.decrypt("FpdcgA==\n", "NrpxoPYPt6k=\n") + this.mCacheSceneType);
            DLEWAdEventTrack.OnImpression(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
            if (this.m_throttle.CheckNetworkNameControlled(maxAd.getNetworkName())) {
                this.m_throttle.AddDailyShowCount();
            }
            DLEWAd.addAdDdisplayCount(false);
            DLEWAd.addAdDdisplayDailyCount();
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
            GGAnalysis.afTrack(StringFog.decrypt("/5TP0ak8b6r/lP/LqjFooviVz8j9JmWo\n", "jPygps9VAcM=\n"), null);
        }
    }

    public void onAdHidden(MaxAd maxAd) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("QuRJuz8IUgNF41C7fAVoQFDlVO8rRFlAfuRUqjRWVQlD40GjB0FiDFj5RasDU0QOQw==\n", "N4ogz0YlIWA=\n"));
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
                XLog.d(StringFog.decrypt("arnejd737ARtvseNnfo=\n", "H9e3+afan2c=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("dyfaSiWFpJzQhR7zU9Wtw7i2e457vcDz2u99yiuNmJDutRL7SQ==\n", "Vwr3asMwJXU=\n"));
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
            XLog.d(StringFog.decrypt("TDTcmqPvBp5LM8Wa4OI83V41wc63ow3dcDTBi6ixAZRNM9SCm6Y2kVA53ou+hwOYVy4=\n", "OVq17trCdf0=\n"));
            DLEWAdEventTrack.OnClicked(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
        }
    }

    public void onAdLoadFailed(String str, MaxError maxError) {
        if (str.equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("gVLT+dYCgTmGVcr5lQ+7epNTzq3CTop6vVLO6N1chjOAVdvh7ku+NZVY/OzGQ5c+sUrf49sD0jmb\nWN+3jw==\n", "9Dy6ja8v8lo=\n") + maxError.getCode() + StringFog.decrypt("/I8acsasjdeg2xd42+/Fng==\n", "0K9+F7XP/74=\n") + maxError.getMessage());
            DLEWAdEventTrack.OnLoadFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("ZvrMu/4nLQ==\n", "BZWo3sQHDYI=\n") + maxError.getCode() + StringFog.decrypt("GcL/c/d5gVtFlvJ56iDT\n", "NeKbFoQa8zI=\n") + maxError.getMessage(), StringFog.decrypt("9xpd\n", "mnsljOGbK4U=\n"));
            this.mLoading = false;
            this.retryAttempt = this.retryAttempt + 1;
            new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, r1))));
        }
    }

    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("ZKyE+sRqboljq536h2dUynatma7QJmXKWKyZ6880aYNlq4zi/CNOgn61q+/UK3iOVLSI4MlrPQ==\n", "EcLtjr1HHeo=\n") + maxError.toString());
            DLEWAdEventTrack.OnShowFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxError.toString(), maxAd.getNetworkName());
            DLEWInterstitialListener dLEWInterstitialListener = this.listener;
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onFialed();
            }
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
            Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiInterstitialAd
    public void show(String str, DLEWInterstitialListener dLEWInterstitialListener) {
        this.mCacheSceneType = str;
        this.listener = dLEWInterstitialListener;
        if (!NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("6H9P3pR8IlHUew==\n", "phBvkPEIVT4=\n"), getNetworkName());
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
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("LNWJFSOSHs8D2MUx\n", "YrqpVFXzd6M=\n"), getNetworkName());
            return;
        }
        if (this.m_throttle.getConfig().showLoading <= 0 || !this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName)) {
            this.interstitialAd.showAd();
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        } else {
            this.interstitialAd.showAd();
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        }
    }

    public void onAdRevenuePaid(MaxAd maxAd) {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra(maxAd, this.mRequestCode);
        GGAnalysis.trackImmediately(StringFog.decrypt("SZaf6uLoudpBhJTc5OuW2kGBgtv+4A==\n", "JPfntYuFyag=\n"), dLEWAdInfoExtra);
        DLEWRoas.SendMaxRevenueToFirebase(dLEWAdInfoExtra);
        DLEWRoas.CheckTaiChiTcpaAdRevenueEvent(maxAd.getRevenue());
        DLEWRoas.CheckTaichiTroasFirebaseAdRevenueEvent(maxAd.getRevenue());
        DLEWRoas.trackKwaiValueToAF(maxAd.getRevenue());
        DLEWRoas.sendRevenueToAF(dLEWAdInfoExtra);
    }
}