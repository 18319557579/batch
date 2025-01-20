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
import com.dlew.other.ad.core.DLEWiOpenAd;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import sg.bigo.ads.api.AdError;
import sg.bigo.ads.api.AdLoadListener;
import sg.bigo.ads.api.SplashAd;
import sg.bigo.ads.api.SplashAdInteractionListener;
import sg.bigo.ads.api.SplashAdLoader;
import sg.bigo.ads.api.SplashAdRequest;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/bigo/DLEWBigoOpenAdapter.class */
public class DLEWBigoOpenAdapter implements AdLoadListener<SplashAd>, SplashAdInteractionListener, DLEWiOpenAd {
    private SplashAd splashAd;
    private DLEWInterstitialListener listener;
    private boolean mLoading;
    int retryAttempt;
    private DLEWAdThrottle m_throttle;
    private DLEWAdInfo m_adInfo;
    Activity mContext;
    boolean isLoading;
    private String mCacheSceneType = StringFog.decrypt("zGyD6g==\n", "ohnvhiUAZ8Y=\n");
    private String mRequestCode = UUID.randomUUID().toString();
    private String mAdType = StringFog.decrypt("ZvscfpR2Sjo=\n", "B4tsIfsGL1Q=\n");
    boolean showAndroidActivity = false;
    private String m_lastRequestCode = BuildConfig.VERSION_NAME;
    private int m_multiClickCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/bigo/DLEWBigoOpenAdapter$EvLveE1.class */
    public class EvLveE1 implements Runnable {
        public EvLveE1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DLEWBigoOpenAdapter.this.Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getRequestCode() {
        return this.mRequestCode;
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public String getNetworkName() {
        return StringFog.decrypt("FAgwgGSrzVMfFzI=\n", "dmFX7zvFrCc=\n");
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public double checkRevenue(String str) {
        if (this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName) && this.m_throttle.CheckThrottle(str)) {
            return -1.0d;
        }
        if (isAvailable()) {
            return this.m_adInfo.revenue;
        }
        XLog.d(StringFog.decrypt("LdV8zsjRuq4q0mXOi9ySrzHceufqk7moNuY1\n", "WLsVurH8yc0=\n") + this.m_throttle.getConfig().openID + StringFog.decrypt("LOZK1INc+3KgaoJN2iXGHupcxxPZU7I5lA==\n", "DMtn9GXAV5Q=\n"));
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
        XLog.d(StringFog.decrypt("17XY46MLUsXQssHj4AY=\n", "otuxl9omIaY=\n") + this.m_throttle.getConfig().openID + StringFog.decrypt("Hmb3p6oTjdyZwD8N70ewhB4Js+Agj2JJWyX6YvYQ6Ki0\n", "Pkvah0+vDTk=\n"));
        String uuid = UUID.randomUUID().toString();
        this.mRequestCode = uuid;
        DLEWAdEventTrack.OnRequest(this.mAdType, uuid, this.mCacheSceneType, getNetworkName());
        this.mLoading = true;
        SplashAd splashAd = this.splashAd;
        if (splashAd != null) {
            splashAd.destroy();
            this.splashAd = null;
        }
        new SplashAdLoader.Builder().withAdLoadListener(this).build().loadAd(new SplashAdRequest.Builder().withSlotId(this.m_throttle.getConfig().openID).build());
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public void init(DLEWLimitConfig dLEWLimitConfig, Activity activity) {
        this.m_throttle = new DLEWAdThrottle(dLEWLimitConfig);
        DLEWAdInfo dLEWAdInfo = new DLEWAdInfo();
        this.m_adInfo = dLEWAdInfo;
        dLEWAdInfo.unitID = dLEWLimitConfig.openID;
        this.mContext = activity;
        if (this.m_throttle.CheckThrottle(null)) {
            XLog.d(StringFog.decrypt("Se3Fh1W30SVO6tyHFro=\n", "PIOs8yyaokY=\n") + this.m_throttle.getConfig().openID + StringFog.decrypt("QOjfhhpdSdDnShs/bA1Aj495fkJEZS2/7SB4BhRVddzZehc3dg==\n", "YMXypvzoyDk=\n"));
        } else {
            Load();
        }
    }

    @Override // com.dlew.other.ad.core.DLEWiAd
    public boolean isAvailable() {
        SplashAd splashAd = this.splashAd;
        return (splashAd == null || splashAd.isExpired()) ? false : true;
    }

    @Override // com.dlew.other.ad.core.DLEWiOpenAd
    public void show(String str, DLEWInterstitialListener dLEWInterstitialListener) {
        this.mCacheSceneType = str;
        this.listener = dLEWInterstitialListener;
        if (!NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("3WkXhhDwyM7hbQ==\n", "kwY3yHWEv6E=\n"), getNetworkName());
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
            DLEWAdEventTrack.OnTriggeredFail(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("Ll4EjSU35mkBU0ip\n", "YDEkzFNWjwU=\n"), getNetworkName());
            return;
        }
        if (this.m_throttle.getConfig().showLoading <= 0 || !this.m_throttle.CheckNetworkNameControlled(this.m_adInfo.networkName)) {
            this.splashAd.show();
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        } else {
            this.splashAd.show();
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        }
    }

    public void trackRevenue() {
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra();
        dLEWAdInfoExtra.requestCode = this.mRequestCode;
        dLEWAdInfoExtra.Revenue = this.m_adInfo.revenue;
        dLEWAdInfoExtra.NetworkName = getNetworkName();
        dLEWAdInfoExtra.AdFormat = StringFog.decrypt("GkEBltsAiw==\n", "WxFR2YtFxSM=\n");
        GGAnalysis.trackImmediately(StringFog.decrypt("9cf3Sy3XC5791fx9K9Qknv3Q6nox3w==\n", "mKaPFES6e+w=\n"), dLEWAdInfoExtra);
        DLEWRoas.SendMaxRevenueToFirebase(dLEWAdInfoExtra);
        DLEWRoas.CheckTaiChiTcpaAdRevenueEvent(this.m_adInfo.revenue);
        DLEWRoas.CheckTaichiTroasFirebaseAdRevenueEvent(this.m_adInfo.revenue);
        DLEWRoas.trackKwaiValueToAF(this.m_adInfo.revenue);
        DLEWRoas.sendRevenueToAF(dLEWAdInfoExtra);
    }

    public void onError(@NonNull AdError adError) {
        XLog.d(StringFog.decrypt("brLmgYWlEI5ptf+BxqgqzXyz+9W+4QSCO7P/kJLJB6F0veuzneEPiH+Z+ZCS/E/NeLPrkMao\n", "G9yP9fyIY+0=\n") + adError.getCode() + StringFog.decrypt("WWqaaWhIG98FPpdjdQtTlg==\n", "dUr+DBsrabY=\n") + adError.getMessage());
        DLEWAdEventTrack.OnLoadFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("8bpxWFzA9w==\n", "ktUVPWbg17I=\n") + adError.getCode() + StringFog.decrypt("vvZH6ejpXkTiokrj9bAM\n", "ktYjjJuKLC0=\n") + adError.getMessage(), getNetworkName());
        this.mLoading = false;
        this.retryAttempt = this.retryAttempt + 1;
        new Handler().postDelayed(new EvLveE1(), TimeUnit.SECONDS.toMillis((long) Math.pow(2.0d, Math.min(6, r1))));
    }

    public void onAdError(@NonNull AdError adError) {
        XLog.d(StringFog.decrypt("j9Ma2lf7Du6I1APaFPY0rZ3SB45svxri2tIDy0CXGd6S0gToT78R6J74BctAolGt\n", "+r1zri7WfY0=\n") + adError.getMessage());
        DLEWAdEventTrack.OnShowFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, adError.getMessage(), getNetworkName());
        DLEWInterstitialListener dLEWInterstitialListener = this.listener;
        if (dLEWInterstitialListener != null) {
            dLEWInterstitialListener.onFialed();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
        Load();
    }

    public void onAdImpression() {
        XLog.d(StringFog.decrypt("Z3b97PPERQZgceTssMl/RXV34LjIgFEKMnfk/eSoUjZ6d+PL/4pVAHd88fzPn1MLZji5tao=\n", "EhiUmIrpNmU=\n") + this + StringFog.decrypt("gqbv6w==\n", "oovCy+RE5ws=\n") + this.mCacheSceneType);
        DLEWAdEventTrack.OnImpression(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        if (this.m_throttle.CheckNetworkNameControlled(getNetworkName())) {
            this.m_throttle.AddDailyShowCount();
        }
        DLEWAd.addAdDdisplayCount(false);
        DLEWAd.addAdDdisplayDailyCount();
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_SHOW));
        GGAnalysis.afTrack(StringFog.decrypt("PCrsnkVcq5w8KtyERlGslDsr7IcRRqGe\n", "T0KD6SM1xfU=\n"), null);
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
        XLog.d(StringFog.decrypt("ObjhEYKyeyI+v/gRwb9BYSu5/EW59m8ubLn4AJXebAIgv+sOnvtNNym4/A==\n", "TNaIZfufCEE=\n"));
        DLEWAdEventTrack.OnClicked(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
    }

    public void onAdOpened() {
    }

    public void onAdClosed() {
    }

    public void onAdSkipped() {
        XLog.d(StringFog.decrypt("bLU5h0lGJUhrsiCHCksfC360JNNfGzNFWL8Tn18YM09crTWdRA==\n", "GdtQ8zBrVis=\n"));
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
            XLog.d(StringFog.decrypt("7v5Y0h+AkW3p+UHSXI0=\n", "m5Axpmat4g4=\n") + this.m_throttle.getConfig().openID + StringFog.decrypt("tBDpEcK1gUATsi2otOWIH3uBSNWcjeUvGdhOkcy9vUwtgiGgrg==\n", "lD3EMSQAAKk=\n"));
        } else {
            Load();
        }
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_HIDE));
    }

    public void onAdFinished() {
    }

    public void onAdLoaded(@NonNull SplashAd splashAd) {
        DLEWAdEventTrack.OnLoaded(this.mAdType, this.mRequestCode, this.mCacheSceneType, getNetworkName());
        this.splashAd = splashAd;
        splashAd.setAdInteractionListener(this);
        if (this.splashAd.getBid() != null) {
            this.m_adInfo.revenue = this.splashAd.getBid().getPrice() / 1000.0d;
        } else {
            this.m_adInfo.revenue = 0.0d;
        }
        XLog.d(StringFog.decrypt("NFYZVLFj1cIzUQBU8m7vgSZXBACKJ8HOYVcARaYPwvMkWRRZjTjDzzUYUA3lbg==\n", "QThwIMhOpqE=\n") + this.m_adInfo.revenue);
        this.m_adInfo.networkName = getNetworkName();
        this.mLoading = false;
        this.retryAttempt = 0;
        EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.AD_ON_OPEN_READY));
    }
}