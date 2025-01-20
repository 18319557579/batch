package com.dlew.log;

import android.content.Context;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.dlew.StringFog;
import com.dlew.common.NetUtils;
import com.dlew.lib.DLEWSDK;
import com.dlew.log.bean.BIEvent;
import com.dlew.log.bean.BIUser;
import com.dlew.log.db.AnalysisDBHelper;
import com.dlew.log.db.DataBean;
import com.dlew.net.StatNet;
import com.dlew.net.req.LogRes;
import com.dlew.net.util.DLEWNetCallback;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/EventTrackManager.class */
public class EventTrackManager {
    private static EventTrackManager singleton;
    private NetworkConnectChangedReceiver mReceiver;
    private Timer _timer;
    private final String table_info = StringFog.decrypt("BLACchLHj90Eui5YAs2Q7wm1AkguwYzWBItA\n", "a9RxLXGo4rA=\n");
    private final String table_event = StringFog.decrypt("jIYxSFwp94yMjB1iTCPovoaUJ3lLGas=\n", "4+JCFz9GmuE=\n");
    private final String event_protocol = StringFog.decrypt("Q/pZRg==\n", "esp0c9EkyOA=\n");
    private final String LINE_DELIMITER = "\u0002";
    final String app_open = StringFog.decrypt("O+j3fRc6G94=\n", "WpiHInhKfrA=\n");
    final String app_front = StringFog.decrypt("JEHMmlQUa7Qx\n", "RTG8xTJmBNo=\n");
    final String app_exit = StringFog.decrypt("FQqsA2WqV+E=\n", "dHrcXADSPpU=\n");
    public final String key_event = StringFog.decrypt("W0kxGH9zMj1qVg==\n", "BCJUYSAWRFg=\n");
    public String strategyId = StringFog.decrypt("EA==\n", "IQ2LDC3AYkE=\n");
    private int perTime = 30000;
    private boolean _baseRetry = true;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/EventTrackManager$EeleE.class */
    public class EeleE implements DLEWNetCallback<Object> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ AnalysisDBHelper f8EvLveE1;
        public final /* synthetic */ List v1vLvLE;

        public EeleE(AnalysisDBHelper analysisDBHelper, List list) {
            this.f8EvLveE1 = analysisDBHelper;
            this.v1vLvLE = list;
        }

        @Override // com.dlew.net.util.DLEWNetCallback
        public void OnCompleted(boolean z, Object obj) {
            if (z) {
                return;
            }
            this.f8EvLveE1.insert(StringFog.decrypt("E1JV2w==\n", "KmJ47hwubBc=\n"), this.v1vLvLE);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/EventTrackManager$EvLveE1.class */
    public class EvLveE1 extends TimerTask {
        public EvLveE1() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            EventTrackManager.this.SendBaseEvent();
            EventTrackManager.this.UploadUserEvents();
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/EventTrackManager$evleeEelE.class */
    public class evleeEelE implements DLEWNetCallback<Object> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ BIEvent f10EvLveE1;

        public evleeEelE(BIEvent bIEvent) {
            this.f10EvLveE1 = bIEvent;
        }

        @Override // com.dlew.net.util.DLEWNetCallback
        public void OnCompleted(boolean z, Object obj) {
            if (z) {
                return;
            }
            EventTrackManager.this.PackageUserEvent(this.f10EvLveE1);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/EventTrackManager$v1vLvLE.class */
    public class v1vLvLE implements DLEWNetCallback<Object> {
        public v1vLvLE() {
        }

        @Override // com.dlew.net.util.DLEWNetCallback
        public void OnCompleted(boolean z, Object obj) {
            if (z) {
                EventTrackManager.this._baseRetry = false;
            } else {
                EventTrackManager.this._baseRetry = true;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.dlew.log.EventTrackManager>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static EventTrackManager getInstance() {
        if (singleton == null) {
            ?? r0 = EventTrackManager.class;
            synchronized (r0) {
                if (singleton == null) {
                    singleton = new EventTrackManager();
                }
                r0 = r0;
            }
        }
        return singleton;
    }

    private EventTrackManager() {
        new AnalysisDBHelper().clear();
        initReceive(DLEWSDK.getApplication());
    }

    private void initReceive(Context context) {
        this.mReceiver = new NetworkConnectChangedReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(StringFog.decrypt("0Rnsk8T5cI3eEvzPyP96zZ40x6/l1Vf3+SHBtfLPV+vxOc+k\n", "sHeI4auQFKM=\n"));
        context.registerReceiver(this.mReceiver, intentFilter);
    }

    private void PackageUserEvent(String str, String str2, int i, int i2, String str3, String str4) {
        PackageUserEvent(new BIEvent(str, str2, i, i2, str3, str4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void UploadUserEvents() {
        if (NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            AnalysisDBHelper analysisDBHelper = new AnalysisDBHelper();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(analysisDBHelper.queryVailData(this.event_protocol));
            List<List<DataBean>> groupListByQuantity = groupListByQuantity(arrayList);
            if (groupListByQuantity.size() > 0) {
                XLog.d(StringFog.decrypt("liDh1qN3vETQfuOp61/jBfYUmoaHMuZMSw==\n", "cZt+Pg3WWuw=\n"));
            }
            for (List<DataBean> list : groupListByQuantity) {
                analysisDBHelper.remove(toListStr(list));
                StringBuilder sb = new StringBuilder();
                Iterator<DataBean> it = list.iterator();
                while (it.hasNext()) {
                    sb.append(it.next().getData());
                    sb.append("\u0002");
                }
                LogRes logRes = new LogRes();
                logRes.biao = this.table_event;
                logRes.banben = 13;
                logRes.event = sb.toString();
                StatNet.getInstance().SendEvent(logRes, new EeleE(analysisDBHelper, list));
            }
        }
    }

    private static List<String> toListStr(List<DataBean> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<DataBean> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getRequestCode());
        }
        return arrayList;
    }

    private static List<List<DataBean>> groupListByQuantity(List<DataBean> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null || list.size() == 0) {
            return arrayList;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                return arrayList;
            }
            int i3 = i2 + 100;
            arrayList.add(list.subList(i2, i3 > list.size() ? list.size() : i3));
            i = i3;
        }
    }

    public void StartTimer() {
        if (this._timer != null) {
            return;
        }
        XLog.d(StringFog.decrypt("jI3sfK39zD3K0+4D5uCqcMy9mzqiur0jjq/brg==\n", "azZzlANcKpU=\n"));
        Timer timer = new Timer();
        this._timer = timer;
        EvLveE1 evLveE1 = new EvLveE1();
        int i = this.perTime;
        timer.schedule(evLveE1, i, i);
    }

    public void StopTimer() {
        if (this._timer != null) {
            XLog.d(StringFog.decrypt("nPKcJOzslrfarJ5bp8zs+dbr62Ljq+epntCr9g==\n", "e0kDzEJNcB8=\n"));
            this._timer.cancel();
            this._timer = null;
        }
    }

    public void SendAppOpen() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(StringFog.decrypt("/dVO+oQsnUXm1A==\n", "j7A/j+Ff6Ro=\n"), UUID.randomUUID().toString());
        SendUserEvent(this.app_open, StringFog.decrypt("KQ==\n", "HVzWhGsCKNo=\n"), 4, 1, BuildConfig.VERSION_NAME, (Object) jsonObject, true);
        StartTimer();
    }

    public void SendAppExit(String str) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(StringFog.decrypt("SMBxZcd1UNJTwQ==\n", "OqUAEKIGJI0=\n"), UUID.randomUUID().toString());
        jsonObject.addProperty(StringFog.decrypt("Ij4IDFWveZQjBAwESr4=\n", "Rlt4bSfbDOY=\n"), Long.valueOf(System.currentTimeMillis()));
        jsonObject.addProperty(StringFog.decrypt("AI+UhroX6UwIhA==\n", "ZeHg9MNInSU=\n"), str);
        SendUserEvent(this.app_exit, StringFog.decrypt("aQ==\n", "XaJLj2yMNsQ=\n"), 4, 1, BuildConfig.VERSION_NAME, jsonObject, !DLEWSDK.isTrackThrottle());
        StopTimer();
    }

    public void SendAppFront() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(StringFog.decrypt("GVWibGruecMCVA==\n", "azDTGQ+dDZw=\n"), UUID.randomUUID().toString());
        SendUserEvent(this.app_front, StringFog.decrypt("Ow==\n", "D+jVtWabYKw=\n"), 4, 1, BuildConfig.VERSION_NAME, jsonObject, !DLEWSDK.isTrackThrottle());
        StartTimer();
    }

    public void SendBaseEvent() {
        if (NetUtils.isNetworkOK(DLEWSDK.getApplication()) && this._baseRetry) {
            BIUser bIUser = new BIUser();
            LogRes logRes = new LogRes();
            logRes.biao = this.table_info;
            logRes.banben = 15;
            logRes.event = bIUser.getFormatString();
            XLog.e(StringFog.decrypt("qQYiR0cM/Yr3WQkhKRa588wR9w==\n", "Tb6ooc2pGBU=\n") + bIUser.google_id + StringFog.decrypt("1w==\n", "iD1ESTk33k0=\n") + bIUser.ivm);
            StatNet.getInstance().SendEvent(logRes, new v1vLvLE());
        }
    }

    public void SendUserEvent(String str, String str2, int i, int i2, String str3, Object obj, boolean z) {
        SendUserEvent(str, str2, i, i2, str3, new Gson().toJson(obj), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void PackageUserEvent(BIEvent bIEvent) {
        AnalysisDBHelper analysisDBHelper = new AnalysisDBHelper();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DataBean(bIEvent.request_id, bIEvent.getFormatString(), System.currentTimeMillis(), 0));
        analysisDBHelper.insert(this.event_protocol, arrayList);
    }

    public void SendUserEvent(String str, String str2, int i, int i2, String str3, String str4, boolean z) {
        String obj;
        if (TextUtils.isEmpty(str4)) {
            obj = StringFog.decrypt("qKzq9Mn2kA==\n", "046cna3Uqqs=\n") + this.strategyId + StringFog.decrypt("Mw==\n", "TgahJI3I9Dc=\n");
        } else {
            JsonObject jsonObject = (JsonObject) new Gson().fromJson(str4, JsonObject.class);
            jsonObject.addProperty(StringFog.decrypt("kD13\n", "5lQTftV+7kU=\n"), this.strategyId);
            obj = jsonObject.toString();
        }
        XLog.e(StringFog.decrypt("fOLhsxKc4A==\n", "mmtyVJAl2p4=\n") + str + StringFog.decrypt("KSI=\n", "dn3lzNBHgKI=\n") + obj);
        if (!NetUtils.isNetworkOK(DLEWSDK.getApplication())) {
            PackageUserEvent(str, str2, i, i2, str3, obj);
            return;
        }
        if (!str.equals(this.app_open) && !str.equals(this.app_exit) && !str.equals(this.app_front) && !z) {
            PackageUserEvent(str, str2, i, i2, str3, obj);
            return;
        }
        BIEvent bIEvent = new BIEvent(str, str2, i, i2, str3, obj);
        LogRes logRes = new LogRes();
        logRes.biao = this.table_event;
        logRes.banben = 13;
        logRes.event = bIEvent.getFormatString();
        StatNet.getInstance().SendEvent(logRes, new evleeEelE(bIEvent));
    }
}