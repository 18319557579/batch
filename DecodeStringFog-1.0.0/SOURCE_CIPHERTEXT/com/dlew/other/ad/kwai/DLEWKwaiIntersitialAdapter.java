package com.dlew.other.ad.kwai;

import android.app.Activity;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.kwai.network.sdk.KwaiAdSDK;
import com.kwai.network.sdk.api.KwaiAdLoaderManager;
import com.kwai.network.sdk.constant.KwaiError;
import com.kwai.network.sdk.loader.business.interstitial.data.KwaiInterstitialAd;
import com.kwai.network.sdk.loader.business.interstitial.data.KwaiInterstitialAdConfig;
import com.kwai.network.sdk.loader.business.interstitial.data.KwaiInterstitialAdRequest;
import com.kwai.network.sdk.loader.business.interstitial.interf.IKwaiInterstitialAdListener;
import com.kwai.network.sdk.loader.common.interf.AdLoadListener;
import com.kwai.network.sdk.loader.common.interf.IKwaiAdLoader;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/kwai/DLEWKwaiIntersitialAdapter.class */
public class DLEWKwaiIntersitialAdapter implements DLEWiInterstitialAd, AdLoadListener<KwaiInterstitialAd>, IKwaiInterstitialAdListener {
    private KwaiInterstitialAd mKwaiInterstitialAd;
    private DLEWInterstitialListener listener;
    private boolean mLoading;
    int retryAttempt;
    private DLEWAdThrottle m_throttle;
    private DLEWAdInfo m_adInfo;
    boolean isLoading;
    private IKwaiAdLoader<KwaiInterstitialAdRequest> mKwaiInterstitialAdLoader = null;
    private String mCacheSceneType = StringFog.decrypt("cGOzWg==\n", "HhbfNmmects=\n");
    private String mRequestCode = UUID.randomUUID().toString();
    private String mAdType = StringFog.decrypt("VYlG\n", "POc1oDxqRE4=\n");
    boolean showAndroidActivity = false;
    private String m_lastRequestCode = BuildConfig.VERSION_NAME;
    private int m_multiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/kwai/DLEWKwaiIntersitialAdapter$EvLveE1.class */
    public class EvLveE1 implements Runnable {
        public EvLveE1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DLEWKwaiIntersitialAdapter.this.Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getRequestCode() {
        return this.mRequestCode;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getNetworkName() {
        return StringFog.decrypt("FW5VwBX9KJUXb1E=\n", "fhk0qUqTSeE=\n");
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public double checkRevenue(String str) {
        if (this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName) && this.m_throttle.CheckThrottle(str)) {
            return -1.0d;
        }
        if (isAvailable()) {
            return this.m_adInfo.revenue;
        }
        XLog.d(StringFog.decrypt("H/SLuAR/yF8Y85K4R3LgVx37i5EmO9VPNw==\n", "aprizH1Suzw=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("UUkUNYC0ng7dxdys2c2jYpfzmfLau9dF6Q==\n", "cWQ5FWYoMug=\n"));
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
        XLog.d(StringFog.decrypt("C353wP2aa3EMeW7AvuxzZR95Q+/t2WtPXg==\n", "fhAetIS3GBI=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("fOL/MGAuPsv7RDeaJXoDk7l2bfUUGA==\n", "XM/SEIWSvi4=\n"));
        String uuid = UUID.randomUUID().toString();
        this.mRequestCode = uuid;
        DLEWAdEventTrack.OnRequest(this.mAdType, uuid, this.mCacheSceneType, getNetworkName());
        this.mLoading = true;
        KwaiAdLoaderManager kwaiAdLoaderManager = KwaiAdSDK.getKwaiAdLoaderManager();
        if (kwaiAdLoaderManager != null) {
            IKwaiAdLoader<KwaiInterstitialAdRequest> buildInterstitialAdLoader = kwaiAdLoaderManager.buildInterstitialAdLoader(new KwaiInterstitialAdConfig.Builder(this).withKwaiInterstitialAdListener(this).build());
            this.mKwaiInterstitialAdLoader = buildInterstitialAdLoader;
            buildInterstitialAdLoader.loadAd(new KwaiInterstitialAdRequest(this.m_throttle.getConfig().insID));
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        this.m_throttle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.m_adInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.insID;
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("9B70lG/J1u7zGe2ULMQ=\n", "gXCd4BbkpY0=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("+CvhNjKA8LJfiSWPRND57Te6QPJsuJTdVeNGtjyIzL5huSmHXg==\n", "2AbMFtQ1cVs=\n"));
        } else {
            Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public boolean isAvailable() {
        KwaiInterstitialAd kwaiInterstitialAd = this.mKwaiInterstitialAd;
        return kwaiInterstitialAd != null && kwaiInterstitialAd.isReady();
    }

    @Override // com.dlew.other.ad.core.DLEWiInterstitialAd
    public void show(String str, DLEWInterstitialListener dLEWInterstitialListener) {
        this.mCacheSceneType = str;
        this.listener = dLEWInterstitialListener;
        if (!NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("kS8X3pqwBTOtKw==\n", "30A3kP/Eclw=\n"), getNetworkName());
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onFialed();
                return;
            }
            return;
        }
        if (isAvailable()) {
            this.mKwaiInterstitialAd.show(DLEWSDK.getActivity());
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
            return;
        }
        if (!this.mLoading) {
            Load();
        }
        if (dLEWInterstitialListener != null) {
            dLEWInterstitialListener.onFialed();
        }
        DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("tXIOP57bai2af0Ib\n", "+x0ufui6A0E=\n"), getNetworkName());
    }

    public void trackRevenue() {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra();
        dLEWAdInfoExtra.requestCode = this.mRequestCode;
        dLEWAdInfoExtra.Revenue = this.m_adInfo.revenue;
        dLEWAdInfoExtra.NetworkName = getNetworkName();
        dLEWAdInfoExtra.AdFormat = StringFog.decrypt("zf0jN6Q=\n", "hLN3cvaB4oA=\n");
        GGAnalysis.trackImmediately(StringFog.decrypt("8KlJDiN5NzP4u0I4JXoYM/i+VD8/cQ==\n", "ncgxUUoUR0E=\n"), dLEWAdInfoExtra);
        DLEWRoas.SendMaxRevenueToFirebase(dLEWAdInfoExtra);
        DLEWRoas.CheckTaiChiTcpaAdRevenueEvent(this.m_adInfo.revenue);
        DLEWRoas.CheckTaichiTroasFirebaseAdRevenueEvent(this.m_adInfo.revenue);
        DLEWRoas.trackKwaiValueToAF(this.m_adInfo.revenue);
        DLEWRoas.sendRevenueToAF(dLEWAdInfoExtra);
    }

    public void onAdLoadStart(@Nullable String str) {
    }

    public void onAdLoadFailed(@Nullable String str, @NonNull KwaiError kwaiError) {
        XLog.d(StringFog.decrypt("3fZeajo+22Pa8UdqeTPhIM/3Qz4IZMlpiNFZaiZh23TB7F5/L1LMTMf5U1giesRlzN1Bey1nhCDL\n91N7eTM=\n", "qJg3HkMTqAA=\n") + kwaiError.getCode() + StringFog.decrypt("7iz3xBvK1gWyePrOBomeTA==\n", "wgyToWippGw=\n") + kwaiError.getMsg());
        DLEWAdEventTrack.OnLoadFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("vo9pQZrU8Q==\n", "3eANJKD00aM=\n") + kwaiError.getCode() + StringFog.decrypt("RWSGKTLZQBgZMIsjL4AS\n", "aUTiTEG6MnE=\n") + kwaiError.getMsg(), getNetworkName());
        this.mLoading = false;
        this.retryAttempt = this.retryAttempt + 1;
        new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, r1))));
    }

    public void onAdShow() {
        XLog.d(StringFog.decrypt("EGPxeMi/zXAXZOh4i7L3MwJi7Cz65d96RUT2eNTgzWcMefFt3dPaQA1i71/E8d12AGn9aPTk230R\nLbUhkQ==\n", "ZQ2YDLGSvhM=\n") + this + StringFog.decrypt("B7tZDQ==\n", "J5Z0LQFwqUM=\n") + this.mCacheSceneType);
        DLEWAdEventTrack.OnImpression(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        if (this.m_throttle.CheckNetworkNameControlled(getNetworkName())) {
            this.m_throttle.AddDailyShowCount();
        }
        DLEWAd.addAdDdisplayCount(false);
        DLEWAd.addAdDdisplayDailyCount();
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
        GGAnalysis.afTrack(StringFog.decrypt("0srJ9uGzwd/Syvns4r7G19XLye+1qcvd\n", "oaKmgYfar7Y=\n"), null);
        trackRevenue();
    }

    public void onAdShowFailed(@NonNull KwaiError kwaiError) {
        XLog.d(StringFog.decrypt("0wE8tXNX2lfUBiW1MFrgFMEAIeFBDchdhiY7tW8I2kDPGzygZjvNZ84AIodrE8VRwiojpGQOhRQ=\n", "pm9VwQp6qTQ=\n") + kwaiError.getMsg());
        DLEWAdEventTrack.OnShowFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, kwaiError.getMsg(), getNetworkName());
        DLEWInterstitialListener dLEWInterstitialListener = this.listener;
        if (dLEWInterstitialListener != null) {
            dLEWInterstitialListener.onFialed();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
        Load();
    }

    public void onAdClick() {
        if (this.m_throttle.CheckNetworkNameControlled(getNetworkName())) {
            this.m_throttle.AddDailyClickCount();
            if (this.m_lastRequestCode.equals(this.mRequestCode)) {
                this.m_multiClickCount++;
            } else {
                this.m_lastRequestCode = this.mRequestCode;
                this.m_multiClickCount = 1;
            }
        }
        XLog.d(StringFog.decrypt("CtmHKTN4GAYN3p4pcHUiRRjYmn0BIgoMX/6AKS8nGBEWw4c8JhQPJhPejTYvMS4TGtma\n", "f7fuXUpVa2U=\n"));
        DLEWAdEventTrack.OnClicked(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
    }

    public void onAdClose() {
        XLog.d(StringFog.decrypt("haZ4G5vUEGCCoWEb2NkqI5enZU+pjgJq0IF/G4eLEHeZvHgOjrgHQJynYgqGvBVmnrw=\n", "8MgRb+L5YwM=\n"));
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
            XLog.d(StringFog.decrypt("fR49eT9PlTd6GSR5fEI=\n", "CHBUDUZi5lQ=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("eQjvLz4yITveqiuWSGIoZLaZTutgCkVU1MBIrzA6HTfgmieeUg==\n", "WSXCD9iHoNI=\n"));
        } else {
            Load();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
    }

    public void onAdPlayComplete() {
    }

    public void sendBinWin() {
        KwaiInterstitialAd kwaiInterstitialAd = this.mKwaiInterstitialAd;
        if (kwaiInterstitialAd != null) {
            kwaiInterstitialAd.getBidController().sendBidWin();
        }
    }

    public void sendBinLose() {
        KwaiInterstitialAd kwaiInterstitialAd = this.mKwaiInterstitialAd;
        if (kwaiInterstitialAd != null) {
            kwaiInterstitialAd.getBidController().sendBidLose();
        }
    }

    public void onAdLoadSuccess(@Nullable String str, @NonNull KwaiInterstitialAd kwaiInterstitialAd) {
        DLEWAdEventTrack.OnLoaded(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        this.mKwaiInterstitialAd = kwaiInterstitialAd;
        if (kwaiInterstitialAd.getPrice().isEmpty()) {
            this.m_adInfo.revenue = 0.0d;
        } else {
            this.m_adInfo.revenue = Double.parseDouble(this.mKwaiInterstitialAd.getPrice()) / 1000.0d;
        }
        XLog.d(StringFog.decrypt("OWLxRV5WULc+ZehFHVtq9Ctj7BFsDEK9bEX2RUIJUKAlePFQSzpHhilt/EhiDUa6OCy4HApb\n", "TAyYMSd7I9Q=\n") + this.m_adInfo.revenue);
        this.m_adInfo.networkName = getNetworkName();
        this.mLoading = false;
        this.retryAttempt = 0;
    }
}