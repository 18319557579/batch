package com.dlew.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/CharacterDecoder.class */
public abstract class CharacterDecoder {
    public abstract int bytesPerAtom();

    public abstract int bytesPerLine();

    public void decodeBufferPrefix(PushbackInputStream pushbackInputStream, OutputStream outputStream) throws IOException {
    }

    public void decodeBufferSuffix(PushbackInputStream pushbackInputStream, OutputStream outputStream) throws IOException {
    }

    public int decodeLinePrefix(PushbackInputStream pushbackInputStream, OutputStream outputStream) throws IOException {
        return bytesPerLine();
    }

    public void decodeLineSuffix(PushbackInputStream pushbackInputStream, OutputStream outputStream) throws IOException {
    }

    public void decodeAtom(PushbackInputStream pushbackInputStream, OutputStream outputStream, int i) throws IOException {
        throw new CEStreamExhausted();
    }

    public int readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int read = inputStream.read();
            if (read == -1) {
                if (i3 == 0) {
                    i3 = -1;
                }
                return i3;
            }
            bArr[i3 + i] = (byte) read;
            i3++;
        }
        return i2;
    }

    public void decodeBuffer(InputStream inputStream, OutputStream outputStream) throws IOException {
        int i;
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
        decodeBufferPrefix(pushbackInputStream, outputStream);
        while (true) {
            try {
                int decodeLinePrefix = decodeLinePrefix(pushbackInputStream, outputStream);
                int i2 = 0;
                while (true) {
                    i = i2;
                    if (i + bytesPerAtom() >= decodeLinePrefix) {
                        break;
                    }
                    decodeAtom(pushbackInputStream, outputStream, bytesPerAtom());
                    bytesPerAtom();
                    i2 = i + bytesPerAtom();
                }
                if (i + bytesPerAtom() == decodeLinePrefix) {
                    decodeAtom(pushbackInputStream, outputStream, bytesPerAtom());
                    bytesPerAtom();
                } else {
                    decodeAtom(pushbackInputStream, outputStream, decodeLinePrefix - i);
                }
                decodeLineSuffix(pushbackInputStream, outputStream);
            } catch (CEStreamExhausted unused) {
                decodeBufferSuffix(pushbackInputStream, outputStream);
                return;
            }
        }
    }

    public ByteBuffer decodeBufferToByteBuffer(String str) throws IOException {
        return ByteBuffer.wrap(decodeBuffer(str));
    }

    public ByteBuffer decodeBufferToByteBuffer(InputStream inputStream) throws IOException {
        return ByteBuffer.wrap(decodeBuffer(inputStream));
    }

    public byte[] decodeBuffer(String str) throws IOException {
        byte[] bArr = new byte[str.length()];
        str.getBytes(0, str.length(), bArr, 0);
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decodeBuffer(byteArrayInputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] decodeBuffer(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        decodeBuffer(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}