package com.dlew.net;

import android.text.TextUtils;
import com.dlew.StringFog;
import com.dlew.hardware.PhoneInfoWrapper;
import com.dlew.lib.DLEWConfig;
import com.dlew.lib.DLEWSDK;
import com.dlew.net.bean.DLEWABData;
import com.dlew.net.bean.DLEWBaseRsp;
import com.dlew.net.req.ABRes;
import com.dlew.net.util.DLEWNetCallback;
import com.dlew.net.util.DLEWRequestCompleted;
import java.util.LinkedHashMap;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/StrategyNet.class */
public class StrategyNet extends BaseNet {
    private static StrategyNet singleton;
    private static final String API_STRATEGY = StringFog.decrypt("aJBLIoc=\n", "R/0gTvdHo0s=\n");
    private static int retryCount = 0;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/StrategyNet$EvLveE1.class */
    public class EvLveE1 implements DLEWNetCallback<String> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ String f20EvLveE1;
        public final /* synthetic */ DLEWNetCallback v1vLvLE;

        public EvLveE1(String str, DLEWNetCallback dLEWNetCallback) {
            this.f20EvLveE1 = str;
            this.v1vLvLE = dLEWNetCallback;
        }

        @Override // com.dlew.net.util.DLEWNetCallback
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, String str) {
            StrategyNet.this.Fetch(this.f20EvLveE1, this.v1vLvLE);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/StrategyNet$v1vLvLE.class */
    public class v1vLvLE extends DLEWRequestCompleted<DLEWBaseRsp<DLEWABData[]>> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWNetCallback f21EvLveE1;

        public v1vLvLE(DLEWNetCallback dLEWNetCallback) {
            this.f21EvLveE1 = dLEWNetCallback;
        }

        @Override // com.dlew.net.util.DLEWRequestCompleted
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWBaseRsp<DLEWABData[]> dLEWBaseRsp) {
            if (z && dLEWBaseRsp.code == 0) {
                this.f21EvLveE1.OnCompleted(true, dLEWBaseRsp.data);
            } else {
                this.f21EvLveE1.OnCompleted(false, null);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.dlew.net.StrategyNet>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static StrategyNet getInstance() {
        if (singleton == null) {
            ?? r0 = StrategyNet.class;
            synchronized (r0) {
                if (singleton == null) {
                    singleton = new StrategyNet();
                }
                r0 = r0;
            }
        }
        return singleton;
    }

    private StrategyNet() {
        this.url = DLEWConfig.getServerUrl();
        this.needAccessToken = false;
    }

    public void Fetch(String str, DLEWNetCallback<DLEWABData[]> dLEWNetCallback) {
        retryCount++;
        if (TextUtils.isEmpty(PhoneInfoWrapper.getGAID()) && retryCount < 3) {
            PhoneInfoWrapper.Init(new EvLveE1(str, dLEWNetCallback));
            return;
        }
        retryCount = 0;
        ABRes aBRes = new ABRes();
        aBRes.bCodes = str;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(StringFog.decrypt("PkJd\n", "SDIzhJ/5lKI=\n"), Boolean.valueOf(PhoneInfoWrapper.checkvpn()));
        linkedHashMap.put(StringFog.decrypt("LW/djw==\n", "XwCy+w98Ues=\n"), Boolean.valueOf(PhoneInfoWrapper.checkroot()));
        linkedHashMap.put(StringFog.decrypt("EgmJajLu\n", "c27sBFGXbiI=\n"), Boolean.valueOf(PhoneInfoWrapper.checkagency()));
        linkedHashMap.put(StringFog.decrypt("tbdSPLQ=\n", "0dIwSdN2M2A=\n"), Boolean.FALSE);
        linkedHashMap.put(StringFog.decrypt("KHhVJmdSwHssZA==\n", "XxA8UgINrBI=\n"), PhoneInfoWrapper.getGAID());
        linkedHashMap.put(StringFog.decrypt("6UsqZ1eEFPH8SzZqTYkD6P1c\n", "mTlFAyLnYIc=\n"), aBRes.vcode);
        linkedHashMap.put(StringFog.decrypt("S0pRikms+JteSk2HU6HijFZd\n", "Ozg+7jzPjO0=\n"), aBRes.vname);
        linkedHashMap.put(StringFog.decrypt("oxnl/dfHgVKzFOU=\n", "wHGEk7mi7Q0=\n"), PhoneInfoWrapper.getConversionData().channel);
        linkedHashMap.put(StringFog.decrypt("YDWIXZSFKL9qKog=\n", "A0ftPODgd8s=\n"), Long.valueOf(DLEWSDK.getUserInfo().createTimeStamp));
        linkedHashMap.put(StringFog.decrypt("H3hMr/knqowyeUq/+Q==\n", "bR0rxopTz/4=\n"), Integer.valueOf(DLEWSDK.getUserInfo().zhuceDays));
        linkedHashMap.put(StringFog.decrypt("to80WRm38ra2lTM=\n", "1+xAMG/SrdI=\n"), Integer.valueOf(DLEWSDK.getUserInfo().huoyueDays));
        aBRes.more = linkedHashMap;
        aBRes.bb = StringFog.decrypt("Bs84Aw==\n", "dqBLd8MKvTE=\n");
        String str2 = API_STRATEGY;
        aBRes.aa = str2;
        PostAsync(aBRes, str2, new v1vLvLE(dLEWNetCallback));
    }
}