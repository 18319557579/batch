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
import com.dlew.other.ad.core.DLEWRewardVideoListener;
import com.dlew.other.ad.core.DLEWiRewardVideoAd;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import z.k.api.NetAdError;
import z.k.api.NetRewardVideoAd;
import z.k.api.NetRewardVideoAdListener;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/nm/DLEWNMRewardVideoAdapter.class */
public class DLEWNMRewardVideoAdapter implements NetRewardVideoAdListener, DLEWiRewardVideoAd {
    private NetRewardVideoAd rewardedAd;
    private DLEWRewardVideoListener listener;
    int retryAttempt;
    private DLEWAdThrottle m_throttle;
    private DLEWAdInfo m_adInfo;
    boolean isLoading;
    private String mCacheSceneType = StringFog.decrypt("t4P/cA==\n", "2faTHKnOdbs=\n");
    private String mRequestCode = UUID.randomUUID().toString();
    private String mAdType = StringFog.decrypt("wRqYQPeLNnk=\n", "s3/vIYXvUx0=\n");
    private boolean mAdRewarded = false;
    private boolean mAdClosed = false;
    private final String VideoTimesKey = StringFog.decrypt("GDb6CsNoQiM4FfYc4VhdJDsy+DDwbkYoLA==\n", "X1eXb4QHK00=\n");
    private String m_lastRequestCode = BuildConfig.VERSION_NAME;
    private int m_multiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/nm/DLEWNMRewardVideoAdapter$EvLveE1.class */
    public class EvLveE1 implements Runnable {
        public EvLveE1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DLEWNMRewardVideoAdapter.this.LoadRewardedAd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LoadRewardedAd() {
        if (this.isLoading) {
            return;
        }
        XLog.d(StringFog.decrypt("aMoi4tPwBxNvzTvikP0vHnD5EODDuREfQA==\n", "HaRLlqrddHA=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("RIpWRnvo/BDiQvwDL9WkUtAYkzJNSDSaSQ==\n", "aad2o8doGbc=\n"));
        String uuid = UUID.randomUUID().toString();
        this.mRequestCode = uuid;
        DLEWAdEventTrack.OnRequest(this.mAdType, uuid, this.mCacheSceneType, getNetworkName());
        this.isLoading = true;
        NetRewardVideoAd netRewardVideoAd = this.rewardedAd;
        if (netRewardVideoAd != null) {
            netRewardVideoAd.destroy();
            this.rewardedAd = null;
        }
        NetRewardVideoAd netRewardVideoAd2 = new NetRewardVideoAd(DLEWSDK.getActivity(), this.m_throttle.getConfig().videoID);
        this.rewardedAd = netRewardVideoAd2;
        netRewardVideoAd2.setMNRewardVideoAdListener(this);
        this.rewardedAd.load();
    }

    public void trackRevenue() {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra();
        dLEWAdInfoExtra.requestCode = this.mRequestCode;
        dLEWAdInfoExtra.Revenue = this.m_adInfo.revenue;
        dLEWAdInfoExtra.NetworkName = getNetworkName();
        dLEWAdInfoExtra.AdFormat = StringFog.decrypt("g0fZBDnl0FM=\n", "0QKORWuhlRc=\n");
        GGAnalysis.trackImmediately(StringFog.decrypt("chwMHF6EN4F6DgcqWIcYgXoLES1CjA==\n", "H310QzfpR/M=\n"), dLEWAdInfoExtra);
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
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("1KVhhZy0aAfooQ==\n", "mspBy/nAH2g=\n"), getNetworkName());
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
                return;
            }
            return;
        }
        XLog.d(StringFog.decrypt("xPyzDuZ8L5vD+6oOpXESncXfuwjpNDDW+OGbDP44MJnT/r9asnE1i/DkuxPzMD6U1LrzlSPL\n", "sZLaep9RXPg=\n") + isAvailable());
        if (isAvailable()) {
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
            this.rewardedAd.showAd(DLEWSDK.getActivity());
        } else {
            XLog.d(StringFog.decrypt("WwovMfGrJBxcDTYxsqYaHlZKDzbJ8DYWQgUkKe2mel9oBSo27Q==\n", "LmRGRYiGV38=\n"));
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
            }
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("eJpIOLUBPGtXlwQc\n", "NvVoecNgVQc=\n"), getNetworkName());
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        this.m_throttle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.m_adInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.videoID;
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("mVWMetRIHfKeUpV6l0U=\n", "7DvlDq1lbpE=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("ab7nxtOnrvHOHCN/pfenrqYvRgKNn8qexHZARt2vkv3wLC93vw==\n", "SZPK5jUSLxg=\n"));
        } else {
            LoadRewardedAd();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public boolean isAvailable() {
        return this.rewardedAd.isReady();
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getRequestCode() {
        return this.mRequestCode;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getNetworkName() {
        return StringFog.decrypt("F6JHGhkD9YYc\n", "ec8YdHh3nPA=\n");
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public double checkRevenue(String str) {
        if (this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName) && this.m_throttle.CheckThrottle(str)) {
            return -1.0d;
        }
        if (isAvailable()) {
            return this.m_adInfo.revenue;
        }
        XLog.d(StringFog.decrypt("2oyp8y8iQjndi7DzbC9qNMK/m/E/a1Q18g==\n", "r+LAh1YPMVo=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("U5rjvsOuif7fFisnmte0kpUgbnmZocC16w==\n", "c7fOniUyJRg=\n"));
        return this.m_throttle.CheckThrottle(str) ? -1.0d : 0.0d;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public DLEWAdInfo getAdInfo() {
        return this.m_adInfo;
    }

    public void onRewardedVideoAdLoaded() {
        this.isLoading = false;
        this.retryAttempt = 0;
        this.m_adInfo.revenue = this.rewardedAd.getEcpm() / 1000.0d;
        XLog.d(StringFog.decrypt("ohIKFSkhXlqlFRMVaixkGbATF0E+YQ1rsgsCEzRpSW++GAYOEXpMULsdAQg8ZVlAlBQCDzdpSXyh\nGQ0VcCEAGQ==\n", "13xjYVAMLTk=\n") + this.m_adInfo.revenue);
        this.m_adInfo.networkName = getNetworkName();
        DLEWAdEventTrack.OnLoaded(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
    }

    public void onRewardedVideoAdFailed(NetAdError netAdError) {
        this.isLoading = false;
        XLog.d(StringFog.decrypt("EHzdz5LkgQQXe8TP0em8AhFf1cmdrJ5HCnz136emkwMjc93Xjq3SXQ==\n", "ZRK0u+vJ8mc=\n") + this.m_adInfo.unitID);
        XLog.d(StringFog.decrypt("V4w3eqsLRYZQiy566AZZi2OGEmGzQnCES447avIc\n", "IuJeDtImNuU=\n") + this.m_adInfo.unitID);
        this.retryAttempt = this.retryAttempt + 1;
        new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, r2))));
        DLEWAdEventTrack.OnLoadFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("/70GKMkJeg==\n", "nNJiTfMpWjU=\n") + netAdError.getErrorCode() + StringFog.decrypt("LiRMMKx2xZ1ycEE6sS+X\n", "AgQoVd8Vt/Q=\n") + netAdError.getErrorMsg(), getNetworkName());
    }

    public void onRewardedVideoAdPlayStart() {
        XLog.d(StringFog.decrypt("XGN5Tp0drpxbZGBO3hCU305iZBqKXf2tTHpxSIBVualAaXVVpVSSj0xjdV6hRriRXS09Gg==\n", "KQ0QOuQw3f8=\n") + this + StringFog.decrypt("OBrU03R3\n", "GDr5/lRXGFk=\n") + this.mCacheSceneType);
        DLEWAdEventTrack.OnImpression(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        DLEWAd.addAdDdisplayCount(true);
        DLEWAd.addAdDdisplayDailyCount();
        if (this.m_throttle.CheckNetworkNameControlled(getNetworkName())) {
            this.m_throttle.AddDailyShowCount();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
        GGAnalysis.afTrack(StringFog.decrypt("GOvfWzMxoO8Y6+9BMDyn5x/q30JnK6rt\n", "a4OwLFVYzoY=\n"), null);
        trackRevenue();
    }

    public void onRewardedVideoAdPlayEnd() {
    }

    public void onRewardedVideoAdPlayFailed(NetAdError netAdError) {
        LoadRewardedAd();
        XLog.d(StringFog.decrypt("ULKmFu1Y4GlXtb8WrlXaKkKzu0L6GLNYQKuuEPAQ91xMuKoN1RHAYkqriQP9GfZuYKqqDOBZs2lK\nuKpCrlWz\n", "JdzPYpR1kwo=\n") + netAdError.getErrorCode() + StringFog.decrypt("hvKrU1qL9jvapqZZR8i+cg==\n", "qtLPNinohFI=\n") + netAdError.getErrorMsg());
        DLEWAdEventTrack.OnShowFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, netAdError.getErrorMsg(), getNetworkName());
        DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, netAdError.getErrorMsg(), getNetworkName());
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
        DLEWRewardVideoListener dLEWRewardVideoListener = this.listener;
        if (dLEWRewardVideoListener != null) {
            dLEWRewardVideoListener.onFialed();
        }
    }

    public void onRewardedVideoAdClosed() {
        DLEWRewardVideoListener dLEWRewardVideoListener = this.listener;
        if (dLEWRewardVideoListener != null) {
            dLEWRewardVideoListener.onReward();
        }
        this.mAdRewarded = false;
        this.mAdClosed = false;
        DLEWAdEventTrack.OnRewarded(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        this.m_throttle.AddDailyMultiClickCount(getNetworkName(), this.m_multiClickCount);
        this.m_multiClickCount = 0;
        DLEWAdInfo dLEWAdInfo = this.m_adInfo;
        dLEWAdInfo.revenue = 0.0d;
        dLEWAdInfo.networkName = BuildConfig.VERSION_NAME;
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("rWCiWcD9BvSqZ7tZg/A=\n", "2A7LLbnQdZc=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("00OYwQNB4NB04Vx4dRHpjxzSOQVdeYS/fos/QQ1J3NxK0VBwbw==\n", "82614eX0YTk=\n"));
        } else {
            LoadRewardedAd();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
    }

    public void onRewardedVideoAdPlayClicked() {
        if (this.m_throttle.CheckNetworkNameControlled(getNetworkName())) {
            this.m_throttle.AddDailyClickCount();
            if (this.m_lastRequestCode.equals(this.mRequestCode)) {
                this.m_multiClickCount++;
            } else {
                this.m_lastRequestCode = this.mRequestCode;
                this.m_multiClickCount = 1;
            }
        }
        XLog.d(StringFog.decrypt("Ugdk+2AUqBZVAH37IxmSVUAGea9LXKwUVQ1o609QvxBIKGnMdVC4HkINSPl8V68=\n", "J2kNjxk523U=\n"));
        DLEWAdEventTrack.OnClicked(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
    }

    public void onReward() {
        XLog.d(StringFog.decrypt("7kj2aMjisCLpT+9oi++KYfxJ6zzfouMT/lH+btWqpxfyQvpz8KuRJOxH7XjUq4Y3/kjr\n", "myafHLHPw0E=\n"));
        this.mAdRewarded = true;
        GGAnalysis.afTrack(StringFog.decrypt("ZZ17E/WhBYZ+lmUB7w==\n", "F/gMcofFWuA=\n"), null);
    }
}