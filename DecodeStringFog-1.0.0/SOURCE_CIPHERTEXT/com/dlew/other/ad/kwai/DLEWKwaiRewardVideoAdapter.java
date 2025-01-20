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
import com.dlew.other.ad.core.DLEWRewardVideoListener;
import com.dlew.other.ad.core.DLEWiRewardVideoAd;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import com.kwai.network.sdk.KwaiAdSDK;
import com.kwai.network.sdk.api.KwaiAdLoaderManager;
import com.kwai.network.sdk.constant.KwaiError;
import com.kwai.network.sdk.loader.business.reward.data.KwaiRewardAd;
import com.kwai.network.sdk.loader.business.reward.data.KwaiRewardAdConfig;
import com.kwai.network.sdk.loader.business.reward.data.KwaiRewardAdRequest;
import com.kwai.network.sdk.loader.business.reward.interf.IKwaiRewardAdListener;
import com.kwai.network.sdk.loader.common.interf.AdLoadListener;
import com.kwai.network.sdk.loader.common.interf.IKwaiAdLoader;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/kwai/DLEWKwaiRewardVideoAdapter.class */
public class DLEWKwaiRewardVideoAdapter implements AdLoadListener<KwaiRewardAd>, IKwaiRewardAdListener, DLEWiRewardVideoAd {
    private KwaiRewardAd mKwaiRewardAd;
    private DLEWRewardVideoListener listener;
    int retryAttempt;
    private DLEWAdThrottle m_throttle;
    private DLEWAdInfo m_adInfo;
    boolean isLoading;
    private IKwaiAdLoader<KwaiRewardAdRequest> mKwaiRewardAdAdLoader = null;
    private String mCacheSceneType = StringFog.decrypt("gq6YbA==\n", "7Nv0AO2ypeU=\n");
    private String mRequestCode = UUID.randomUUID().toString();
    private String mAdType = StringFog.decrypt("854UJFygyDg=\n", "gftjRS7ErVw=\n");
    private boolean mAdRewarded = false;
    private boolean mAdClosed = false;
    private final String VideoTimesKey = StringFog.decrypt("qst9NEk1S+qK6HEiawVU7YnPfw56M0/hng==\n", "7aoQUQ5aIoQ=\n");
    private String m_lastRequestCode = BuildConfig.VERSION_NAME;
    private int m_multiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/kwai/DLEWKwaiRewardVideoAdapter$EvLveE1.class */
    public class EvLveE1 implements Runnable {
        public EvLveE1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DLEWKwaiRewardVideoAdapter.this.LoadRewardedAd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LoadRewardedAd() {
        if (this.isLoading) {
            return;
        }
        XLog.d(StringFog.decrypt("MuVY3hPLjeU14kHeUMalzTDqWPcxkJfiIuRs\n", "R4sxqmrm/oY=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("enIITpnRBfHcuqILzexdvvDZwQm0tFnpss6iiwh8wA==\n", "V18oqyVR4FY=\n"));
        String uuid = UUID.randomUUID().toString();
        this.mRequestCode = uuid;
        DLEWAdEventTrack.OnRequest(this.mAdType, uuid, this.mCacheSceneType, getNetworkName());
        this.isLoading = true;
        KwaiAdLoaderManager kwaiAdLoaderManager = KwaiAdSDK.getKwaiAdLoaderManager();
        if (kwaiAdLoaderManager != null) {
            IKwaiAdLoader<KwaiRewardAdRequest> buildRewardAdLoader = kwaiAdLoaderManager.buildRewardAdLoader(new KwaiRewardAdConfig.Builder(this).withKwaiRewardAdListener(this).build());
            this.mKwaiRewardAdAdLoader = buildRewardAdLoader;
            buildRewardAdLoader.loadAd(new KwaiRewardAdRequest(this.m_throttle.getConfig().videoID));
        }
    }

    public void trackRevenue() {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra();
        dLEWAdInfoExtra.requestCode = this.mRequestCode;
        dLEWAdInfoExtra.Revenue = this.m_adInfo.revenue;
        dLEWAdInfoExtra.NetworkName = getNetworkName();
        dLEWAdInfoExtra.AdFormat = StringFog.decrypt("9iF+qhQCfi8=\n", "pGQp60ZGO2s=\n");
        GGAnalysis.trackImmediately(StringFog.decrypt("Y5fAtnhtwTtrhcuAfm7uO2uA3YdkZQ==\n", "Dva46REAsUk=\n"), dLEWAdInfoExtra);
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
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("piyDdhWiD8iaKA==\n", "6EOjOHDWeKc=\n"), getNetworkName());
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
                return;
            }
            return;
        }
        XLog.d(StringFog.decrypt("I+H62qIQhIgk5uPa4R28nDfmveeofIGKP+PyzLdY18Z25uDvrVyehzft/8vzFBhXzA==\n", "Vo+Trts99+s=\n") + isAvailable());
        if (isAvailable()) {
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
            this.mKwaiRewardAd.show(DLEWSDK.getActivity());
        } else {
            XLog.d(StringFog.decrypt("1m+G06my4zXRaJ/T6r/bIcJowe6j3uY3ym2Oxbz6sHuDR47Lo/o=\n", "owHvp9CfkFY=\n"));
            if (dLEWRewardVideoListener != null) {
                dLEWRewardVideoListener.onFialed();
            }
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("FdIZy0EcSYo631Xv\n", "W705ijd9IOY=\n"), getNetworkName());
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        this.m_throttle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.m_adInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.videoID;
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("jcjSPzfWwqKKz8s/dNs=\n", "+Ka7S077scE=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("WH9vCeASgA//3auwlkKJUJfuzs2+KuRg9bfIie4avAPB7ae4jA==\n", "eFJCKQanAeY=\n"));
        } else {
            LoadRewardedAd();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public boolean isAvailable() {
        KwaiRewardAd kwaiRewardAd = this.mKwaiRewardAd;
        return kwaiRewardAd != null && kwaiRewardAd.isReady();
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getRequestCode() {
        return this.mRequestCode;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getNetworkName() {
        return StringFog.decrypt("OgDPFIweq944Acs=\n", "UXeufdNwyqo=\n");
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public double checkRevenue(String str) {
        if (this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName) && this.m_throttle.CheckThrottle(str)) {
            return -1.0d;
        }
        if (isAvailable()) {
            return this.m_adInfo.revenue;
        }
        XLog.d(StringFog.decrypt("ufQdV561UL2+8wRX3bh4lbv7HX687kq6qfUp\n", "zJp0I+eYI94=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("cXt9Kw3dz9j997WyVKTytLfB8OxX0oaTyQ==\n", "UVZQC+tBYz4=\n"));
        return this.m_throttle.CheckThrottle(str) ? -1.0d : 0.0d;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public DLEWAdInfo getAdInfo() {
        return this.m_adInfo;
    }

    public void onAdLoadStart(@Nullable String str) {
    }

    public void onAdLoadFailed(@Nullable String str, @NonNull KwaiError kwaiError) {
        this.isLoading = false;
        XLog.d(StringFog.decrypt("2i2TUJukddrdKopQ2KlNzs4q2kuMyGL1wCKeYoPgatzLY8A=\n", "r0P6JOKJBrk=\n") + this.m_adInfo.unitID);
        this.retryAttempt = this.retryAttempt + 1;
        new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, r2))));
        DLEWAdEventTrack.OnLoadFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("d5TJL3pnlg==\n", "FPutSkBHtok=\n") + kwaiError.getCode() + StringFog.decrypt("ZRLkjbkdhuw5RumHpETU\n", "STKA6Mp+9IU=\n") + kwaiError.getMsg(), getNetworkName());
    }

    public void onRewardEarned() {
        XLog.d(StringFog.decrypt("gFxWJmHlbOeHW08mIuhWpJJdS3JTv37t1WBaJXm6e+GRZFY2fade4KdXSDNqrHrgsERaPGw=\n", "9TI/UhjIH4Q=\n"));
        this.mAdRewarded = true;
        GGAnalysis.afTrack(StringFog.decrypt("pSxxwTb8lMC+J2/TLA==\n", "10kGoESYy6Y=\n"), null);
    }

    public void onAdShow() {
        XLog.d(StringFog.decrypt("oEKYla/Tc7anRYGV7N5J9bJDhcGdiWG89X6UlreMZLCxepiFs5FBsZpclI+zmkWjsEKFwfve\n", "1Szx4db+ANU=\n") + this + StringFog.decrypt("5s5XFBeT\n", "xu56OTeztg4=\n") + this.mCacheSceneType);
        DLEWAdEventTrack.OnImpression(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        DLEWAd.addAdDdisplayCount(true);
        DLEWAd.addAdDdisplayDailyCount();
        if (this.m_throttle.CheckNetworkNameControlled(getNetworkName())) {
            this.m_throttle.AddDailyShowCount();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
        GGAnalysis.afTrack(StringFog.decrypt("dYAucpIk+sF1gB5okSn9yXKBLmvGPvDD\n", "BuhBBfRNlKg=\n"), null);
        trackRevenue();
    }

    public void onAdShowFailed(@NonNull KwaiError kwaiError) {
        LoadRewardedAd();
        XLog.d(StringFog.decrypt("Ptb3sr6JBsw50e6y/YQ8jyzX6uaM0xTGa+r7sabWEcov7veioss0yxjQ8bGBxRzDLtzbsKLKAYNr\n2/GiooRPj2s=\n", "S7iexsekda8=\n") + kwaiError.getCode() + StringFog.decrypt("Zyk9kqjFPW47fTCYtYZ1Jw==\n", "SwlZ99umTwc=\n") + kwaiError.getMsg());
        DLEWAdEventTrack.OnShowFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, kwaiError.getMsg(), getNetworkName());
        DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, kwaiError.getMsg(), getNetworkName());
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
        DLEWRewardVideoListener dLEWRewardVideoListener = this.listener;
        if (dLEWRewardVideoListener != null) {
            dLEWRewardVideoListener.onFialed();
        }
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
        XLog.d(StringFog.decrypt("AwgfwCxrLrEEDwbAb2YU8hEJApQeMTy7VjQTwzQ0ObcSMB/QMCkctjUKH9c+IzmXAAMYwA==\n", "dmZ2tFVGXdI=\n"));
        DLEWAdEventTrack.OnClicked(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
    }

    public void onAdClose() {
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
            XLog.d(StringFog.decrypt("9l1Y9Nx3Et3xWkH0n3o=\n", "gzMxgKVaYb4=\n") + this.m_throttle.getConfig().videoID + StringFog.decrypt("6OAgeWcFY95PQuTAEVVqgSdxgb05PQexRSiH+WkNX9JxcujICw==\n", "yM0NWYGw4jc=\n"));
        } else {
            LoadRewardedAd();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
    }

    public void onAdPlayComplete() {
    }

    public void sendBinWin() {
        KwaiRewardAd kwaiRewardAd = this.mKwaiRewardAd;
        if (kwaiRewardAd != null) {
            kwaiRewardAd.getBidController().sendBidWin();
        }
    }

    public void sendBinLose() {
        KwaiRewardAd kwaiRewardAd = this.mKwaiRewardAd;
        if (kwaiRewardAd != null) {
            kwaiRewardAd.getBidController().sendBidLose();
        }
    }

    public void onAdLoadSuccess(@Nullable String str, @NonNull KwaiRewardAd kwaiRewardAd) {
        this.mKwaiRewardAd = kwaiRewardAd;
        this.isLoading = false;
        this.retryAttempt = 0;
        if (kwaiRewardAd.getPrice().isEmpty()) {
            this.m_adInfo.revenue = 0.0d;
        } else {
            this.m_adInfo.revenue = Double.parseDouble(this.mKwaiRewardAd.getPrice()) / 1000.0d;
        }
        XLog.d(StringFog.decrypt("Ael5DpAAB8QG7mAO0w09hxPoZFqiWhXOVNV1DYhfEMIQ0XkejEI10RXufBuLRBjOAP5TEohDE8IQ\nwmYfh1lUilmn\n", "dIcQeuktdKc=\n") + this.m_adInfo.revenue);
        this.m_adInfo.networkName = getNetworkName();
        DLEWAdEventTrack.OnLoaded(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
    }
}