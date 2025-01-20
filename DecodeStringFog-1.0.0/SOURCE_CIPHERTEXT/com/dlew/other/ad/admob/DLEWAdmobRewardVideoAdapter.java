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
import com.dlew.other.ad.core.DLEWRewardVideoListener;
import com.dlew.other.ad.core.DLEWiRewardVideoAd;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.gson.Gson;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobRewardVideoAdapter.class */
public class DLEWAdmobRewardVideoAdapter implements DLEWiRewardVideoAd {
    private RewardedAd mRewardAd;
    private DLEWRewardVideoListener listener;
    private int retryAttempt;
    private DLEWAdThrottle mThrottle;
    private DLEWAdInfo mAdInfo;
    boolean isLoading;
    private String mCacheSceneType = StringFog.decrypt("QcHhKw==\n", "L7SNR1mQWyE=\n");
    private String mRequestCode = UUID.randomUUID().toString();
    private final String mAdType = StringFog.decrypt("pGfLhYIsovA=\n", "1gK85PBIx5Q=\n");
    private final FullScreenContentCallback mFullScreenContentCallback = new EvLveE1();
    private OnPaidEventListener mOnPaidEventListener = new v1vLvLE();
    private RewardedAdLoadCallback mRewardedAdLoadCallback = new evleeEelE();
    private OnUserEarnedRewardListener mOnUserEarnedRewardListener = new EeleE();
    private String mLastRequestCode = BuildConfig.VERSION_NAME;
    private int mMultiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobRewardVideoAdapter$EeleE.class */
    public class EeleE implements OnUserEarnedRewardListener {
        public EeleE() {
        }

        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
            XLog.d(StringFog.decrypt("8pGaWT4jtQX1loNZfS6PRuCQhw0GaqsJ5d+hSDBvtALim6VEI2upJ+OtllomfKID47qFSCl6\n", "h//zLUcOxmY=\n"));
            GGAnalysis.afTrack(StringFog.decrypt("mlsL/5YsEy2BUBXtjA==\n", "6D58nuRITEs=\n"), null);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobRewardVideoAdapter$EvLveE1.class */
    public class EvLveE1 extends FullScreenContentCallback {
        public EvLveE1() {
        }

        public void onAdClicked() {
            if (DLEWAdmobRewardVideoAdapter.this.mThrottle.CheckNetworkNameControlled(DLEWAdmobRewardVideoAdapter.this.getNetworkName())) {
                DLEWAdmobRewardVideoAdapter.this.mThrottle.AddDailyClickCount();
                if (DLEWAdmobRewardVideoAdapter.this.mLastRequestCode.equals(DLEWAdmobRewardVideoAdapter.this.mRequestCode)) {
                    DLEWAdmobRewardVideoAdapter.access$308(DLEWAdmobRewardVideoAdapter.this);
                } else {
                    DLEWAdmobRewardVideoAdapter dLEWAdmobRewardVideoAdapter = DLEWAdmobRewardVideoAdapter.this;
                    dLEWAdmobRewardVideoAdapter.mLastRequestCode = dLEWAdmobRewardVideoAdapter.mRequestCode;
                    DLEWAdmobRewardVideoAdapter.this.mMultiClickCount = 1;
                }
            }
            XLog.d(StringFog.decrypt("v8id6N1aque4z4TonleQpK3JgLzlE7TrqIam+dMWq+CvwqL1wBK2xa7lmPXHHLzgj9CR8tA=\n", "yqb0nKR32YQ=\n"));
            DLEWAdEventTrack.OnClicked(StringFog.decrypt("PXJCcSArYgo=\n", "Txc1EFJPB24=\n"), DLEWAdmobRewardVideoAdapter.this.mRequestCode, DLEWAdmobRewardVideoAdapter.this.mCacheSceneType, DLEWAdmobRewardVideoAdapter.this.getNetworkName());
        }

        public void onAdDismissedFullScreenContent() {
            DLEWAdmobRewardVideoAdapter.this.mRewardAd = null;
            if (DLEWAdmobRewardVideoAdapter.this.listener != null) {
                DLEWAdmobRewardVideoAdapter.this.listener.onReward();
            }
            DLEWAdEventTrack.OnRewarded(StringFog.decrypt("APVN2Ps/b6g=\n", "cpA6uYlbCsw=\n"), DLEWAdmobRewardVideoAdapter.this.mRequestCode, DLEWAdmobRewardVideoAdapter.this.mCacheSceneType, DLEWAdmobRewardVideoAdapter.this.getNetworkName());
            DLEWAdmobRewardVideoAdapter.this.mThrottle.AddDailyMultiClickCount(DLEWAdmobRewardVideoAdapter.this.getNetworkName(), DLEWAdmobRewardVideoAdapter.this.mMultiClickCount);
            DLEWAdmobRewardVideoAdapter.this.mMultiClickCount = 0;
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
            if (DLEWAdmobRewardVideoAdapter.this.mThrottle.CheckThrottle(null)) {
                XLog.d(StringFog.decrypt("Fx941LQjy0gQGGHU9y4=\n", "YnERoM0OuCs=\n") + DLEWAdmobRewardVideoAdapter.this.mAdInfo.unitID + StringFog.decrypt("RYD7KYlHcY/iIj+Q/xd40IoRWu3XfxXg6EhcqYdPTYPcEjOY5Q==\n", "Za3WCW/y8GY=\n"));
            } else {
                DLEWAdmobRewardVideoAdapter.this.loadRewardedAd();
            }
        }

        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
            DLEWAdmobRewardVideoAdapter.this.mRewardAd = null;
            XLog.d(StringFog.decrypt("Pa/dpio0dRE6qMSmaTlPUi+uwPISfWsdKuHmtyR4dBYtpeK7N3xpMyyS3L0kX2cbJKTQlyV8aAZk\n4de9N3wmSGjh\n", "SMG00lMZBnI=\n") + adError.getCode() + StringFog.decrypt("tiECEkXDK/zqdQ8YWIBjtQ==\n", "mgFmdzagWZU=\n") + adError.getMessage());
            DLEWAdEventTrack.OnShowFailure(StringFog.decrypt("AkqSG0fjZGE=\n", "cC/lejWHAQU=\n"), DLEWAdmobRewardVideoAdapter.this.mRequestCode, DLEWAdmobRewardVideoAdapter.this.mCacheSceneType, adError.getMessage(), DLEWAdmobRewardVideoAdapter.this.getNetworkName());
            DLEWAdEventTrack.OnTriggeredFail(StringFog.decrypt("pCxiOJ+oxv8=\n", "1kkVWe3Mo5s=\n"), DLEWAdmobRewardVideoAdapter.this.mRequestCode, DLEWAdmobRewardVideoAdapter.this.mCacheSceneType, adError.getMessage(), DLEWAdmobRewardVideoAdapter.this.getNetworkName());
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
            if (DLEWAdmobRewardVideoAdapter.this.listener != null) {
                DLEWAdmobRewardVideoAdapter.this.listener.onFialed();
            }
            DLEWAdmobRewardVideoAdapter.this.loadRewardedAd();
        }

        public void onAdShowedFullScreenContent() {
            XLog.d(StringFog.decrypt("r7+3LE1fvUKouK4sDlKHAb2+qnh1FqNOuPGMPUMTvEW/tYgxUBehYL6erj1aF6pkrLSwLBRf7g==\n", "2tHeWDRyziE=\n") + this + StringFog.decrypt("oxn1ZvWb\n", "gznYS9W7ooY=\n") + DLEWAdmobRewardVideoAdapter.this.mCacheSceneType);
            DLEWAdEventTrack.OnImpression(StringFog.decrypt("D7WGP4iELOY=\n", "fdDxXvrgSYI=\n"), DLEWAdmobRewardVideoAdapter.this.mRequestCode, DLEWAdmobRewardVideoAdapter.this.mCacheSceneType, DLEWAdmobRewardVideoAdapter.this.getNetworkName());
            DLEWAd.addAdDdisplayCount(true);
            DLEWAd.addAdDdisplayDailyCount();
            AdmobUtils.setAdmobFirstShow();
            if (DLEWAdmobRewardVideoAdapter.this.mThrottle.CheckNetworkNameControlled(DLEWAdmobRewardVideoAdapter.this.getNetworkName())) {
                DLEWAdmobRewardVideoAdapter.this.mThrottle.AddDailyShowCount();
            }
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
            GGAnalysis.afTrack(StringFog.decrypt("ec4UY4G78Zh5ziR5grb2kH7PFHrVofua\n", "CqZ7FOfSn/E=\n"), null);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobRewardVideoAdapter$evleeEelE.class */
    public class evleeEelE extends RewardedAdLoadCallback {

        /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobRewardVideoAdapter$evleeEelE$EvLveE1.class */
        public class EvLveE1 implements Runnable {
            public EvLveE1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                DLEWAdmobRewardVideoAdapter.this.loadRewardedAd();
            }
        }

        public evleeEelE() {
        }

        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
            DLEWAdmobRewardVideoAdapter.this.isLoading = false;
            XLog.d(StringFog.decrypt("+leJLtZi8ET9UJAulW/CQ+JWgnrAIcJDw1aBPuku6kvqXcBg\n", "jzngWq9Pgyc=\n") + DLEWAdmobRewardVideoAdapter.this.mAdInfo.unitID);
            DLEWAdmobRewardVideoAdapter.access$908(DLEWAdmobRewardVideoAdapter.this);
            new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, DLEWAdmobRewardVideoAdapter.this.retryAttempt))));
            DLEWAdEventTrack.OnLoadFailure(StringFog.decrypt("LxBuHCPncyo=\n", "XXUZfVGDFk4=\n"), DLEWAdmobRewardVideoAdapter.this.mRequestCode, DLEWAdmobRewardVideoAdapter.this.mCacheSceneType, StringFog.decrypt("YuT45S0JCg==\n", "AYucgBcpKtE=\n") + loadAdError.getCode() + StringFog.decrypt("/OnOdKvThyCgvcN+torV\n", "0MmqEdiw9Uk=\n") + loadAdError.getMessage(), DLEWAdmobRewardVideoAdapter.this.getNetworkName());
        }

        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
            DLEWAdmobRewardVideoAdapter.this.mRewardAd = rewardedAd;
            DLEWAdmobRewardVideoAdapter.this.mRewardAd.setFullScreenContentCallback(DLEWAdmobRewardVideoAdapter.this.mFullScreenContentCallback);
            DLEWAdmobRewardVideoAdapter.this.mRewardAd.setOnPaidEventListener(DLEWAdmobRewardVideoAdapter.this.mOnPaidEventListener);
            DLEWAdmobRewardVideoAdapter dLEWAdmobRewardVideoAdapter = DLEWAdmobRewardVideoAdapter.this;
            dLEWAdmobRewardVideoAdapter.isLoading = false;
            dLEWAdmobRewardVideoAdapter.retryAttempt = 0;
            DLEWAdmobRewardVideoAdapter.this.mAdInfo.networkName = DLEWAdmobRewardVideoAdapter.this.getNetworkName();
            XLog.d(StringFog.decrypt("5/ho5C3oPpzg/3HkbuUE3/X5dbAVoSCQ8LZT9SOkP5v38lf5MKAivuT3aPw1pyST++J40zykI5j3\n8kTmMas537+7IQ==\n", "kpYBkFTFTf8=\n") + DLEWAdmobRewardVideoAdapter.this.mAdInfo.revenue);
            DLEWAdEventTrack.OnLoaded(StringFog.decrypt("IbHwqzSC754=\n", "U9SHykbmivo=\n"), DLEWAdmobRewardVideoAdapter.this.mRequestCode, DLEWAdmobRewardVideoAdapter.this.mCacheSceneType, DLEWAdmobRewardVideoAdapter.this.getNetworkName());
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobRewardVideoAdapter$v1vLvLE.class */
    public class v1vLvLE implements OnPaidEventListener {
        public v1vLvLE() {
        }

        public void onPaidEvent(@NonNull AdValue adValue) {
            DLEWAdmobRewardVideoAdapter.this.mAdInfo.revenue = AdmobUtils.changeLongRevenueToDouble(adValue.getValueMicros());
            AdmobUtils.setAdmobRewardLastRevenue(DLEWAdmobRewardVideoAdapter.this.mAdInfo.revenue);
            XLog.e(StringFog.decrypt("1OPr+/5+u32MisaalVPpE5zRs6Lr\n", "PG1cHnHoXvU=\n") + DLEWAdmobRewardVideoAdapter.this.mAdInfo.revenue);
            DLEWAdmobRewardVideoAdapter.this.trackRevenue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadRewardedAd() {
        if (this.isLoading) {
            return;
        }
        this.isLoading = true;
        XLog.d(StringFog.decrypt("clomrT8HftV1XT+tfApW12NZILsbcXvfY1EghGsHedN0QGL0\n", "BzRP2UYqDbY=\n") + this.mAdInfo.unitID + StringFog.decrypt("0b6R7GQCR2p3djupMD8fJVsVWKtJZxtyGQI7KfWvgg==\n", "/JOxCdiCos0=\n"));
        this.mRequestCode = UUID.randomUUID().toString();
        RewardedAd.load(DLEWSDK.getActivity(), this.mAdInfo.unitID, new AdRequest.Builder().build(), this.mRewardedAdLoadCallback);
        DLEWAdEventTrack.OnRequest(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
    }

    public static /* synthetic */ int access$308(DLEWAdmobRewardVideoAdapter dLEWAdmobRewardVideoAdapter) {
        int i = dLEWAdmobRewardVideoAdapter.mMultiClickCount;
        dLEWAdmobRewardVideoAdapter.mMultiClickCount = i + 1;
        return i;
    }

    public static /* synthetic */ int access$908(DLEWAdmobRewardVideoAdapter dLEWAdmobRewardVideoAdapter) {
        int i = dLEWAdmobRewardVideoAdapter.retryAttempt;
        dLEWAdmobRewardVideoAdapter.retryAttempt = i + 1;
        return i;
    }

    public void trackRevenue() {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra();
        dLEWAdInfoExtra.requestCode = this.mRequestCode;
        dLEWAdInfoExtra.Revenue = this.mAdInfo.revenue;
        dLEWAdInfoExtra.NetworkName = getNetworkName();
        dLEWAdInfoExtra.AdFormat = StringFog.decrypt("n2r76PjeLp4=\n", "zS+sqaqaa9o=\n");
        GGAnalysis.trackImmediately(StringFog.decrypt("5okJaZ7KVyDumwJfmMl4IO6eFFiCwg==\n", "i+hxNvenJ1I=\n"), dLEWAdInfoExtra);
        DLEWRoas.SendMaxRevenueToFirebase(dLEWAdInfoExtra);
        DLEWRoas.CheckTaiChiTcpaAdRevenueEvent(this.mAdInfo.revenue);
        DLEWRoas.CheckTaichiTroasFirebaseAdRevenueEvent(this.mAdInfo.revenue);
        DLEWRoas.trackKwaiValueToAF(this.mAdInfo.revenue);
        DLEWRoas.sendRevenueToAF(dLEWAdInfoExtra);
    }

    @Override // com.dlew.other.ad.core.DLEWiRewardVideoAd
    public void show(String str, DLEWRewardVideoListener dLEWRewardVideoListener) {
        this.mCacheSceneType = str;
        this.listener = dLEWRewardVideoListener;
        if (!NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("/pc1BNxMJl3Ckw==\n", "sPgVSrk4UTI=\n"), getNetworkName());
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
                return;
            }
            return;
        }
        XLog.d(StringFog.decrypt("EpqXmW1F3BQVnY6ZLkjuEwqbnMNdG+4BBp2SjHYEyldK1JeeVR7OHguVnIFxQIaY224=\n", "Z/T+7RRor3c=\n") + isAvailable());
        if (isAvailable()) {
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
            this.mRewardAd.show(DLEWSDK.getActivity(), this.mOnUserEarnedRewardListener);
        } else {
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
            }
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("JbK+0I4f4mQKv/L0\n", "a92ekfh+iwg=\n"), getNetworkName());
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        this.mThrottle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.mAdInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.videoID;
        dLEWAdInfo.revenue = AdmobUtils.getAdmobRewardLastRevenue();
        XLog.d(StringFog.decrypt("tH7nFFwoBtazef4UHyUU0ax/7F0Y\n", "wRCOYCUFdbU=\n") + new Gson().toJson(dLEWLimitConfig));
        if (this.mThrottle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("wwut18BugLzEDLTXg2M=\n", "tmXEo7lD898=\n") + this.mAdInfo.unitID + StringFog.decrypt("ogQs5z6/l00FpuheSO+eEm2VjSNgh/MiD8yLZzC3q0E7luRWUg==\n", "gikBx9gKFqQ=\n"));
        } else {
            loadRewardedAd();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public boolean isAvailable() {
        return this.mRewardAd != null;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getRequestCode() {
        return this.mRequestCode;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getNetworkName() {
        return StringFog.decrypt("re014dkE3/y44C7r\n", "zIlYjrtbsZ0=\n");
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public double checkRevenue(String str) {
        return AdmobUtils.checkRevenue(this.mThrottle, this.mAdInfo, isAvailable(), str, this.mAdType);
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public DLEWAdInfo getAdInfo() {
        return this.mAdInfo;
    }
}