package com.dlew.other.ad.nm;

import android.app.Activity;
import android.os.Handler;
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
import z.k.api.NetAdError;
import z.k.api.NetInterstitialAd;
import z.k.api.NetInterstitialAdListener;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/nm/DLEWNMIntersitialAdapter.class */
public class DLEWNMIntersitialAdapter implements NetInterstitialAdListener, DLEWiInterstitialAd {
    private NetInterstitialAd interstitialAd;
    private DLEWInterstitialListener listener;
    private boolean mLoading;
    int retryAttempt;
    private DLEWAdThrottle m_throttle;
    private DLEWAdInfo m_adInfo;
    boolean isLoading;
    private String mCacheSceneType = StringFog.decrypt("8HmHcg==\n", "ngzrHlB2hhM=\n");
    private String mRequestCode = UUID.randomUUID().toString();
    private String mAdType = StringFog.decrypt("vG1h\n", "1QMSqoXeR3Q=\n");
    boolean showAndroidActivity = false;
    private String m_lastRequestCode = BuildConfig.VERSION_NAME;
    private int m_multiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/nm/DLEWNMIntersitialAdapter$EvLveE1.class */
    public class EvLveE1 implements Runnable {
        public EvLveE1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DLEWNMIntersitialAdapter.this.Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getRequestCode() {
        return this.mRequestCode;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getNetworkName() {
        return StringFog.decrypt("q8RnCm7Yw3Sg\n", "xak4ZA+sqgI=\n");
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public double checkRevenue(String str) {
        if (this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName) && this.m_throttle.CheckThrottle(str)) {
            return -1.0d;
        }
        if (isAvailable()) {
            return this.m_adInfo.revenue;
        }
        XLog.d(StringFog.decrypt("jK0Ty6K41XyLqgrL4bX9cZSeIda15vs=\n", "+cN6v9uVph8=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("obrVfqj6a48tNh3n8YNW42cAWLny9SLEGQ==\n", "gZf4Xk5mx2k=\n"));
        return this.m_throttle.CheckThrottle(str) ? -1.0d : 0.0d;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public DLEWAdInfo getAdInfo() {
        return this.m_adInfo;
    }

    public void Load() {
        if (this.isLoading) {
            return;
        }
        XLog.d(StringFog.decrypt("iHtJlYce5pOPfFCVxBPOnpBIe4iQQMg=\n", "/RUg4f4zlfA=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("VakwKE81BwbSD/iCCmE6XlVhpLdPGA0=\n", "dYQdCKqJh+M=\n"));
        String uuid = UUID.randomUUID().toString();
        this.mRequestCode = uuid;
        DLEWAdEventTrack.OnRequest(this.mAdType, uuid, this.mCacheSceneType, getNetworkName());
        this.mLoading = true;
        NetInterstitialAd netInterstitialAd = this.interstitialAd;
        if (netInterstitialAd != null) {
            netInterstitialAd.destroy();
            this.interstitialAd = null;
        }
        NetInterstitialAd netInterstitialAd2 = new NetInterstitialAd(DLEWSDK.getActivity(), this.m_throttle.getConfig().insID);
        this.interstitialAd = netInterstitialAd2;
        netInterstitialAd2.setNMInterstitialAdListener(this);
        this.interstitialAd.load();
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        this.m_throttle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.m_adInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.insID;
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("RNwccgBMilND2wVyQ0E=\n", "MbJ1Bnlh+TA=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("1+fZRaH84DJwRR3816zpbRh2eIH/xIRdei9+xa/03D5OdRH0zQ==\n", "98r0ZUdJYds=\n"));
        } else {
            Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public boolean isAvailable() {
        return this.interstitialAd.isReady();
    }

    @Override // com.dlew.other.ad.core.DLEWiInterstitialAd
    public void show(String str, DLEWInterstitialListener dLEWInterstitialListener) {
        this.mCacheSceneType = str;
        this.listener = dLEWInterstitialListener;
        if (!NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("636KLxCji4PXeg==\n", "pRGqYXXX/Ow=\n"), getNetworkName());
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onFialed();
                return;
            }
            return;
        }
        if (isAvailable()) {
            this.interstitialAd.showAd(DLEWSDK.getActivity());
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
            return;
        }
        if (!this.mLoading) {
            Load();
        }
        if (dLEWInterstitialListener != null) {
            dLEWInterstitialListener.onFialed();
        }
        DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("SbFr1QdfddtmvCfx\n", "B95LlHE+HLc=\n"), getNetworkName());
    }

    public void trackRevenue() {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra();
        dLEWAdInfoExtra.requestCode = this.mRequestCode;
        dLEWAdInfoExtra.Revenue = this.m_adInfo.revenue;
        dLEWAdInfoExtra.NetworkName = getNetworkName();
        dLEWAdInfoExtra.AdFormat = StringFog.decrypt("IMGA2go=\n", "aY/Un1iud28=\n");
        GGAnalysis.trackImmediately(StringFog.decrypt("jraQtbAAD9uGpJuDtgMg24ahjYSsCA==\n", "49fo6tltf6k=\n"), dLEWAdInfoExtra);
        DLEWRoas.SendMaxRevenueToFirebase(dLEWAdInfoExtra);
        DLEWRoas.CheckTaiChiTcpaAdRevenueEvent(this.m_adInfo.revenue);
        DLEWRoas.CheckTaichiTroasFirebaseAdRevenueEvent(this.m_adInfo.revenue);
        DLEWRoas.trackKwaiValueToAF(this.m_adInfo.revenue);
        DLEWRoas.sendRevenueToAF(dLEWAdInfoExtra);
    }

    public void onInterstitialAdLoaded() {
        DLEWAdEventTrack.OnLoaded(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        this.m_adInfo.revenue = this.interstitialAd.getEcpm() / 1000.0d;
        XLog.d(StringFog.decrypt("MNQnMkZdyio30z4yBVDwaSLVOmZRHZkAK84rNEwE0D0s2yIHWyLcKCHDCzBaHs1pZZdjZg==\n", "RbpORj9wuUk=\n") + this.m_adInfo.revenue);
        this.m_adInfo.networkName = getNetworkName();
        this.mLoading = false;
        this.retryAttempt = 0;
    }

    public void onInterstitialAdLoadFail(NetAdError netAdError) {
        XLog.d(StringFog.decrypt("XaoZqE6mzkNarQCoDav0AE+rBPxZ5p1pRrAVrkT/1FRBpRydU8fSQUyCEbVb7tllXqEeqBur3k9M\noUr8\n", "KMRw3DeLvSA=\n") + netAdError.getErrorCode() + StringFog.decrypt("HBv6x51vOWtAT/fNgCxxIg==\n", "MDueou4MSwI=\n") + netAdError.getErrorMsg());
        DLEWAdEventTrack.OnLoadFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("yZvAlfA5iA==\n", "qvSk8MoZqLE=\n") + netAdError.getErrorCode() + StringFog.decrypt("9iU8uIiPfEqqcTGyldYu\n", "2gVY3fvsDiM=\n") + netAdError.getErrorMsg(), getNetworkName());
        this.mLoading = false;
        this.retryAttempt = this.retryAttempt + 1;
        new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, r1))));
    }

    public void onInterstitialAdShow() {
        XLog.d(StringFog.decrypt("50gu5v6r1nngTzfmvabsOvVJM7Lp64VT/FIi4PTyzG77RyvT49XNdeV1MvHk48B+90IC5OLo0Tq/\nC2c=\n", "kiZHkoeGpRo=\n") + this + StringFog.decrypt("BLp79w==\n", "JJdW1zT7HII=\n") + this.mCacheSceneType);
        DLEWAdEventTrack.OnImpression(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        if (this.m_throttle.CheckNetworkNameControlled(getNetworkName())) {
            this.m_throttle.AddDailyShowCount();
        }
        DLEWAd.addAdDdisplayCount(false);
        DLEWAd.addAdDdisplayDailyCount();
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
        GGAnalysis.afTrack(StringFog.decrypt("ajwQx9cgMEFqPCDd1C03SW09EN6DOjpD\n", "GVR/sLFJXig=\n"), null);
        trackRevenue();
    }

    public void onInterstitialAdClicked() {
        if (this.m_throttle.CheckNetworkNameControlled(getNetworkName())) {
            this.m_throttle.AddDailyClickCount();
            if (this.m_lastRequestCode.equals(this.mRequestCode)) {
                this.m_multiClickCount++;
            } else {
                this.m_lastRequestCode = this.mRequestCode;
                this.m_multiClickCount = 1;
            }
        }
        XLog.d(StringFog.decrypt("yZ4LjoISZYzOmRKOwR9fz9ufFtqVUjam0oQHiIhLf5vVkQ67n3x6ht+bB56+SXOByA==\n", "vPBi+vs/Fu8=\n"));
        DLEWAdEventTrack.OnClicked(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
    }

    public void onInterstitialAdClose() {
        XLog.d(StringFog.decrypt("EG+17Q8J0YgXaKztTATrywJuqLk/StaOF3Ko8AJNw4ckZZ/1GVfHjyB3ufcC\n", "ZQHcmXYkous=\n"));
        DLEWInterstitialListener dLEWInterstitialListener = this.listener;
        if (dLEWInterstitialListener != null) {
            dLEWInterstitialListener.onHide();
        }
        DLEWAdInfo dLEWAdInfo = this.m_adInfo;
        dLEWAdInfo.revenue = 0.0d;
        dLEWAdInfo.networkName = BuildConfig.VERSION_NAME;
        this.m_throttle.AddDailyMultiClickCount(getNetworkName(), this.m_multiClickCount);
        this.m_multiClickCount = 0;
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("uG+hSAZHdEO/aLhIRUo=\n", "zQHIPH9qByA=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("BS4QIs+WPPiijNSbucY1p8q/seaRrliXqOa3osGeAPScvNiTow==\n", "JQM9AikjvRE=\n"));
        } else {
            Load();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
    }

    public void onInterstitialAdVideoStart() {
    }

    public void onInterstitialAdVideoEnd() {
    }

    public void onInterstitialAdVideoError(NetAdError netAdError) {
        XLog.d(StringFog.decrypt("2+XOsyL2Novc4tezYfsMyMnk0+c1tmWhwP/CtSivLJzH6suGP4gth9nNxq43viGt2O7Js3f7\n", "rounx1vbReg=\n") + netAdError.getErrorMsg());
        DLEWAdEventTrack.OnShowFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, netAdError.getErrorMsg(), getNetworkName());
        DLEWInterstitialListener dLEWInterstitialListener = this.listener;
        if (dLEWInterstitialListener != null) {
            dLEWInterstitialListener.onFialed();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
        Load();
    }
}