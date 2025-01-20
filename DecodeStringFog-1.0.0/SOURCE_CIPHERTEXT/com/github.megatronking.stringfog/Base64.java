package com.github.megatronking.stringfog;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/github/megatronking/stringfog/Base64.class */
public final class Base64 {
    public static final int DEFAULT = 0;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    private static final int CRLF = 4;
    private static final int URL_SAFE = 8;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/github/megatronking/stringfog/Base64$Coder.class */
    public static abstract class Coder {
        byte[] output;
        int op;

        public abstract boolean process(byte[] bArr, int i, int i2, boolean z);

        public abstract int maxOutputSize(int i);
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/github/megatronking/stringfog/Base64$Decoder.class */
    public static class Decoder extends Coder {
        private static final int SKIP = -1;
        private int state;
        private int value;
        private final int[] alphabet;
        private static final int EQUALS = -2;
        private static final int[] DECODE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, EQUALS, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, Base64.URL_SAFE, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] DECODE_WEBSAFE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, EQUALS, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, Base64.URL_SAFE, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        private Decoder(int i, byte[] bArr) {
            this.output = bArr;
            this.alphabet = (i & Base64.URL_SAFE) == 0 ? DECODE : DECODE_WEBSAFE;
            this.state = 0;
            this.value = 0;
        }

        @Override // com.github.megatronking.stringfog.Base64.Coder
        public int maxOutputSize(int i) {
            return ((i * 3) / 4) + 10;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0233  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0247  */
        @Override // com.github.megatronking.stringfog.Base64.Coder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean process(byte[] r11, int r12, int r13, boolean r14) {
            /*
                Method dump skipped, instructions count: 699
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.github.megatronking.stringfog.Base64.Decoder.process(byte[], int, int, boolean):boolean");
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/github/megatronking/stringfog/Base64$Encoder.class */
    public static class Encoder extends Coder {
        static final int LINE_GROUPS = 19;
        private static final byte[] ENCODE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        private static final byte[] ENCODE_WEBSAFE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        private final byte[] tail;
        int tailLen;
        private int count;
        final boolean do_padding;
        final boolean do_newline;
        final boolean do_cr;
        private final byte[] alphabet;

        private Encoder(int i, byte[] bArr) {
            this.output = bArr;
            this.do_padding = (i & 1) == 0;
            boolean z = (i & 2) == 0;
            this.do_newline = z;
            this.do_cr = (i & 4) != 0;
            boolean z2 = z;
            this.alphabet = (i & Base64.URL_SAFE) == 0 ? ENCODE : ENCODE_WEBSAFE;
            this.tail = new byte[2];
            this.tailLen = 0;
            this.count = z2 ? LINE_GROUPS : -1;
        }

        @Override // com.github.megatronking.stringfog.Base64.Coder
        public int maxOutputSize(int i) {
            return ((i * Base64.URL_SAFE) / 5) + 10;
        }

        @Override // com.github.megatronking.stringfog.Base64.Coder
        public boolean process(byte[] bArr, int i, int i2, boolean z) {
            byte b;
            byte b2;
            byte b3;
            int i3;
            byte[] bArr2 = this.alphabet;
            byte[] bArr3 = this.output;
            int i4 = 0;
            int i5 = this.count;
            int i6 = i2 + i;
            int i7 = -1;
            int i8 = this.tailLen;
            if (i8 != 1) {
                if (i8 == 2 && (i3 = i + 1) <= i6) {
                    byte[] bArr4 = this.tail;
                    i7 = ((bArr4[0] & 255) << 16) | ((bArr4[1] & 255) << Base64.URL_SAFE) | (bArr[i] & 255);
                    this.tailLen = 0;
                    i = i3;
                }
            } else if (i + 2 <= i6) {
                int i9 = i + 1;
                int i10 = ((this.tail[0] & 255) << 16) | ((bArr[i] & 255) << Base64.URL_SAFE);
                i = i9 + 1;
                i7 = i10 | (bArr[i9] & 255);
                this.tailLen = 0;
            }
            if (i7 != -1) {
                bArr3[0] = bArr2[(i7 >> 18) & 63];
                bArr3[1] = bArr2[(i7 >> 12) & 63];
                bArr3[2] = bArr2[(i7 >> 6) & 63];
                i4 = 4;
                bArr3[3] = bArr2[i7 & 63];
                int i11 = i5 - 1;
                i5 = i11;
                if (i11 == 0) {
                    if (this.do_cr) {
                        bArr3[4] = 13;
                        i4 = 5;
                    }
                    bArr3[i4] = 10;
                    i5 = LINE_GROUPS;
                    i4++;
                }
            }
            while (true) {
                int i12 = i + 3;
                if (i12 > i6) {
                    break;
                }
                int i13 = ((bArr[i] & 255) << 16) | ((bArr[i + 1] & 255) << Base64.URL_SAFE) | (bArr[i + 2] & 255);
                bArr3[i4] = bArr2[(i13 >> 18) & 63];
                bArr3[i4 + 1] = bArr2[(i13 >> 12) & 63];
                bArr3[i4 + 2] = bArr2[(i13 >> 6) & 63];
                bArr3[i4 + 3] = bArr2[i13 & 63];
                i4 += 4;
                int i14 = i5 - 1;
                i5 = i14;
                if (i14 == 0) {
                    if (this.do_cr) {
                        bArr3[i4] = 13;
                        i4++;
                    }
                    bArr3[i4] = 10;
                    i5 = LINE_GROUPS;
                    i4++;
                    i = i12;
                } else {
                    i = i12;
                }
            }
            if (z) {
                int i15 = this.tailLen;
                if (i - i15 == i6 - 1) {
                    int i16 = 0;
                    if (i15 > 0) {
                        i16 = 1;
                        b3 = this.tail[0];
                    } else {
                        b3 = bArr[i];
                    }
                    int i17 = (b3 & 255) << 4;
                    this.tailLen = i15 - i16;
                    int i18 = i4 + 1;
                    bArr3[i4] = bArr2[(i17 >> 6) & 63];
                    int i19 = i18 + 1;
                    bArr3[i18] = bArr2[i17 & 63];
                    if (this.do_padding) {
                        int i20 = i19 + 1;
                        bArr3[i19] = 61;
                        i19 = i20 + 1;
                        bArr3[i20] = 61;
                    }
                    if (this.do_newline) {
                        if (this.do_cr) {
                            bArr3[i19] = 13;
                            i19++;
                        }
                        bArr3[i19] = 10;
                        i4 = i19 + 1;
                    } else {
                        i4 = i19;
                    }
                } else if (i - i15 == i6 - 2) {
                    int i21 = 0;
                    if (i15 > 1) {
                        i21 = 1;
                        b = this.tail[0];
                    } else {
                        b = bArr[i];
                        i++;
                    }
                    int i22 = (b & 255) << 10;
                    if (i15 > 0) {
                        int i23 = i21;
                        i21 = i23 + 1;
                        b2 = this.tail[i23];
                    } else {
                        b2 = bArr[i];
                    }
                    int i24 = i22 | ((b2 & 255) << 2);
                    this.tailLen = i15 - i21;
                    int i25 = i4 + 1;
                    bArr3[i4] = bArr2[(i24 >> 12) & 63];
                    int i26 = i25 + 1;
                    bArr3[i25] = bArr2[(i24 >> 6) & 63];
                    int i27 = i26 + 1;
                    bArr3[i26] = bArr2[i24 & 63];
                    if (this.do_padding) {
                        bArr3[i27] = 61;
                        i27++;
                    }
                    if (this.do_newline) {
                        if (this.do_cr) {
                            bArr3[i27] = 13;
                            i27++;
                        }
                        bArr3[i27] = 10;
                        i4 = i27 + 1;
                    } else {
                        i4 = i27;
                    }
                } else if (this.do_newline && i4 > 0 && i5 != LINE_GROUPS) {
                    if (this.do_cr) {
                        bArr3[i4] = 13;
                        i4++;
                    }
                    bArr3[i4] = 10;
                    i4++;
                }
            } else if (i == i6 - 1) {
                byte[] bArr5 = this.tail;
                int i28 = this.tailLen;
                this.tailLen = i28 + 1;
                bArr5[i28] = bArr[i];
            } else if (i == i6 - 2) {
                byte[] bArr6 = this.tail;
                int i29 = this.tailLen;
                int i30 = i29 + 1;
                bArr6[i29] = bArr[i];
                this.tailLen = i30 + 1;
                bArr6[i30] = bArr[i + 1];
            }
            this.op = i4;
            this.count = i5;
            return true;
        }
    }

    public static byte[] decode(String str, int i) {
        return decode(str.getBytes(), i);
    }

    public static byte[] encode(String str, int i) {
        return encode(str.getBytes(), i);
    }

    private Base64() {
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, 0, bArr.length, i);
    }

    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, bArr.length, i);
    }

    private static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        Decoder decoder = new Decoder(i3, new byte[(i2 * 3) / 4]);
        if (decoder.process(bArr, i, i2, true)) {
            int i4 = decoder.op;
            byte[] bArr2 = decoder.output;
            if (i4 == bArr2.length) {
                return bArr2;
            }
            byte[] bArr3 = new byte[i4];
            System.arraycopy(bArr2, 0, bArr3, 0, i4);
            return bArr3;
        }
        throw new IllegalArgumentException("bad base-64");
    }

    private static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        Encoder encoder = new Encoder(i3, null);
        int i4 = (i2 / 3) * 4;
        if (encoder.do_padding) {
            if (i2 % 3 > 0) {
                i4 += 4;
            }
        } else {
            int i5 = i2 % 3;
            if (i5 == 1) {
                i4 += 2;
            } else if (i5 == 2) {
                i4 += 3;
            }
        }
        if (encoder.do_newline && i2 > 0) {
            i4 += (((i2 - 1) / 57) + 1) * (encoder.do_cr ? 2 : 1);
        }
        encoder.output = new byte[i4];
        encoder.process(bArr, i, i2, true);
        return encoder.output;
    }
}