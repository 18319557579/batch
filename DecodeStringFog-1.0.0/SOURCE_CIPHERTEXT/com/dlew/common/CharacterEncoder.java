package com.dlew.common;

import com.dlew.StringFog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/CharacterEncoder.class */
public abstract class CharacterEncoder {
    protected PrintStream pStream;

    private byte[] getBytes(ByteBuffer byteBuffer) {
        byte[] bArr = null;
        if (byteBuffer.hasArray()) {
            byte[] array = byteBuffer.array();
            if (array.length == byteBuffer.capacity() && array.length == byteBuffer.remaining()) {
                byteBuffer.position(byteBuffer.limit());
                bArr = array;
            }
        }
        if (bArr == null) {
            byte[] bArr2 = new byte[byteBuffer.remaining()];
            bArr = bArr2;
            byteBuffer.get(bArr2);
        }
        return bArr;
    }

    public abstract int bytesPerAtom();

    public abstract int bytesPerLine();

    public void encodeBufferPrefix(OutputStream outputStream) throws IOException {
        this.pStream = new PrintStream(outputStream);
    }

    public void encodeBufferSuffix(OutputStream outputStream) throws IOException {
    }

    public void encodeLinePrefix(OutputStream outputStream, int i) throws IOException {
    }

    public void encodeLineSuffix(OutputStream outputStream) throws IOException {
        this.pStream.println();
    }

    public abstract void encodeAtom(OutputStream outputStream, byte[] bArr, int i, int i2) throws IOException;

    public int readFully(InputStream inputStream, byte[] bArr) throws IOException {
        for (int i = 0; i < bArr.length; i++) {
            int read = inputStream.read();
            if (read == -1) {
                return i;
            }
            bArr[i] = (byte) read;
        }
        return bArr.length;
    }

    public void encode(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[bytesPerLine()];
        encodeBufferPrefix(outputStream);
        while (true) {
            int readFully = readFully(inputStream, bArr);
            if (readFully == 0) {
                break;
            }
            encodeLinePrefix(outputStream, readFully);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= readFully) {
                    break;
                }
                if (i2 + bytesPerAtom() <= readFully) {
                    encodeAtom(outputStream, bArr, i2, bytesPerAtom());
                } else {
                    encodeAtom(outputStream, bArr, i2, readFully - i2);
                }
                i = i2 + bytesPerAtom();
            }
            if (readFully < bytesPerLine()) {
                break;
            } else {
                encodeLineSuffix(outputStream);
            }
        }
        encodeBufferSuffix(outputStream);
    }

    public void encodeBuffer(InputStream inputStream, OutputStream outputStream) throws IOException {
        int readFully;
        byte[] bArr = new byte[bytesPerLine()];
        encodeBufferPrefix(outputStream);
        do {
            readFully = readFully(inputStream, bArr);
            if (readFully == 0) {
                break;
            }
            encodeLinePrefix(outputStream, readFully);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= readFully) {
                    break;
                }
                if (i2 + bytesPerAtom() <= readFully) {
                    encodeAtom(outputStream, bArr, i2, bytesPerAtom());
                } else {
                    encodeAtom(outputStream, bArr, i2, readFully - i2);
                }
                i = i2 + bytesPerAtom();
            }
            encodeLineSuffix(outputStream);
        } while (readFully >= bytesPerLine());
        encodeBufferSuffix(outputStream);
    }

    public void encodeBuffer(byte[] bArr, OutputStream outputStream) throws IOException {
        encodeBuffer(new ByteArrayInputStream(bArr), outputStream);
    }

    public String encodeBuffer(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encodeBuffer(new ByteArrayInputStream(bArr), byteArrayOutputStream);
            return byteArrayOutputStream.toString();
        } catch (Exception unused) {
            throw new Error(StringFog.decrypt("e98i+Q+As1VK8i3oAYeiQhbSLegBh6JyTdEl7hzDrl5M0jHlD4/nVUrFLPk=\n", "OLdDi27jxzA=\n"));
        }
    }

    public void encode(byte[] bArr, OutputStream outputStream) throws IOException {
        encode(new ByteArrayInputStream(bArr), outputStream);
    }

    public String encode(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encode(new ByteArrayInputStream(bArr), byteArrayOutputStream);
            return byteArrayOutputStream.toString(StringFog.decrypt("KIRnXoht\n", "ELxSZ9dcVZI=\n"));
        } catch (Exception unused) {
            throw new Error(StringFog.decrypt("wteyV+l9I0Hz+r1G53oyVq/avUbnejIE6NGnQPpwNkih2qFX52w=\n", "gb/TJYgeVyQ=\n"));
        }
    }

    public void encodeBuffer(ByteBuffer byteBuffer, OutputStream outputStream) throws IOException {
        encodeBuffer(getBytes(byteBuffer), outputStream);
    }

    public String encodeBuffer(ByteBuffer byteBuffer) {
        return encodeBuffer(getBytes(byteBuffer));
    }

    public void encode(ByteBuffer byteBuffer, OutputStream outputStream) throws IOException {
        encode(getBytes(byteBuffer), outputStream);
    }

    public String encode(ByteBuffer byteBuffer) {
        return encode(getBytes(byteBuffer));
    }
}