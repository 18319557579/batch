package com.dlew.common;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/BASE64Decoder.class */
public class BASE64Decoder extends CharacterDecoder {
    private static final char[] pem_array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] pem_convert_array = new byte[256];
    byte[] decode_buffer = new byte[4];

    static {
        for (int i = 0; i < 255; i++) {
            pem_convert_array[i] = -1;
        }
        int i2 = 0;
        while (true) {
            int i3 = i2;
            char[] cArr = pem_array;
            if (i3 >= cArr.length) {
                return;
            }
            pem_convert_array[cArr[i2]] = (byte) i2;
            i2++;
        }
    }

    @Override // com.dlew.common.CharacterDecoder
    public int bytesPerAtom() {
        return 4;
    }

    @Override // com.dlew.common.CharacterDecoder
    public int bytesPerLine() {
        return 72;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x013a  */
    @Override // com.dlew.common.CharacterDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void decodeAtom(java.io.PushbackInputStream r10, java.io.OutputStream r11, int r12) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dlew.common.BASE64Decoder.decodeAtom(java.io.PushbackInputStream, java.io.OutputStream, int):void");
    }

    @Override // com.dlew.common.CharacterDecoder
    public int readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        return super.readFully(inputStream, bArr, i, i2);
    }
}