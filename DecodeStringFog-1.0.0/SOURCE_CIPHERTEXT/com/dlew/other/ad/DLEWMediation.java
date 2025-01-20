package com.dlew.other.ad;

import android.text.TextUtils;
import com.dlew.StringFog;
import com.dlew.net.StrategyNet;
import com.dlew.net.bean.DLEWABData;
import com.dlew.net.bean.DLEWABField;
import com.dlew.net.util.DLEWNetCallback;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWMediation.class */
public class DLEWMediation {
    private static List<DLEWLimitConfig> m_mediations;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWMediation$EvLveE1.class */
    public class EvLveE1 implements DLEWNetCallback<DLEWABData[]> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWNetCallback f45EvLveE1;

        public EvLveE1(DLEWNetCallback dLEWNetCallback) {
            this.f45EvLveE1 = dLEWNetCallback;
        }

        @Override // com.dlew.net.util.DLEWNetCallback
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWABData[] dLEWABDataArr) {
            if (z) {
                for (DLEWABData dLEWABData : dLEWABDataArr) {
                    for (DLEWABField dLEWABField : dLEWABData.fields) {
                        if (dLEWABField.fkey.equals(StringFog.decrypt("fWPuqRl8LWJe\n", "MAaKwHgIRA0=\n")) && !TextUtils.isEmpty(dLEWABField.fvalue)) {
                            Gson gson = new Gson();
                            List unused = DLEWMediation.m_mediations = new ArrayList(Arrays.asList((DLEWLimitConfig[]) gson.fromJson(((JsonObject) gson.fromJson(dLEWABField.fvalue, JsonObject.class)).getAsJsonArray(StringFog.decrypt("1y9BZRIiLw==\n", "tEAvA3tFXFg=\n")).toString(), DLEWLimitConfig[].class)));
                        }
                    }
                }
            }
            DLEWNetCallback dLEWNetCallback = this.f45EvLveE1;
            if (dLEWNetCallback != null) {
                dLEWNetCallback.OnCompleted(z, DLEWMediation.m_mediations);
            }
        }
    }

    public static void fetchMediationConfigs(DLEWNetCallback<List<DLEWLimitConfig>> dLEWNetCallback) {
        StrategyNet.getInstance().Fetch(StringFog.decrypt("em5Q/gzK7VBZ\n", "Nws0l22+hD8=\n"), new EvLveE1(dLEWNetCallback));
    }
}