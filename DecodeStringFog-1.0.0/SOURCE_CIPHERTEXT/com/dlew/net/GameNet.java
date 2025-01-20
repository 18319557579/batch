package com.dlew.net;

import com.dlew.StringFog;
import com.dlew.hardware.PhoneInfoWrapper;
import com.dlew.lib.DLEWConfig;
import com.dlew.net.bean.DLEWBaseRsp;
import com.dlew.net.bean.DLEWGameConfigSet;
import com.dlew.net.bean.DLEWSettingInfo;
import com.dlew.net.req.SettingRes;
import com.dlew.net.util.DLEWNetCallback;
import com.dlew.net.util.DLEWRequestCompleted;
import com.dlew.other.af.AFWrapper;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/GameNet.class */
public class GameNet extends BaseNet {
    private static GameNet singleton;
    protected static final String API_GAME_CONFIG = StringFog.decrypt("uaJJszY=\n", "ltgo2F2L+18=\n");

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/GameNet$EvLveE1.class */
    public class EvLveE1 extends DLEWRequestCompleted<DLEWBaseRsp<DLEWGameConfigSet>> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWNetCallback f18EvLveE1;

        public EvLveE1(DLEWNetCallback dLEWNetCallback) {
            this.f18EvLveE1 = dLEWNetCallback;
        }

        @Override // com.dlew.net.util.DLEWRequestCompleted
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWBaseRsp<DLEWGameConfigSet> dLEWBaseRsp) {
            if (z && dLEWBaseRsp.code == 0) {
                this.f18EvLveE1.OnCompleted(true, dLEWBaseRsp.data.list);
            } else {
                this.f18EvLveE1.OnCompleted(false, null);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.dlew.net.GameNet>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static GameNet getInstance() {
        if (singleton == null) {
            ?? r0 = GameNet.class;
            synchronized (r0) {
                if (singleton == null) {
                    singleton = new GameNet();
                }
                r0 = r0;
            }
        }
        return singleton;
    }

    private GameNet() {
        this.url = DLEWConfig.getServerUrl();
        this.needAccessToken = true;
    }

    public void FetchConfig(String str, DLEWNetCallback<DLEWSettingInfo[]> dLEWNetCallback) {
        SettingRes settingRes = new SettingRes();
        settingRes.ids = str;
        settingRes.channel = PhoneInfoWrapper.getConversionData().channel;
        settingRes.site_id = AFWrapper.getAFSiteId();
        settingRes.bb = StringFog.decrypt("em3weQ==\n", "CgKDDdKD3hI=\n");
        String str2 = API_GAME_CONFIG;
        settingRes.aa = str2;
        PostAsync(settingRes, str2, new EvLveE1(dLEWNetCallback));
    }
}