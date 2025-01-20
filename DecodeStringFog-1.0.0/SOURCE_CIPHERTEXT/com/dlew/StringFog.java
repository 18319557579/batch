package com.dlew;

import com.github.megatronking.stringfog.Base64;
import com.github.megatronking.stringfog.xor.StringFogImpl;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/StringFog.class */
public final class StringFog {
    private static final StringFogImpl IMPL = new StringFogImpl();

    public static String decrypt(String str, String str2) {
        return IMPL.decrypt(Base64.decode(str, 0), Base64.decode(str2, 0));
    }
}