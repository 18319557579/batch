package com.dlew.common;

import com.dlew.StringFog;
import com.dlew.lib.DLEWSDK;
import com.dlew.log.bean.Configure;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/ServerTimeUtil.class */
public class ServerTimeUtil {
    private static long serverTimeStamp;
    private static long realtimeSinceStartup;
    private static int currentDay = 0;

    public static void SetServerTime(long j) {
        if (j == 0) {
            j = System.currentTimeMillis();
        }
        serverTimeStamp = j;
        realtimeSinceStartup = System.currentTimeMillis();
    }

    public static void setServerTime(String str) {
        int rawOffset = TimeZone.getDefault().getRawOffset();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StringFog.decrypt("xwvG0zriRuXaFp/iX5VmpYQBzA==\n", "vnK/qhevC8g=\n"));
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(StringFog.decrypt("6dgbnA==\n", "rpVPvKy0AO8=\n") + ((rawOffset / 60) / 60)));
        try {
            serverTimeStamp = simpleDateFormat.parse(str).getTime();
            realtimeSinceStartup = System.currentTimeMillis();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static long getRealTimeStamp() {
        return (serverTimeStamp + System.currentTimeMillis()) - realtimeSinceStartup;
    }

    public static int getRealRegisterDays() {
        if (DLEWSDK.getUserInfo() == null) {
            return 0;
        }
        return !isToday() ? DLEWSDK.getUserInfo().zhuceDays + 1 : DLEWSDK.getUserInfo().zhuceDays;
    }

    public static int getRealActiveDays() {
        if (DLEWSDK.getUserInfo() == null) {
            return 0;
        }
        return !isToday() ? DLEWSDK.getUserInfo().huoyueDays + 1 : DLEWSDK.getUserInfo().huoyueDays;
    }

    public static boolean isToday() {
        return isToday(getRealTimeStamp());
    }

    private static long getWeeOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    private static boolean isToday(long j) {
        long weeOfToday = getWeeOfToday();
        return j >= weeOfToday && j < weeOfToday + Configure.LIMIT_DATE_NUM;
    }
}