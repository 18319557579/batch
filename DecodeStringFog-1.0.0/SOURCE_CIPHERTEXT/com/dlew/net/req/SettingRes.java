package com.dlew.net.req;

import com.dlew.lib.DLEWSDK;
import com.dlew.net.bean.DLEWRootReq;
import com.google.gson.annotations.SerializedName;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/req/SettingRes.class */
public class SettingRes extends DLEWRootReq {

    @SerializedName("pe")
    public String vname;

    @SerializedName("qo")
    public String channel;

    @SerializedName("bs")
    public String ids;

    @SerializedName("site_id")
    public String site_id;

    public SettingRes() {
        try {
            this.vname = DLEWSDK.getApplication().getPackageManager().getPackageInfo(DLEWSDK.getApplication().getPackageName(), 0).versionName;
        } catch (Exception unused) {
        }
    }
}