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
import com.dlew.other.ad.core.DLEWRewardVideoListener;
import com.dlew.other.ad.core.DLEWiRewardVideoAd;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import sg.bigo.ads.api.AdError;
import sg.bigo.ads.api.AdLoadListener;
import sg.bigo.ads.api.RewardAdInteractionListener;
import sg.bigo.ads.api.RewardVideoAd;
import sg.bigo.ads.api.RewardVideoAdLoader;
import sg.bigo.ads.api.RewardVideoAdRequest;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/bigo/DLEWBigoRewardVideoAdapter.class */
public class DLEWBigoRewardVideoAdapter implements AdLoadListener<RewardVideoAd>, RewardAdInteractionListener, DLEWiRewardVideoAd {
    private RewardVideoAd rewardedAd;
    private DLEWRewardVideoListener listener;
    int retryAttempt;
    private DLEWAdThrottle m_throttle;
    private DLEWAdInfo m_adInfo;
    boolean isLoading;
    Activity mContext;
    private String mCacheSceneType = StringFog.decrypt("I5xzOQ==\n", "TekfVYc1/yw=\n");
    private String mRequestCode = UUID.randomUUID().toString();
    private String mAdType = StringFog.decrypt("J7axYUD59eA=\n", "VdPGADKdkIQ=\n");
    private boolean mAdRewarded = false;
    private boolean mAdClosed = false;
    private final String VideoTimesKey = StringFog.decrypt("KMH+NIfkKyYI4vIipdQ0IQvF/A604i8tHA==\n", "b6CTUcCLQkg=\n");
    private String m_lastRequestCode = BuildConfig.VERSION_NAME;
    private int m_multiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/bigo/DLEWBigoRewardVideoAdapter$EvLveE1.class */
    public class EvLveE1 implements Runnable {
        public EvLveE1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DLEWBigoRewardVideoAdapter.this.LoadRewardedAd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LoadRewardedAd() {
        if (this.isLoading) {
            return;
        }
        XLog.d(StringFog.decrypt("EdhI0D5IAb0W31HQfUUpvA3RTvkcExu6Adl8\n", "ZLYhpEdlct4=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("7/p0oF2ZUVRJMt7lCaQJG2VRvedw/A1MJ0beZcw0lA==\n", "wtdUReEZtPM=\n"));
        String uuid = UUID.randomUUID().toString();
        this.mRequestCode = uuid;
        DLEWAdEventTrack.OnRequest(this.mAdType, uuid, this.mCacheSceneType, getNetworkName());
        this.isLoading = true;
        RewardVideoAd rewardVideoAd = this.rewardedAd;
        if (rewardVideoAd != null) {
            rewardVideoAd.destroy();
            this.rewardedAd = null;
        }
        new RewardVideoAdLoader.Builder().withAdLoadListener(this).build().loadAd(new RewardVideoAdRequest.Builder().withSlotId(this.m_throttle.getConfig().videoID).build());
    }

    public void trackRevenue() {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra();
        dLEWAdInfoExtra.requestCode = this.mRequestCode;
        dLEWAdInfoExtra.Revenue = this.m_adInfo.revenue;
        dLEWAdInfoExtra.NetworkName = getNetworkName();
        dLEWAdInfoExtra.AdFormat = StringFog.decrypt("0Ieps7kyXz0=\n", "gsL+8ut2Gnk=\n");
        GGAnalysis.trackImmediately(StringFog.decrypt("iq+WSvIyVVGCvZ189DF6UYK4i3vuOg==\n", "587uFZtfJSM=\n"), dLEWAdInfoExtra);
        DLEWRoas.SendMaxRevenueToFirebase(dLEWAdInfoExtra);
        DLEWRoas.CheckTaiChiTcpaAdRevenueEvent(this.m_adInfo.revenue);
        DLEWRoas.CheckTaichiTroasFirebaseAdRevenueEvent(this.m_adInfo.revenue);
        DLEWRoas.trackKwaiValueToAF(this.m_adInfo.revenue);
        DLEWRoas.sendRevenueToAF(dLEWAdInfoExtra);
    }

    @Override // com.dlew.other.ad.core.DLEWiRewardVideoAd
    public void show(String str, DLEWRewardVideoListener dLEWRewardVideoListener) {
        this.mCacheSceneType = str;
        this.listener = dLEWRewardVideoListener;
        if (!NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("08nxJuDwmSXvzQ==\n", "nabRaIWE7ko=\n"), getNetworkName());
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
                return;
            }
            return;
        }
        XLog.d(StringFog.decrypt("I5lIuj8Dx2MknlG6fA72aTGYD4c1b8JhP5tArCpLlC12nlKPME/dbDeVTatuB1u8zA==\n", "VvchzkYutAA=\n") + isAvailable());
        if (!isAvailable()) {
            XLog.d(StringFog.decrypt("E5qS6EV9D2UUnYvoBnAxZx7asu99Jh1vCpWZ8FlwUSYglZfvWQ==\n", "ZvT7nDxQfAY=\n"));
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
            }
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("k2ojNAiJ1gO8Z28Q\n", "3QUDdX7ov28=\n"), getNetworkName());
            return;
        }
        if (this.m_throttle.getConfig().showLoading <= 0 || !this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName)) {
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
            this.rewardedAd.show();
        } else {
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
            this.rewardedAd.show();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        this.mContext = activity;
        this.m_throttle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.m_adInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.videoID;
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("yPiOVyPleXbP/5dXYOg=\n", "vZbnI1rIChU=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("MASULCjpTk2XplCVXrlHEv+VNeh20SoincwzrCbhckGpllydRA==\n", "ECm5DM5cz6Q=\n"));
        } else {
            LoadRewardedAd();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public boolean isAvailable() {
        RewardVideoAd rewardVideoAd = this.rewardedAd;
        return (rewardVideoAd == null || rewardVideoAd.isExpired()) ? false : true;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getRequestCode() {
        return this.mRequestCode;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getNetworkName() {
        return StringFog.decrypt("y9KJ6GcjmHXAzYs=\n", "qbvuhzhN+QE=\n");
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public double checkRevenue(String str) {
        if (this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName) && this.m_throttle.CheckThrottle(str)) {
            return -1.0d;
        }
        if (isAvailable()) {
            return this.m_adInfo.revenue;
        }
        XLog.d(StringFog.decrypt("NTry0/0VqLMyPevTvhiAsikz9PrfTrK0JTvG\n", "QFSbp4Q429A=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("rlfKBSBzik0i2wKceQq3IWjtR8J6fMMGFg==\n", "jnrnJcbvJqs=\n"));
        return this.m_throttle.CheckThrottle(str) ? -1.0d : 0.0d;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public DLEWAdInfo getAdInfo() {
        return this.m_adInfo;
    }

    public void onError(@NonNull AdError adError) {
        this.isLoading = false;
        XLog.d(StringFog.decrypt("85mSsMNj/aj0nouwgG7MouGY26vUD+qH6Zafgtsn4q7i18E=\n", "hvf7xLpOjss=\n") + this.m_adInfo.unitID);
        XLog.d(StringFog.decrypt("zpbZKbX146/JkcAp9vj/ovqc/DKtvNat0pTVOezi\n", "u/iwXczYkMw=\n") + this.m_adInfo.unitID);
        this.retryAttempt = this.retryAttempt + 1;
        new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, r2))));
        DLEWAdEventTrack.OnLoadFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("T5SM9bRHeA==\n", "LPvokI5nWAk=\n") + adError.getCode() + StringFog.decrypt("d/TSE4FVquYroN8ZnAz4\n", "W9S2dvI22I8=\n") + adError.getMessage(), getNetworkName());
    }

    public void onAdRewarded() {
        XLog.d(StringFog.decrypt("7c6+Tr6RNJDqyadO/ZwO0//PoxqF1SCcuPKyTabOI5b89r5eotMGl8rFoFu12CKX3dayVLM=\n", "mKDXOse8R/M=\n"));
        this.mAdRewarded = true;
        GGAnalysis.afTrack(StringFog.decrypt("ovzxuAElhui59++qGw==\n", "0JmG2XNB2Y4=\n"), null);
    }

    public void onAdError(@NonNull AdError adError) {
        LoadRewardedAd();
        XLog.d(StringFog.decrypt("z5sJXt/sNq3InBBenOEM7t2aFArkqCKhmqcFXcezIaveowlOw64EqumdD13goCyi35ElXMOvMeKa\nlg9Ow+F/7po=\n", "uvVgKqbBRc4=\n") + adError.getCode() + StringFog.decrypt("yvj38DSwquGWrPr6KfPiqA==\n", "5tiTlUfT2Ig=\n") + adError.getMessage());
        DLEWAdEventTrack.OnShowFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, adError.getMessage(), getNetworkName());
        DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, adError.getMessage(), getNetworkName());
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
        DLEWRewardVideoListener dLEWRewardVideoListener = this.listener;
        if (dLEWRewardVideoListener != null) {
            dLEWRewardVideoListener.onFialed();
        }
    }

    public void onAdImpression() {
        XLog.d(StringFog.decrypt("ehTydR+X9TJ9E+t1XJrPcWgV7yEk0+E+Lyj+dgfI4jRrLPJlA9XHNUAK/m8D3sMnahTvIUua\n", "D3qbAWa6hlE=\n") + this + StringFog.decrypt("PgLre0Id\n", "HiLGVmI9OSY=\n") + this.mCacheSceneType);
        DLEWAdEventTrack.OnImpression(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        DLEWAd.addAdDdisplayCount(true);
        DLEWAd.addAdDdisplayDailyCount();
        if (this.m_throttle.CheckNetworkNameControlled(getNetworkName())) {
            this.m_throttle.AddDailyShowCount();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
        GGAnalysis.afTrack(StringFog.decrypt("DrcuJ6aXsSYOtx49pZq2Lgm2Lj7yjbsk\n", "fd9BUMD+308=\n"), null);
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
        XLog.d(StringFog.decrypt("1m3TlV/4o/7RasqVHPWZvcRszsF0sKf80WffhXC8tPjMQt6iSryz9sZn/5dDu6Q=\n", "owO64SbV0J0=\n"));
        DLEWAdEventTrack.OnClicked(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
    }

    public void onAdOpened() {
    }

    public void onAdClosed() {
        this.mAdRewarded = false;
        this.mAdClosed = false;
        DLEWRewardVideoListener dLEWRewardVideoListener = this.listener;
        if (dLEWRewardVideoListener != null) {
            dLEWRewardVideoListener.onReward();
        }
        DLEWAdEventTrack.OnRewarded(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        this.m_throttle.AddDailyMultiClickCount(getNetworkName(), this.m_multiClickCount);
        this.m_multiClickCount = 0;
        DLEWAdInfo dLEWAdInfo = this.m_adInfo;
        dLEWAdInfo.revenue = 0.0d;
        dLEWAdInfo.networkName = BuildConfig.VERSION_NAME;
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("CiowTrskizQNLSlO+Ck=\n", "f0RZOsIJ+Fc=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("wksOhAgarHhl6co9fkqlJw3ar0BWIsgXb4OpBAYSkHRb2cY1ZA==\n", "4mYjpO6vLZE=\n"));
        } else {
            LoadRewardedAd();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
    }

    public void onAdLoaded(@NonNull RewardVideoAd rewardVideoAd) {
        this.rewardedAd = rewardVideoAd;
        rewardVideoAd.setAdInteractionListener(this);
        this.isLoading = false;
        this.retryAttempt = 0;
        if (this.rewardedAd.getBid() != null) {
            this.m_adInfo.revenue = this.rewardedAd.getBid().getPrice() / 1000.0d;
        } else {
            this.m_adInfo.revenue = 0.0d;
        }
        XLog.d(StringFog.decrypt("9oFsA6Gwb7fxhnUD4r1V9OSAcVea9Hu7o71gALnveLHnuWwTvfJdouKGaRa69HC995ZGH7nze7Hn\nqnMStuk8+a7P\n", "g+8Fd9idHNQ=\n") + this.m_adInfo.revenue);
        this.m_adInfo.networkName = getNetworkName();
        DLEWAdEventTrack.OnLoaded(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
    }
}