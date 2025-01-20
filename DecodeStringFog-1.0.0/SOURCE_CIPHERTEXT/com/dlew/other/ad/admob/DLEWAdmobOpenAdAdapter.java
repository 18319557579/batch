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
import com.dlew.other.ad.core.DLEWiOpenAd;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.gson.Gson;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobOpenAdAdapter.class */
public class DLEWAdmobOpenAdAdapter implements DLEWiOpenAd {
    private AppOpenAd mAppOpenAd;
    private DLEWInterstitialListener listener;
    private int retryAttempt;
    private DLEWAdThrottle mThrottle;
    private DLEWAdInfo mAdInfo;
    boolean isLoading;
    private String mCacheSceneType = StringFog.decrypt("UNoirA==\n", "Pq9OwM8jgOA=\n");
    private long loadTime = 0;
    private String mRequestCode = UUID.randomUUID().toString();
    private final String mAdType = StringFog.decrypt("MGVCXenYuCI=\n", "URUyAoao3Uw=\n");
    private final FullScreenContentCallback mFullScreenContentCallback = new EvLveE1();
    private OnPaidEventListener mOnPaidEventListener = new v1vLvLE();
    private AppOpenAd.AppOpenAdLoadCallback mAppOpenAdLoadCallback = new evleeEelE();
    private String mLastRequestCode = BuildConfig.VERSION_NAME;
    private int mMultiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobOpenAdAdapter$EvLveE1.class */
    public class EvLveE1 extends FullScreenContentCallback {
        public EvLveE1() {
        }

        public void onAdClicked() {
            if (DLEWAdmobOpenAdAdapter.this.mThrottle.CheckNetworkNameControlled(DLEWAdmobOpenAdAdapter.this.getNetworkName())) {
                DLEWAdmobOpenAdAdapter.this.mThrottle.AddDailyClickCount();
                if (DLEWAdmobOpenAdAdapter.this.mLastRequestCode.equals(DLEWAdmobOpenAdAdapter.this.mRequestCode)) {
                    DLEWAdmobOpenAdAdapter.access$308(DLEWAdmobOpenAdAdapter.this);
                } else {
                    DLEWAdmobOpenAdAdapter dLEWAdmobOpenAdAdapter = DLEWAdmobOpenAdAdapter.this;
                    dLEWAdmobOpenAdAdapter.mLastRequestCode = dLEWAdmobOpenAdAdapter.mRequestCode;
                    DLEWAdmobOpenAdAdapter.this.mMultiClickCount = 1;
                }
            }
            XLog.d(StringFog.decrypt("pTB5p4msryuiN2CnyqGVaLcxZPOx5bEnsn5/o5XvnSyTMnmwm+S4DaY7fqc=\n", "0F4Q0/CB3Eg=\n"));
            DLEWAdEventTrack.OnClicked(StringFog.decrypt("cM3O8bgHIb8=\n", "Eb2+rtd3RNE=\n"), DLEWAdmobOpenAdAdapter.this.mRequestCode, DLEWAdmobOpenAdAdapter.this.mCacheSceneType, DLEWAdmobOpenAdAdapter.this.getNetworkName());
        }

        public void onAdDismissedFullScreenContent() {
            DLEWAdmobOpenAdAdapter.this.mAppOpenAd = null;
            XLog.d(StringFog.decrypt("tBDU0oXqxGSzF83Sxuf+J6YRyYa9o9poo17S1pmp9mOCEtLVmaPycaQQyQ==\n", "wX69pvzHtwc=\n"));
            if (DLEWAdmobOpenAdAdapter.this.listener != null) {
                DLEWAdmobOpenAdAdapter.this.listener.onHide();
            }
            DLEWAdmobOpenAdAdapter.this.mThrottle.AddDailyMultiClickCount(DLEWAdmobOpenAdAdapter.this.getNetworkName(), DLEWAdmobOpenAdAdapter.this.mMultiClickCount);
            DLEWAdmobOpenAdAdapter.this.mMultiClickCount = 0;
            if (DLEWAdmobOpenAdAdapter.this.mThrottle.CheckThrottle(null)) {
                XLog.d(StringFog.decrypt("6P7Xr7LYpz3v+c6v8dU=\n", "nZC+28v11F4=\n") + DLEWAdmobOpenAdAdapter.this.mAdInfo.unitID + StringFog.decrypt("4a2R3dzvdu9GD1Vkqr9/sC48MBmC1xKATGU2XdLnSuN4P1lssA==\n", "wYC8/Tpa9wY=\n"));
            } else {
                DLEWAdmobOpenAdAdapter.this.loadAd();
            }
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
        }

        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
            DLEWAdmobOpenAdAdapter.this.mAppOpenAd = null;
            XLog.d(StringFog.decrypt("2oBQtVKkD/zdh0m1Eak1v8iBTeFq7RHwzc5WsU7nPfv8hla2begV88qKfLdO5wizj41WpU6pRr+P\n", "r+45wSuJfJ8=\n") + adError.getCode() + StringFog.decrypt("y5wgaMQiZrGXyC1i2WEu+A==\n", "57xEDbdBFNg=\n") + adError.getMessage());
            DLEWAdEventTrack.OnShowFailure(StringFog.decrypt("B2hZxKc6ZJM=\n", "Zhgpm8hKAf0=\n"), DLEWAdmobOpenAdAdapter.this.mRequestCode, DLEWAdmobOpenAdAdapter.this.mCacheSceneType, adError.getMessage(), DLEWAdmobOpenAdAdapter.this.getNetworkName());
            DLEWAdEventTrack.OnTriggeredFail(StringFog.decrypt("ffSndh/z9Js=\n", "HITXKXCDkfU=\n"), DLEWAdmobOpenAdAdapter.this.mRequestCode, DLEWAdmobOpenAdAdapter.this.mCacheSceneType, adError.getMessage(), DLEWAdmobOpenAdAdapter.this.getNetworkName());
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
            if (DLEWAdmobOpenAdAdapter.this.listener != null) {
                DLEWAdmobOpenAdAdapter.this.listener.onFialed();
            }
            DLEWAdmobOpenAdAdapter.this.loadAd();
        }

        public void onAdShowedFullScreenContent() {
            XLog.d(StringFog.decrypt("h1QlizP2NNWAUzyLcPsOlpVVON8LvyrZkBojjy+1BtKhUiOIGa4k1ZdfKJounjHTnE5s0mo=\n", "8jpM/0rbR7Y=\n") + this + StringFog.decrypt("vQgoW3z1\n", "nSgFdlzVWKo=\n") + DLEWAdmobOpenAdAdapter.this.mCacheSceneType);
            DLEWAdEventTrack.OnImpression(StringFog.decrypt("vCcfkdmyMME=\n", "3VdvzrbCVa8=\n"), DLEWAdmobOpenAdAdapter.this.mRequestCode, DLEWAdmobOpenAdAdapter.this.mCacheSceneType, DLEWAdmobOpenAdAdapter.this.getNetworkName());
            DLEWAd.addAdDdisplayCount(false);
            DLEWAd.addAdDdisplayDailyCount();
            AdmobUtils.setAdmobFirstShow();
            if (DLEWAdmobOpenAdAdapter.this.mThrottle.CheckNetworkNameControlled(DLEWAdmobOpenAdAdapter.this.getNetworkName())) {
                DLEWAdmobOpenAdAdapter.this.mThrottle.AddDailyShowCount();
            }
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
            GGAnalysis.afTrack(StringFog.decrypt("TAHkncGA5BBMAdSHwo3jGEsA5ISVmu4S\n", "P2mL6qfpink=\n"), null);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobOpenAdAdapter$evleeEelE.class */
    public class evleeEelE extends AppOpenAd.AppOpenAdLoadCallback {

        /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobOpenAdAdapter$evleeEelE$EvLveE1.class */
        public class EvLveE1 implements Runnable {
            public EvLveE1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                DLEWAdmobOpenAdAdapter.this.loadAd();
            }
        }

        public evleeEelE() {
        }

        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
            XLog.d(StringFog.decrypt("oGCdQ9S+pB6nZ4RDl7OeXbJhgBfM97oSty6bR8j9lhmZYZVT6/K+EbBqsUHI/aNR9W2bU8ip9w==\n", "1Q70N62T130=\n") + loadAdError.getCode() + StringFog.decrypt("7465YmEyDoGz2rRofHFGyA==\n", "w67dBxJRfOg=\n") + loadAdError.getMessage());
            DLEWAdEventTrack.OnLoadFailure(StringFog.decrypt("+NH3OmpKJmI=\n", "maGHZQU6Qww=\n"), DLEWAdmobOpenAdAdapter.this.mRequestCode, DLEWAdmobOpenAdAdapter.this.mCacheSceneType, StringFog.decrypt("+/dOddAHoQ==\n", "mJgqEOongaI=\n") + loadAdError.getCode() + StringFog.decrypt("l/5KPh5LtQLLqkc0AxLn\n", "u94uW20ox2s=\n") + loadAdError.getMessage(), DLEWAdmobOpenAdAdapter.this.getNetworkName());
            DLEWAdmobOpenAdAdapter dLEWAdmobOpenAdAdapter = DLEWAdmobOpenAdAdapter.this;
            dLEWAdmobOpenAdAdapter.isLoading = false;
            DLEWAdmobOpenAdAdapter.access$908(dLEWAdmobOpenAdAdapter);
            new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, DLEWAdmobOpenAdAdapter.this.retryAttempt))));
        }

        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
            DLEWAdmobOpenAdAdapter.this.mAppOpenAd = appOpenAd;
            DLEWAdmobOpenAdAdapter.this.mAppOpenAd.setFullScreenContentCallback(DLEWAdmobOpenAdAdapter.this.mFullScreenContentCallback);
            DLEWAdmobOpenAdAdapter.this.mAppOpenAd.setOnPaidEventListener(DLEWAdmobOpenAdAdapter.this.mOnPaidEventListener);
            DLEWAdmobOpenAdAdapter dLEWAdmobOpenAdAdapter = DLEWAdmobOpenAdAdapter.this;
            dLEWAdmobOpenAdAdapter.isLoading = false;
            dLEWAdmobOpenAdAdapter.retryAttempt = 0;
            DLEWAdmobOpenAdAdapter.this.mAdInfo.networkName = DLEWAdmobOpenAdAdapter.this.getNetworkName();
            DLEWAdmobOpenAdAdapter.this.loadTime = new Date().getTime();
            XLog.d(StringFog.decrypt("IUPJD0Xvq3ImRNAPBuKRMTNC1Ft9prV+Ng3PC1msmXUGSMEfRYeudDpZgFYR4g==\n", "VC2gezzC2BE=\n") + DLEWAdmobOpenAdAdapter.this.mAdInfo.revenue);
            DLEWAdEventTrack.OnLoaded(StringFog.decrypt("+t33TkSfAT8=\n", "m62HESvvZFE=\n"), DLEWAdmobOpenAdAdapter.this.mRequestCode, DLEWAdmobOpenAdAdapter.this.mCacheSceneType, DLEWAdmobOpenAdAdapter.this.getNetworkName());
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_OPEN_READY));
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/admob/DLEWAdmobOpenAdAdapter$v1vLvLE.class */
    public class v1vLvLE implements OnPaidEventListener {
        public v1vLvLE() {
        }

        public void onPaidEvent(@NonNull AdValue adValue) {
            DLEWAdmobOpenAdAdapter.this.mAdInfo.revenue = AdmobUtils.changeLongRevenueToDouble(adValue.getValueMicros());
            AdmobUtils.setAdmobOpenLastRevenue(DLEWAdmobOpenAdAdapter.this.mAdInfo.revenue);
            DLEWAdmobOpenAdAdapter.this.trackRevenue();
            XLog.e(StringFog.decrypt("lPEHzswTuo/MmCqvpz7o4dzDX5fZ\n", "fH+wK0OFXwc=\n") + DLEWAdmobOpenAdAdapter.this.mAdInfo.revenue);
        }
    }

    private boolean wasLoadTimeLessThanNHoursAgo(long j) {
        return new Date().getTime() - this.loadTime < j * 3600000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAd() {
        if (this.isLoading) {
            return;
        }
        this.isLoading = true;
        XLog.d(StringFog.decrypt("G4NWfZiELZgchE9924kFmgqAUGu88iiSCohQVJXMLY9DwBI=\n", "bu0/CeGpXvs=\n") + this.mAdInfo.unitID + StringFog.decrypt("VLpDe/DtbTPycuk+pNA1fN4RijzdiDErnAbpvmFAqA==\n", "eZdjnkxtiJQ=\n"));
        this.mRequestCode = UUID.randomUUID().toString();
        AppOpenAd.load(DLEWSDK.getActivity(), this.mAdInfo.unitID, new AdRequest.Builder().build(), 1, this.mAppOpenAdLoadCallback);
        DLEWAdEventTrack.OnRequest(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
    }

    public static /* synthetic */ int access$308(DLEWAdmobOpenAdAdapter dLEWAdmobOpenAdAdapter) {
        int i = dLEWAdmobOpenAdAdapter.mMultiClickCount;
        dLEWAdmobOpenAdAdapter.mMultiClickCount = i + 1;
        return i;
    }

    public static /* synthetic */ int access$908(DLEWAdmobOpenAdAdapter dLEWAdmobOpenAdAdapter) {
        int i = dLEWAdmobOpenAdAdapter.retryAttempt;
        dLEWAdmobOpenAdAdapter.retryAttempt = i + 1;
        return i;
    }

    public void trackRevenue() {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra();
        dLEWAdInfoExtra.requestCode = this.mRequestCode;
        dLEWAdInfoExtra.Revenue = this.mAdInfo.revenue;
        dLEWAdInfoExtra.NetworkName = getNetworkName();
        dLEWAdInfoExtra.AdFormat = StringFog.decrypt("cdnLYBHZRQ==\n", "MImbL0GcC5o=\n");
        GGAnalysis.trackImmediately(StringFog.decrypt("+3rQVjeli+DzaNtgMaak4PNtzWcrrQ==\n", "lhuoCV7I+5I=\n"), dLEWAdInfoExtra);
        DLEWRoas.SendMaxRevenueToFirebase(dLEWAdInfoExtra);
        DLEWRoas.CheckTaiChiTcpaAdRevenueEvent(this.mAdInfo.revenue);
        DLEWRoas.CheckTaichiTroasFirebaseAdRevenueEvent(this.mAdInfo.revenue);
        DLEWRoas.trackKwaiValueToAF(this.mAdInfo.revenue);
        DLEWRoas.sendRevenueToAF(dLEWAdInfoExtra);
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        this.mThrottle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.mAdInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.openID;
        dLEWAdInfo.revenue = AdmobUtils.getAdmobOpenLastRevenue();
        XLog.d(StringFog.decrypt("HEEF7JQqNdIbRhzs1ycn1QRADqXQ\n", "aS9smO0HRrE=\n") + new Gson().toJson(dLEWLimitConfig));
        if (this.mThrottle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("0bD+h3Skff3Wt+eHN6k=\n", "pN6X8w2JDp4=\n") + this.mAdInfo.unitID + StringFog.decrypt("BKXrlZmscBSjBy8s7/x5S8s0SlHHlBR7qW1MFZekTBidNyMk9Q==\n", "JIjGtX8Z8f0=\n"));
        } else {
            loadAd();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public boolean isAvailable() {
        return this.mAppOpenAd != null && wasLoadTimeLessThanNHoursAgo(4L);
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getRequestCode() {
        return this.mRequestCode;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getNetworkName() {
        return StringFog.decrypt("MO9sq3/0VZkl4neh\n", "UYsBxB2rO/g=\n");
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public double checkRevenue(String str) {
        return AdmobUtils.checkRevenue(this.mThrottle, this.mAdInfo, isAvailable(), str, this.mAdType);
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public DLEWAdInfo getAdInfo() {
        return this.mAdInfo;
    }

    @Override // com.dlew.other.ad.core.DLEWiOpenAd
    public void show(String str, DLEWInterstitialListener dLEWInterstitialListener) {
        this.mCacheSceneType = str;
        this.listener = dLEWInterstitialListener;
        if (!NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("jeN/JMDyKtSx5w==\n", "w4xfaqWGXbs=\n"), getNetworkName());
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onFialed();
                return;
            }
            return;
        }
        XLog.d(StringFog.decrypt("azJOd15yMRdsNVd3HX8DEHMzRS1uLAMCfzVLYkUzJ1QzfE5wZikjHXI9RW9Cd2ubosY=\n", "HlwnAydfQnQ=\n") + isAvailable());
        if (isAvailable()) {
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
            this.mAppOpenAd.show(DLEWSDK.getActivity());
        } else {
            if (dLEWInterstitialListener != null) {
                dLEWInterstitialListener.onFialed();
            }
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("bLQ68pGiIEtDuXbW\n", "Itsas+fDSSc=\n"), getNetworkName());
        }
    }
}