package com.dlew.net;

import android.text.TextUtils;
import com.dlew.StringFog;
import com.dlew.lib.DLEWConfig;
import com.dlew.net.bean.DLEWBaseRsp;
import com.dlew.net.req.LogRes;
import com.dlew.net.util.DLEWNetCallback;
import com.dlew.net.util.DLEWRequestCompleted;
import com.elvishew.xlog.XLog;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/StatNet.class */
public class StatNet extends BaseNet {
    private static StatNet singleton;
    static final String API_STAT = StringFog.decrypt("mFK9Lvw=\n", "tyjcS5obnTE=\n");

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/StatNet$EvLveE1.class */
    public class EvLveE1 extends DLEWRequestCompleted<DLEWBaseRsp<Object>> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWNetCallback f19EvLveE1;

        public EvLveE1(DLEWNetCallback dLEWNetCallback) {
            this.f19EvLveE1 = dLEWNetCallback;
        }

        @Override // com.dlew.net.util.DLEWRequestCompleted
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWBaseRsp<Object> dLEWBaseRsp) {
            this.f19EvLveE1.OnCompleted(z, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.dlew.net.StatNet>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static StatNet getInstance() {
        if (singleton == null) {
            ?? r0 = StatNet.class;
            synchronized (r0) {
                if (singleton == null) {
                    singleton = new StatNet();
                }
                r0 = r0;
            }
        }
        return singleton;
    }

    private StatNet() {
        if (TextUtils.isEmpty(DLEWConfig.getStatUrl())) {
            this.url = DLEWConfig.getServerUrl();
        } else {
            this.url = DLEWConfig.getStatUrl();
        }
        XLog.d(StringFog.decrypt("AA7BuyUvgVg=\n", "U3qgz2tK9WI=\n") + this.url);
        this.needAccessToken = true;
    }

    public void SendEvent(LogRes logRes, DLEWNetCallback<Object> dLEWNetCallback) {
        logRes.bb = StringFog.decrypt("IQJJLw==\n", "UW06W29LX7U=\n");
        String str = API_STAT;
        logRes.aa = str;
        PostAsync(logRes, str, new EvLveE1(dLEWNetCallback));
    }
}