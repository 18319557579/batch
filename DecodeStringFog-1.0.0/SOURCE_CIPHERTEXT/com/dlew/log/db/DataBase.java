package com.dlew.log.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.dlew.StringFog;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/db/DataBase.class */
public class DataBase extends SQLiteOpenHelper {
    private static volatile DataBase sDataBase;
    static final String ANALYSIS_TABLE_NAME = StringFog.decrypt("FiXm73zg9V4dOO7DQur1RgUp5sN4\n", "ZEuHsB2OlDI=\n");
    private static final String DB_NAME = StringFog.decrypt("nEMVfrr8D8uXXh1S9fYM\n", "7i10IduSbqc=\n");
    private static final int VERSION = 1;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/db/DataBase$EvLveE1.class */
    public static class EvLveE1 {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public static final String f13EvLveE1 = StringFog.decrypt("hExEVTJyQA==\n", "4C0wMG0bJAk=\n");
        public static final String v1vLvLE = StringFog.decrypt("pZAcVintM0GKgRxGIw==\n", "1eJzIkaOXC0=\n");
        public static final String evleeEelE = StringFog.decrypt("KU/NNg==\n", "TS65VyeTaI0=\n");
        public static final String EeleE = StringFog.decrypt("OoktwEh6uLIl\n", "SOxZsjEl1sc=\n");
        public static final String LvL11LLEl = StringFog.decrypt("MFM5BkTD6p8+Vw==\n", "UzJabiGcnvY=\n");
        public static final String EvLeE11LLE = StringFog.decrypt("g8pknkp9K2+f12U=\n", "9roA/z4YdBs=\n");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.dlew.log.db.DataBase>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static DataBase getInstance(Context context) {
        if (sDataBase == null) {
            ?? r0 = DataBase.class;
            synchronized (r0) {
                if (sDataBase == null) {
                    sDataBase = new DataBase(context, DB_NAME, null, 1);
                }
                r0 = r0;
            }
        }
        return sDataBase;
    }

    private DataBase(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    private void createAnalysisTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(StringFog.decrypt("ToOIvEJw1btMk4G4Nnyzz0Oemd1Tbby8WYLtj3hUqo5jsKGEZVyGsGmwuZx0VIaKLfmSlHIVnIF5\ntKqYZBWlvUScjK9PFb6qVPGMqEJ6vKFOg4iwU3uhz0Oemd1YYLmjIbWsiXNqnIsthYilQhWgoUSA\nmLg6RYeAeb6uknpqloBptO2pU22hw2mwuZw2YbC3Wf2/mGJHjLBjpKDdX3uhqkqUn9FjRZGOebSS\niX9YkM9En5m4UXCnw26wrpVzaoGGYLTttFhhsKhIg+Q=\n", "DdHN/RY19e8=\n"));
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        createAnalysisTable(sQLiteDatabase);
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2) {
        return query(str, strArr, str2, strArr2, null);
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        try {
            return getReadableDatabase().query(str, strArr, str2, strArr2, null, null, str3);
        } catch (Exception unused) {
            return null;
        }
    }
}