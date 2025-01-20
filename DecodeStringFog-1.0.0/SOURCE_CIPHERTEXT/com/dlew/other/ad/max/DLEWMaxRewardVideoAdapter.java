package com.dlew.other.ad.max;

import android.app.Activity;
import android.os.Handler;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxRewardedAd;
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

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/max/DLEWMaxRewardVideoAdapter.class */
public class DLEWMaxRewardVideoAdapter implements MaxRewardedAdListener, DLEWiRewardVideoAd, MaxAdRevenueListener {
    private MaxRewardedAd rewardedAd;
    private DLEWRewardVideoListener listener;
    int retryAttempt;
    private DLEWAdThrottle m_throttle;
    private DLEWAdInfo m_adInfo;
    private String mCacheSceneType = StringFog.decrypt("90goxw==\n", "mT1Eq1x62tY=\n");
    private String mRequestCode = UUID.randomUUID().toString();
    private String mAdType = StringFog.decrypt("pF+3s5rP4KU=\n", "1jrA0uirhcE=\n");
    private boolean mAdRewarded = false;
    private boolean mAdClosed = false;
    private final String VideoTimesKey = StringFog.decrypt("DWE5NI+HljMwcAwClIuB\n", "Xy94a/nu8lY=\n");
    private String m_lastRequestCode = BuildConfig.VERSION_NAME;
    private int m_multiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/max/DLEWMaxRewardVideoAdapter$EvLveE1.class */
    public class EvLveE1 implements Runnable {
        public EvLveE1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DLEWMaxRewardVideoAdapter.this.LoadRewardedAd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LoadRewardedAd() {
        XLog.d(StringFog.decrypt("AJGvlCNU/uUHlraUYFnW6xSHm7ssEOnjGqI=\n", "df/G4Fp5jYY=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("6+BqPwWY3f5NKMB6UaWFvH9yr0sz\n", "xs1K2rkYOFk=\n"));
        String uuid = UUID.randomUUID().toString();
        this.mRequestCode = uuid;
        DLEWAdEventTrack.OnRequest(this.mAdType, uuid, this.mCacheSceneType, StringFog.decrypt("LRLe\n", "QHOmkZnZiRQ=\n"));
        this.rewardedAd.loadAd();
    }

    public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("Wfhw5Ewpgite/2nkDyS4aEv5bbBYZYlofvNu8UdglCx6/331WkWVGknheOJRYZUNWvN35A==\n", "LJYZkDUE8Ug=\n"));
            this.mAdRewarded = true;
            GGAnalysis.afTrack(StringFog.decrypt("KUCjOC74Eu8yS70qNA==\n", "WyXUWVycTYk=\n"), null);
        }
    }

    public void onAdLoaded(MaxAd maxAd) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("afWlGo0sUnJu8rwaziFoMXv0uE6ZYFkxTv67D4ZlRHVK8qgLm0BXcHX3rQydbUhlZdikD5pmRHVZ\n7akAgCEMPDw=\n", "HJvMbvQBIRE=\n") + maxAd.getAdUnitId() + StringFog.decrypt("o2U=\n", "/DqEMBbh9p8=\n") + maxAd.getRevenue());
            this.retryAttempt = 0;
            this.m_adInfo.revenue = maxAd.getRevenue();
            this.m_adInfo.networkName = maxAd.getNetworkName();
            DLEWAdEventTrack.OnLoaded(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
        }
    }

    public void onAdDisplayed(MaxAd maxAd) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("G4I0Z5JvhVYchS1n0WK/FQmDKTOGI44VPIkqcpkmk1E4hTl2hAOSeh6JM3aPB4BQAJh9Pss=\n", "buxdE+tC9jU=\n") + this + StringFog.decrypt("sPfDxW3I\n", "kNfu6E3o7YU=\n") + this.mCacheSceneType);
            DLEWAdEventTrack.OnImpression(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
            DLEWAd.addAdDdisplayCount(true);
            DLEWAd.addAdDdisplayDailyCount();
            if (this.m_throttle.CheckNetworkNameControlled(maxAd.getNetworkName())) {
                this.m_throttle.AddDailyShowCount();
            }
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
            GGAnalysis.afTrack(StringFog.decrypt("keOYwX3WjpCR46jbftuJmJbimNgpzISS\n", "4ov3thu/4Pk=\n"), null);
        }
    }

    public void onAdHidden(MaxAd maxAd) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            DLEWRewardVideoListener dLEWRewardVideoListener = this.listener;
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onReward();
            }
            this.mAdRewarded = false;
            this.mAdClosed = false;
            DLEWAdEventTrack.OnRewarded(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
            this.m_throttle.AddDailyMultiClickCount(maxAd.getNetworkName(), this.m_multiClickCount);
            this.m_multiClickCount = 0;
            DLEWAdInfo dLEWAdInfo = this.m_adInfo;
            dLEWAdInfo.revenue = 0.0d;
            dLEWAdInfo.networkName = BuildConfig.VERSION_NAME;
            if (this.m_throttle.CheckThrottle(null)) {
                XLog.d(StringFog.decrypt("7q2zQQeOJWjpqqpBRIM=\n", "m8PaNX6jVgs=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("qnhaMMc8aGAN2p6JsWxhP2Xp+/SZBAwPB7D9sMk0VGwz6pKBqw==\n", "ilV3ECGJ6Yk=\n"));
            } else {
                LoadRewardedAd();
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
            XLog.d(StringFog.decrypt("WmVpx0HbYMNdYnDHAtZagEhkdJNVl2uAfW530kqSdsR5YmTWV7d340NiY9hdklbWSmV0nxiYcs1K\nKz2T\n", "LwsAszj2E6A=\n") + maxAd.getAdUnitId());
            DLEWAdEventTrack.OnClicked(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
        }
    }

    public void onAdLoadFailed(String str, MaxError maxError) {
        XLog.d(StringFog.decrypt("JTGtcZzIjAMiNrRx38WQDhE7iGqEgbkBOTOhYcXf\n", "UF/EBeXl/2A=\n") + str + StringFog.decrypt("Lz0=\n", "AhBHIv9hXw0=\n") + this.m_adInfo.unitID);
        if (str.equals(this.m_adInfo.unitID)) {
            XLog.d(StringFog.decrypt("wrVdOHuVI+LFskQ4OJg/7/a/eCNj3Bbg3rdRKCKC\n", "t9s0TAK4UIE=\n") + this.m_adInfo.unitID);
            this.retryAttempt = this.retryAttempt + 1;
            new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, r2))));
            DLEWAdEventTrack.OnLoadFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("6ZckcW0N0w==\n", "ivhAFFct8xc=\n") + maxError.getCode() + StringFog.decrypt("itLsD7JUU+fWhuEFrw0B\n", "pvKIasE3IY4=\n") + maxError.getMessage(), StringFog.decrypt("sbQg\n", "3NVYCOyUtqU=\n"));
        }
    }

    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
        if (maxAd.getAdUnitId().equals(this.m_adInfo.unitID)) {
            LoadRewardedAd();
            XLog.d(StringFog.decrypt("p76etLDhQ6uguYe08+x56LW/g+CkrUjogLWAobuoVayEuZOlpo1Um7q/gIaopVyttpWBpae4HOix\nv5Ol6fYQ6A==\n", "0tD3wMnMMMg=\n") + maxError.getCode() + StringFog.decrypt("wI9q763Kk7Cc22flsInb+Q==\n", "7K8Oit6p4dk=\n") + maxError.getMessage());
            DLEWAdEventTrack.OnShowFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxError.getMessage(), maxAd.getNetworkName());
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxError.getMessage(), getNetworkName());
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
            DLEWRewardVideoListener dLEWRewardVideoListener = this.listener;
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
            }
        }
    }

    public void onAdRevenuePaid(MaxAd maxAd) {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra(maxAd, this.mRequestCode);
        GGAnalysis.trackImmediately(StringFog.decrypt("1Ybw4MdIshTdlPvWwUudFN2R7dHbQA==\n", "uOeIv64lwmY=\n"), dLEWAdInfoExtra);
        DLEWRoas.SendMaxRevenueToFirebase(dLEWAdInfoExtra);
        DLEWRoas.CheckTaiChiTcpaAdRevenueEvent(maxAd.getRevenue());
        DLEWRoas.CheckTaichiTroasFirebaseAdRevenueEvent(maxAd.getRevenue());
        DLEWRoas.trackKwaiValueToAF(maxAd.getRevenue());
        DLEWRoas.sendRevenueToAF(dLEWAdInfoExtra);
    }

    @Override // com.dlew.other.ad.core.DLEWiRewardVideoAd
    public void show(String str, DLEWRewardVideoListener dLEWRewardVideoListener) {
        this.mCacheSceneType = str;
        this.listener = dLEWRewardVideoListener;
        if (!NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("T60UNeMBybZzqQ==\n", "AcI0e4Z1vtk=\n"), getNetworkName());
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
                return;
            }
            return;
        }
        XLog.d(StringFog.decrypt("y1JhwJf3DVDMVXjA1PozUsYSQcevrB9a0l1q2Iv6UxPXT0nCj7MSUtxQbZzHNcKp\n", "vjwItO7afjM=\n") + isAvailable());
        if (!isAvailable()) {
            XLog.d(StringFog.decrypt("OGVAeaXYDSM/Yll55tUzITUlYH6dgx8pIWpLYbnVU2ALakV+uQ==\n", "TQspDdz1fkA=\n"));
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
            }
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("nJMfcpOaNJqznlNW\n", "0vw/M+X7XfY=\n"), getNetworkName());
            return;
        }
        if (this.m_throttle.getConfig().showLoading <= 0 || !this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName)) {
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
            this.rewardedAd.showAd();
        } else {
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
            this.rewardedAd.showAd();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        MaxRewardedAd maxRewardedAd = MaxRewardedAd.getInstance(dLEWLimitConfig.videoID, activity);
        this.rewardedAd = maxRewardedAd;
        maxRewardedAd.setListener(this);
        this.rewardedAd.setRevenueListener(this);
        this.m_throttle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.m_adInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.videoID;
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("4ufPbZTtSo/l4NZt1+A=\n", "l4mmGe3AOew=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("S44+eYEggSPsLPrA93CIfIQfn73fGOVM5kaZ+Y8ovS/SHPbI7Q==\n", "a6MTWWeVAMo=\n"));
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
        XLog.d(StringFog.decrypt("tsdIiL/r+++xwFGI/OY=\n", "w6kh/MbGiIw=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("YbdRru4zBjLtO5k3t0o7XqcN3Gm0PE952Q==\n", "QZp8jgivqtQ=\n"));
        return this.m_throttle.CheckThrottle(str) ? -1.0d : 0.0d;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public DLEWAdInfo getAdInfo() {
        return this.m_adInfo;
    }
}