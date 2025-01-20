package com.dlew.other.ad.bigo;

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
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import sg.bigo.ads.api.AdError;
import sg.bigo.ads.api.AdInteractionListener;
import sg.bigo.ads.api.AdLoadListener;
import sg.bigo.ads.api.InterstitialAd;
import sg.bigo.ads.api.InterstitialAdLoader;
import sg.bigo.ads.api.InterstitialAdRequest;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/bigo/DLEWBigoIntersitialAdapter.class */
public class DLEWBigoIntersitialAdapter implements AdLoadListener<InterstitialAd>, AdInteractionListener, DLEWiInterstitialAd {
    private InterstitialAd interstitialAd;
    private DLEWInterstitialListener listener;
    private boolean mLoading;
    int retryAttempt;
    private DLEWAdThrottle m_throttle;
    private DLEWAdInfo m_adInfo;
    Activity mContext;
    boolean isLoading;
    private String mCacheSceneType = StringFog.decrypt("uue8XQ==\n", "1JLQMXpoa30=\n");
    private String mRequestCode = UUID.randomUUID().toString();
    private String mAdType = StringFog.decrypt("LXu6\n", "RBXJ7QqJxWo=\n");
    boolean showAndroidActivity = false;
    private String m_lastRequestCode = BuildConfig.VERSION_NAME;
    private int m_multiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/bigo/DLEWBigoIntersitialAdapter$EvLveE1.class */
    public class EvLveE1 implements Runnable {
        public EvLveE1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DLEWBigoIntersitialAdapter.this.Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getRequestCode() {
        return this.mRequestCode;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getNetworkName() {
        return StringFog.decrypt("DM8GI3nbWkYH0AQ=\n", "bqZhTCa1OzI=\n");
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public double checkRevenue(String str) {
        if (this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName) && this.m_throttle.CheckThrottle(str)) {
            return -1.0d;
        }
        if (isAvailable()) {
            return this.m_adInfo.revenue;
        }
        XLog.d(StringFog.decrypt("UXr94nKjECtWfeTiMa44Kk1z+8tQ5w07eQ==\n", "JBSUlguOY0g=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("TTdNJ9P5GUfBu4W+ioAkK4uNwOCJ9lAM9Q==\n", "bRpgBzVltaE=\n"));
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
        XLog.d(StringFog.decrypt("aXe96+LpDL9ucKTroZ8dtXt2icTyqgyBPA==\n", "HBnUn5vEf9w=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("y97qfuhZTF9MeCLUrQ1xBw5KeLucbw==\n", "6/PHXg3lzLo=\n"));
        String uuid = UUID.randomUUID().toString();
        this.mRequestCode = uuid;
        DLEWAdEventTrack.OnRequest(this.mAdType, uuid, this.mCacheSceneType, getNetworkName());
        this.mLoading = true;
        InterstitialAd interstitialAd = this.interstitialAd;
        if (interstitialAd != null) {
            interstitialAd.destroy();
            this.interstitialAd = null;
        }
        new InterstitialAdLoader.Builder().withAdLoadListener(this).build().loadAd(new InterstitialAdRequest.Builder().withSlotId(this.m_throttle.getConfig().insID).build());
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        this.m_throttle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.m_adInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.insID;
        this.mContext = activity;
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("7CgjSAHnQnrrLzpIQuo=\n", "mUZKPHjKMRk=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("5rBntQ0wm7FBEqMMe2CS7ikhxnFTCP/eS3jANQM4p71/Iq8EYQ==\n", "xp1KleuFGlg=\n"));
        } else {
            Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public boolean isAvailable() {
        InterstitialAd interstitialAd = this.interstitialAd;
        return (interstitialAd == null || interstitialAd.isExpired()) ? false : true;
    }

    @Override // com.dlew.other.ad.core.DLEWiInterstitialAd
    public void show(String str, DLEWInterstitialListener dLEWInterstitialListener) {
        this.mCacheSceneType = str;
        this.listener = dLEWInterstitialListener;
        if (!NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("uj5F+/wPRdCGOg==\n", "9FFltZl7Mr8=\n"), getNetworkName());
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
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("h92c6NRjiFWo0NDM\n", "ybK8qaIC4Tk=\n"), getNetworkName());
            return;
        }
        if (this.m_throttle.getConfig().showLoading <= 0 || !this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName)) {
            this.interstitialAd.show();
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        } else {
            this.interstitialAd.show();
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        }
    }

    public void trackRevenue() {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra();
        dLEWAdInfoExtra.requestCode = this.mRequestCode;
        dLEWAdInfoExtra.Revenue = this.m_adInfo.revenue;
        dLEWAdInfoExtra.NetworkName = getNetworkName();
        dLEWAdInfoExtra.AdFormat = StringFog.decrypt("6zZ7mkY=\n", "ongv3xT0ASM=\n");
        GGAnalysis.trackImmediately(StringFog.decrypt("wPe6IJBgzPjI5bEWlmPj+MjgpxGMaA==\n", "rZbCf/kNvIo=\n"), dLEWAdInfoExtra);
        DLEWRoas.SendMaxRevenueToFirebase(dLEWAdInfoExtra);
        DLEWRoas.CheckTaiChiTcpaAdRevenueEvent(this.m_adInfo.revenue);
        DLEWRoas.CheckTaichiTroasFirebaseAdRevenueEvent(this.m_adInfo.revenue);
        DLEWRoas.trackKwaiValueToAF(this.m_adInfo.revenue);
        DLEWRoas.sendRevenueToAF(dLEWAdInfoExtra);
    }

    public void onError(@NonNull AdError adError) {
        XLog.d(StringFog.decrypt("VmTRtb1WMGNRY8i1/lsKIERlzOGGEiRvA0PWtaEJMHRKftGgqDonTExr3IelEi9lR0/OpKoPbyBA\nZdyk/ls=\n", "Iwq4wcR7QwA=\n") + adError.getCode() + StringFog.decrypt("5/6BkSw4lZi7qoybMXvd0Q==\n", "y97l9F9b5/E=\n") + adError.getMessage());
        DLEWAdEventTrack.OnLoadFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("yu/ZbGUZRw==\n", "qYC9CV85Z5Y=\n") + adError.getCode() + StringFog.decrypt("tsu/yac/VR/qn7LDumYH\n", "muvbrNRcJ3Y=\n") + adError.getMessage(), getNetworkName());
        this.mLoading = false;
        this.retryAttempt = this.retryAttempt + 1;
        new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, r1))));
    }

    public void onAdError(@NonNull AdError adError) {
        XLog.d(StringFog.decrypt("7aN/1UVvBSLqpGbVBmI/Yf+iYoF+KxEuuIR41VkwBTXxuX/AUAMSEvCiYeddKxok/IhgxFI2WmE=\n", "mM0WoTxCdkE=\n") + adError.getMessage());
        DLEWAdEventTrack.OnShowFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, adError.getMessage(), getNetworkName());
        DLEWInterstitialListener dLEWInterstitialListener = this.listener;
        if (dLEWInterstitialListener != null) {
            dLEWInterstitialListener.onFialed();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
        Load();
    }

    public void onAdImpression() {
        XLog.d(StringFog.decrypt("tLHNK9xgAJazttQrn2061aaw0H/nJBSa4ZbKK8A/AIGoq80+yQwXpqmw0wzQLhCQpLvBO+A7Fpu1\n/4lyhQ==\n", "wd+kX6VNc/U=\n") + this + StringFog.decrypt("zrpB5g==\n", "7pdsxn6mb04=\n") + this.mCacheSceneType);
        DLEWAdEventTrack.OnImpression(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        if (this.m_throttle.CheckNetworkNameControlled(getNetworkName())) {
            this.m_throttle.AddDailyShowCount();
        }
        DLEWAd.addAdDdisplayCount(false);
        DLEWAd.addAdDdisplayDailyCount();
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
        GGAnalysis.afTrack(StringFog.decrypt("3iJ1u+OGq2jeIkWh4IusYNkjdaK3nKFq\n", "rUoazIXvxQE=\n"), null);
        trackRevenue();
    }

    public void onAdClicked() {
        if (this.m_throttle.CheckNetworkNameControlled(getNetworkName())) {
            this.m_throttle.AddDailyClickCount();
            if (this.m_lastRequestCode.equals(this.mRequestCode)) {
                this.m_multiClickCount++;
            } else {
                this.m_lastRequestCode = this.mRequestCode;
                this.m_multiClickCount = 1;
            }
        }
        XLog.d(StringFog.decrypt("c/IoecUCZFF09TF5hg9eEmHzNS3+RnBdJtUvedldZEZv6Chs0G5zcWr1ImbZS1JEY/I1\n", "BpxBDbwvFzI=\n"));
        DLEWAdEventTrack.OnClicked(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
    }

    public void onAdOpened() {
    }

    public void onAdClosed() {
        XLog.d(StringFog.decrypt("+KsF7+zn9hT/rBzvr+rMV+qqGLvcpPES/7YY8uGj5BvMoS/3+rngE8izCfXh\n", "jcVsm5XKhXc=\n"));
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
            XLog.d(StringFog.decrypt("rvEQw9joLRCp9gnDm+U=\n", "2595t6HFXnM=\n") + this.m_throttle.getConfig().insID + StringFog.decrypt("kcsFoI68EYU2acEZ+OwY2l5apGTQhHXqPAOiIIC0LYkIWc0R4g==\n", "seYogGgJkGw=\n"));
        } else {
            Load();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
    }

    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
        DLEWAdEventTrack.OnLoaded(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        this.interstitialAd = interstitialAd;
        interstitialAd.setAdInteractionListener(this);
        if (this.interstitialAd.getBid() != null) {
            this.m_adInfo.revenue = this.interstitialAd.getBid().getPrice() / 1000.0d;
        } else {
            this.m_adInfo.revenue = 0.0d;
        }
        XLog.d(StringFog.decrypt("IRaGe2cR1bgmEZ97JBzv+zMXmy9cVcG0dDGBe3tO1a89DIZucn3CiTEZi3ZbSsO1IFjPIjMc\n", "VHjvDx48pts=\n") + this.m_adInfo.revenue);
        this.m_adInfo.networkName = getNetworkName();
        this.mLoading = false;
        this.retryAttempt = 0;
    }
}