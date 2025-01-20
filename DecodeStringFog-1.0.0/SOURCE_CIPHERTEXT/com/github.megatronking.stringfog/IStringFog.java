package com.github.megatronking.stringfog;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/github/megatronking/stringfog/IStringFog.class */
public interface IStringFog {
    byte[] encrypt(String str, byte[] bArr);

    String decrypt(byte[] bArr, byte[] bArr2);

    boolean shouldFog(String str);
}