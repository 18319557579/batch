package com.dlew.log.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.dlew.StringFog;
import com.dlew.lib.DLEWSDK;
import com.dlew.log.bean.Configure;
import java.util.ArrayList;
import java.util.List;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/db/AnalysisDBHelper.class */
public class AnalysisDBHelper {
    private Context mContext = DLEWSDK.getApplication();
    private String tableName = StringFog.decrypt("CLwqlU/wqXgDoSK5cfqpYBuwKrlL\n", "etJLyi6eyBQ=\n");

    private String getDBWhereClause(List<String> list, String str) {
        StringBuilder sb = r1;
        StringBuilder sb2 = new StringBuilder();
        if (list != null && list.size() > 0) {
            sb.append(StringFog.decrypt("Xda49rrPvA==\n", "ObfMk+Wm2Bs=\n")).append(StringFog.decrypt("xGUib10=\n", "5AxMT3Xclo4=\n"));
            for (String str2 : list) {
                if (str == null) {
                    sb.append(StringFog.decrypt("Tw==\n", "aDB7QkBFHkw=\n")).append(str2).append(StringFog.decrypt("gQ==\n", "prQ8d7pRvC4=\n")).append(StringFog.decrypt("SA==\n", "ZI9h0yVHIFw=\n"));
                } else {
                    sb.append(str).append(StringFog.decrypt("Kg==\n", "Bl+uVWCTqZI=\n"));
                }
            }
            sb = sb.delete(sb.length() - 1, sb.length()).append(StringFog.decrypt("Zg==\n", "T05yzYuu0MA=\n"));
        }
        return sb.toString();
    }

    private List<List<String>> groupListByQuantity(List<String> list) {
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
            int i3 = i2 + 300;
            arrayList.add(list.subList(i2, i3 > list.size() ? list.size() : i3));
            i = i3;
        }
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.dlew.log.db.AnalysisDBHelper] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v15, types: [boolean] */
    public synchronized List<DataBean> queryVailData(String str) {
        Cursor query;
        ?? r0 = this;
        Cursor cursor = null;
        ArrayList arrayList = new ArrayList();
        try {
            DataBase dataBase = DataBase.getInstance(r0.mContext);
            String str2 = this.tableName;
            String[] strArr = new String[4];
            strArr[0] = StringFog.decrypt("BTqF0EyOag==\n", "YVvxtRPnDs8=\n");
            strArr[1] = StringFog.decrypt("LouyWw==\n", "SurGOhunSdY=\n");
            strArr[2] = StringFog.decrypt("dYVT1iR+RfF7gQ==\n", "FuQwvkEhMZg=\n");
            strArr[3] = StringFog.decrypt("CTMouzUI8L0W\n", "e1ZcyUxXnsg=\n");
            String decrypt = StringFog.decrypt("eRk2ZELuz/d3HXUyGpGEvnsWMSxSwd//bh0KeE7c3r4mWGosRt/fvmoKOnhI0tTyRRs6aEKRhr4l\nWDRiQ5HJ+24KLFNJxNa+Jlhq\n", "GnhVDCexu54=\n");
            String[] strArr2 = new String[4];
            strArr2[0] = String.valueOf(System.currentTimeMillis() - Configure.LIMIT_DATE_NUM);
            strArr2[1] = String.valueOf(System.currentTimeMillis() - 1000);
            strArr2[2] = str;
            strArr2[3] = String.valueOf(5L);
            query = dataBase.query(str2, strArr, decrypt, strArr2);
            cursor = query;
        } catch (Exception unused) {
        } catch (Throwable th) {
            th.closeCursor(cursor);
            throw r0;
        }
        if (query == null || cursor.getCount() <= 0) {
            closeCursor(cursor);
            return arrayList;
        }
        cursor.moveToFirst();
        do {
            String string = cursor.getString(cursor.getColumnIndex(StringFog.decrypt("05A4obznfQ==\n", "t/FMxOOOGXY=\n")));
            String string2 = cursor.getString(cursor.getColumnIndex(StringFog.decrypt("gEU/Nw==\n", "5CRLVhUs93g=\n")));
            long j = cursor.getLong(cursor.getColumnIndex(StringFog.decrypt("onhFJ/xrbeisfA==\n", "wRkmT5k0GYE=\n")));
            int i = cursor.getInt(cursor.getColumnIndex(StringFog.decrypt("1CXSTw1nLyLL\n", "pkCmPXQ4QVc=\n")));
            r0 = TextUtils.isEmpty(string2);
            if (r0 == 0) {
                arrayList.add(new DataBean(string, string2, j, i));
            }
        } while (cursor.moveToNext());
        closeCursor(cursor);
        return arrayList;
    }

    public synchronized void insert(String str, List<DataBean> list) {
        SQLiteDatabase writableDatabase = DataBase.getInstance(this.mContext).getWritableDatabase();
        writableDatabase.beginTransaction();
        for (DataBean dataBean : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(StringFog.decrypt("si5nGH7XXA==\n", "1k8TfSG+OFY=\n"), dataBean.getRequestCode());
            contentValues.put(StringFog.decrypt("qyCjeD9CUsGEMaNoNQ==\n", "21LMDFAhPa0=\n"), str);
            contentValues.put(StringFog.decrypt("kc6wrQ==\n", "9a/EzGrZILA=\n"), dataBean.getData());
            contentValues.put(StringFog.decrypt("TtKue4+/P5pA1g==\n", "LbPNE+rgS/M=\n"), Long.valueOf(dataBean.getFirstCacheTime()));
            contentValues.put(StringFog.decrypt("uzpg9EVWwqinJ2E=\n", "zkoElTEzndw=\n"), Long.valueOf(System.currentTimeMillis()));
            contentValues.put(StringFog.decrypt("mIZsmY4TH+iH\n", "6uMY6/dMcZ0=\n"), Integer.valueOf(dataBean.getRetryNum() + 1));
            writableDatabase.insert(this.tableName, StringFog.decrypt("TlqdzcgS2w==\n", "KjvpqJd7v0Q=\n"), contentValues);
        }
        writableDatabase.setTransactionSuccessful();
        writableDatabase.endTransaction();
    }

    public synchronized void remove(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        List<List<String>> groupListByQuantity = groupListByQuantity(list);
        SQLiteDatabase readableDatabase = DataBase.getInstance(this.mContext).getReadableDatabase();
        readableDatabase.beginTransaction();
        for (List<String> list2 : groupListByQuantity) {
            try {
                readableDatabase.delete(this.tableName, getDBWhereClause(list2, StringFog.decrypt("Ag==\n", "Pa4OTNA9s9M=\n")), (String[]) list2.toArray(new String[0]));
            } catch (Exception unused) {
            }
        }
        readableDatabase.setTransactionSuccessful();
        readableDatabase.endTransaction();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.dlew.log.db.AnalysisDBHelper] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v13, types: [boolean] */
    public synchronized void clear() {
        Cursor query;
        ?? r0 = this;
        Cursor cursor = null;
        ArrayList arrayList = new ArrayList();
        try {
            DataBase dataBase = DataBase.getInstance(r0.mContext);
            String str = this.tableName;
            String[] strArr = new String[1];
            strArr[0] = StringFog.decrypt("vcMiWktyoQ==\n", "2aJWPxQbxc4=\n");
            String decrypt = StringFog.decrypt("fmC+eM5r9WJwZP0siwuhZG8hr3XfRvhUc3SwMJUJoTQ=\n", "HQHdEKs0gQs=\n");
            String[] strArr2 = new String[2];
            strArr2[0] = String.valueOf(System.currentTimeMillis() - Configure.LIMIT_DATE_NUM);
            strArr2[1] = String.valueOf(5L);
            query = dataBase.query(str, strArr, decrypt, strArr2);
            cursor = query;
        } catch (Exception unused) {
        } catch (Throwable th) {
            th.closeCursor(cursor);
            throw r0;
        }
        if (query == null || cursor.getCount() <= 0) {
            closeCursor(cursor);
            return;
        }
        cursor.moveToFirst();
        do {
            arrayList.add(cursor.getString(cursor.getColumnIndex(StringFog.decrypt("+mJ8vpnAbQ==\n", "ngMI28apCZQ=\n"))));
            r0 = cursor.moveToNext();
        } while (r0 != 0);
        closeCursor(cursor);
        remove(arrayList);
    }
}