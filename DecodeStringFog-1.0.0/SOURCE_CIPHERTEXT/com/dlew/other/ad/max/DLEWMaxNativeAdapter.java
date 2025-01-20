package com.dlew.other.ad.max;

import android.content.Context;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.dlew.StringFog;
import com.dlew.log.GGAnalysis;
import com.dlew.other.ad.DLEWAdEventTrack;
import com.dlew.other.ad.DLEWAdInfoExtra;
import com.dlew.other.ad.DLEWAdThrottle;
import com.dlew.other.ad.DLEWRoas;
import com.dlew.other.ad.core.DLEWNativeAd;
import com.dlew.other.ad.core.DLEWNativeAdListener;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import java.util.UUID;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/max/DLEWMaxNativeAdapter.class */
public class DLEWMaxNativeAdapter extends MaxNativeAdListener implements MaxAdRevenueListener, DLEWNativeAd {
    private boolean mLoading;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;
    private FrameLayout nativeAdContainer;
    DLEWNativeAdListener nativeAdListener;
    DLEWAdThrottle m_throttle;
    private String mCacheSceneType = BuildConfig.VERSION_NAME;
    private String mRequestCode = UUID.randomUUID().toString();
    private String mAdType = StringFog.decrypt("WY4FnN2Y\n", "N+9x9av9dok=\n");
    private int m_multiClickCount = 0;
    private String m_lastRequestCode = BuildConfig.VERSION_NAME;

    @Override // com.dlew.other.ad.core.DLEWNativeAd
    public DLEWNativeAd show(DLEWAdThrottle dLEWAdThrottle, Context context, String str, String str2, FrameLayout frameLayout, DLEWNativeAdListener dLEWNativeAdListener) {
        this.m_throttle = dLEWAdThrottle;
        this.nativeAdContainer = frameLayout;
        this.nativeAdListener = dLEWNativeAdListener;
        MaxNativeAdLoader maxNativeAdLoader = new MaxNativeAdLoader(str, context);
        this.nativeAdLoader = maxNativeAdLoader;
        maxNativeAdLoader.setNativeAdListener(this);
        this.nativeAdLoader.setRevenueListener(this);
        this.mCacheSceneType = str2;
        this.nativeAdLoader.loadAd();
        DLEWAdEventTrack.OnRequest(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("lwE/\n", "+mBHx6TTZJQ=\n"));
        return null;
    }

    @Override // com.dlew.other.ad.core.DLEWNativeAd
    public void destroy() {
        MaxAd maxAd = this.nativeAd;
        if (maxAd != null) {
            this.m_throttle.AddDailyMultiClickCount(maxAd.getNetworkName(), this.m_multiClickCount);
            this.nativeAdLoader.destroy(this.nativeAd);
        }
        FrameLayout frameLayout = this.nativeAdContainer;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
            this.nativeAdContainer = null;
        }
    }

    public void onNativeAdLoaded(@Nullable MaxNativeAdView maxNativeAdView, MaxAd maxAd) {
        super.onNativeAdLoaded(maxNativeAdView, maxAd);
        XLog.d(StringFog.decrypt("vE+KHSlu5127SJMdamPdHq5Ol0k/LdpfvUiVDBEn2FGoRYYNcGO5E+k=\n", "ySHjaVBDlD4=\n") + maxAd.getAdUnitId() + StringFog.decrypt("gRoNHA==\n", "oTcgPAM/PNg=\n") + maxAd.getNetworkName());
        MaxAd maxAd2 = this.nativeAd;
        if (maxAd2 != null) {
            this.nativeAdLoader.destroy(maxAd2);
        }
        this.nativeAd = maxAd;
        if (this.nativeAdContainer != null) {
            maxAd.getNetworkName();
            this.nativeAdContainer.removeAllViews();
            this.nativeAdContainer.addView(maxNativeAdView);
            DLEWAdEventTrack.OnTriggeredSuccess(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
            DLEWAdEventTrack.OnImpression(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
            DLEWNativeAdListener dLEWNativeAdListener = this.nativeAdListener;
            if (dLEWNativeAdListener != null) {
                dLEWNativeAdListener.onShow();
            }
        } else {
            DLEWAdEventTrack.OnShowFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("aTsoBimzl6FYdC8BaLSMqEY=\n", "KlRGckja+cQ=\n"), this.nativeAd.getNetworkName());
        }
        DLEWAdEventTrack.OnLoaded(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
    }

    public void onNativeAdClicked(MaxAd maxAd) {
        super.onNativeAdClicked(maxAd);
        DLEWAdEventTrack.OnClicked(this.mAdType, this.mRequestCode, this.mCacheSceneType, maxAd.getNetworkName());
        XLog.d(StringFog.decrypt("aWzNu1ZL68Rua9S7FUbRh3tt0O9ACNbGaGvSqm4C28t1Yc+qS0a4ijEi\n", "HAKkzy9mmKc=\n") + maxAd.getAdUnitId());
        if (this.m_throttle.CheckNetworkNameControlled(maxAd.getNetworkName())) {
            this.m_throttle.AddDailyClickCount();
            if (this.m_lastRequestCode.equals(this.mRequestCode)) {
                this.m_multiClickCount++;
            } else {
                this.m_lastRequestCode = this.mRequestCode;
                this.m_multiClickCount = 1;
            }
        }
    }

    public void onNativeAdLoadFailed(String str, MaxError maxError) {
        super.onNativeAdLoadFailed(str, maxError);
        DLEWAdEventTrack.OnLoadFailure(this.mAdType, this.mRequestCode, this.mCacheSceneType, StringFog.decrypt("a4ZMVCLOZg==\n", "COkoMRjuRqY=\n") + maxError.getCode() + StringFog.decrypt("mCOVByQz9kHEd5gNOWqk\n", "tAPxYldQhCg=\n") + maxError.getMessage(), StringFog.decrypt("sI+P\n", "3e73bV+zh3E=\n"));
        XLog.d(StringFog.decrypt("jjQf97SuPjaJMwb396MEdZw1AqOi7QM0jzMA5oznATqaPjDipO8oMdt6W67t\n", "+1p2g82DTVU=\n") + maxError.getMessage());
        DLEWNativeAdListener dLEWNativeAdListener = this.nativeAdListener;
        if (dLEWNativeAdListener != null) {
            dLEWNativeAdListener.onFialed();
        }
    }

    public void onAdRevenuePaid(MaxAd maxAd) {
        if (this.m_throttle.CheckNetworkNameControlled(maxAd.getNetworkName())) {
            this.m_throttle.AddDailyShowCount();
        }
        DLEWAdInfoExtra dLEWAdInfoExtra = new DLEWAdInfoExtra(maxAd, this.mRequestCode);
        GGAnalysis.trackImmediately(StringFog.decrypt("uovX+1AG8fiymdzNVgXe+LKcyspMDg==\n", "1+qvpDlrgYo=\n"), dLEWAdInfoExtra);
        DLEWRoas.SendMaxRevenueToFirebase(dLEWAdInfoExtra);
        DLEWRoas.CheckTaiChiTcpaAdRevenueEvent(maxAd.getRevenue());
        DLEWRoas.CheckTaichiTroasFirebaseAdRevenueEvent(maxAd.getRevenue());
        DLEWRoas.trackKwaiValueToAF(maxAd.getRevenue());
        DLEWRoas.sendRevenueToAF(dLEWAdInfoExtra);
    }
}