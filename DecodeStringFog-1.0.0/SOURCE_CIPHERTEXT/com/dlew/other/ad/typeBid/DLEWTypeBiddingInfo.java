package com.dlew.other.ad.typeBid;

import com.dlew.StringFog;
import com.dlew.common.BooleanTypeAdapter;
import com.dlew.common.SPUtil;
import com.dlew.lib.DLEWLib;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/typeBid/DLEWTypeBiddingInfo.class */
public class DLEWTypeBiddingInfo {
    static final String INS_REPLACE_COUNT = StringFog.decrypt("wtZ7D5/352zK220Pjv3ibt8=\n", "i5goUM2ytyA=\n");
    static final String OPEN_REPLACE_COUNT = StringFog.decrypt("EX28Osz9XdQSbLoxzOxX0RB5\n", "Xi35dJOvGIQ=\n");

    @SerializedName("open")
    @JsonAdapter(BooleanTypeAdapter.class)
    public boolean open;

    @SerializedName("maxInsReplace")
    public int maxInsReplace;

    @SerializedName("maxOpenReplace")
    public int maxOpenReplace;

    public int getInsReplaceAvailableCount() {
        return this.maxInsReplace - SPUtil.getInt(INS_REPLACE_COUNT + DLEWLib.getUserInfo().huoyueDays, 0);
    }

    public int getOpenReplaceAvailableCount() {
        return this.maxOpenReplace - SPUtil.getInt(OPEN_REPLACE_COUNT + DLEWLib.getUserInfo().huoyueDays, 0);
    }

    public void addInsReplaceCount() {
        StringBuilder sb = new StringBuilder();
        String str = INS_REPLACE_COUNT;
        SPUtil.putInt(str + DLEWLib.getUserInfo().huoyueDays, SPUtil.getInt(sb.append(str).append(DLEWLib.getUserInfo().huoyueDays).toString(), 0) + 1);
    }

    public void addOpenReplaceCount() {
        StringBuilder sb = new StringBuilder();
        String str = OPEN_REPLACE_COUNT;
        SPUtil.putInt(str + DLEWLib.getUserInfo().huoyueDays, SPUtil.getInt(sb.append(str).append(DLEWLib.getUserInfo().huoyueDays).toString(), 0) + 1);
    }
}