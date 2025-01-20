package com.github.megatronking.stringfog;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/github/megatronking/stringfog/StringFogWrapper.class */
public final class StringFogWrapper implements IStringFog {
    private final IStringFog mStringFogImpl;

    public StringFogWrapper(String str) {
        try {
            this.mStringFogImpl = (IStringFog) Class.forName(str).newInstance();
        } catch (ClassNotFoundException unused) {
            throw new IllegalArgumentException("Stringfog implementation class not found: " + str);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Stringfog implementation class access failed: " + e.getMessage());
        } catch (InstantiationException e2) {
            throw new IllegalArgumentException("Stringfog implementation class new instance failed: " + e2.getMessage());
        }
    }

    @Override // com.github.megatronking.stringfog.IStringFog
    public byte[] encrypt(String str, byte[] bArr) {
        IStringFog iStringFog = this.mStringFogImpl;
        return iStringFog == null ? str.getBytes() : iStringFog.encrypt(str, bArr);
    }

    @Override // com.github.megatronking.stringfog.IStringFog
    public String decrypt(byte[] bArr, byte[] bArr2) {
        String decrypt;
        IStringFog iStringFog = this.mStringFogImpl;
        if (iStringFog == null) {
            decrypt = r0;
            String str = new String(bArr);
        } else {
            decrypt = iStringFog.decrypt(bArr, bArr2);
        }
        return decrypt;
    }

    @Override // com.github.megatronking.stringfog.IStringFog
    public boolean shouldFog(String str) {
        IStringFog iStringFog = this.mStringFogImpl;
        return iStringFog != null && iStringFog.shouldFog(str);
    }
}