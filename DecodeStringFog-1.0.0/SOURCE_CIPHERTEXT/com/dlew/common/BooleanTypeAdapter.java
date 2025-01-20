package com.dlew.common;

import android.text.TextUtils;
import com.dlew.StringFog;
import com.elvishew.xlog.LogLevel;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/BooleanTypeAdapter.class */
public class BooleanTypeAdapter extends TypeAdapter<Boolean> {

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/BooleanTypeAdapter$EvLveE1.class */
    public static /* synthetic */ class EvLveE1 {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public static final /* synthetic */ int[] f0EvLveE1;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f0EvLveE1 = iArr;
            try {
                iArr[JsonToken.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f0EvLveE1[JsonToken.NULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f0EvLveE1[JsonToken.NUMBER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f0EvLveE1[JsonToken.STRING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static boolean toBoolean(String str) {
        return !TextUtils.isEmpty(str) && (str.equalsIgnoreCase(StringFog.decrypt("B5wHkA==\n", "c+5y9ZVTNJg=\n")) || !str.equals(StringFog.decrypt("Kw==\n", "G255fHkxz/U=\n")));
    }

    public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
        if (bool == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(bool);
        }
    }

    /* renamed from: read, reason: merged with bridge method [inline-methods] */
    public Boolean m3read(JsonReader jsonReader) throws IOException {
        JsonToken peek = jsonReader.peek();
        switch (EvLveE1.f0EvLveE1[peek.ordinal()]) {
            case 1:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case 2:
                jsonReader.nextNull();
                return null;
            case LogLevel.DEBUG /* 3 */:
                return Boolean.valueOf(jsonReader.nextInt() != 0);
            case 4:
                return Boolean.valueOf(toBoolean(jsonReader.nextString()));
            default:
                throw new JsonParseException(StringFog.decrypt("i7vGzI44kJTugfnmoQm0vu6sxImjGbiyi5GWy5g41YevsJY=\n", "zsO2qe1M9fA=\n") + peek);
        }
    }
}