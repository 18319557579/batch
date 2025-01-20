package com.elvishew.xlog.flattener;

import com.elvishew.xlog.LogLevel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/flattener/PatternFlattener.class */
public class PatternFlattener implements Flattener, Flattener2 {
    private static final String PARAM = "[^{}]*";
    private static final Pattern PARAM_REGEX = Pattern.compile("\\{([^{}]*)\\}");
    private static final String PARAMETER_DATE = "d";
    private static final String PARAMETER_LEVEL_SHORT = "l";
    private static final String PARAMETER_LEVEL_LONG = "L";
    private static final String PARAMETER_TAG = "t";
    private static final String PARAMETER_MESSAGE = "m";
    static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private String pattern;
    private List<EeleE> parameterFillers;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/flattener/PatternFlattener$EeleE.class */
    public static abstract class EeleE {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public String f70EvLveE1;
        public String v1vLvLE;

        public EeleE(String str, String str2) {
            this.f70EvLveE1 = str;
            this.v1vLvLE = str2;
        }

        public abstract String EvLveE1(String str, long j, int i, String str2, String str3);
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/flattener/PatternFlattener$EvLveE1.class */
    public static class EvLveE1 extends EeleE {
        public String evleeEelE;
        public ThreadLocal<SimpleDateFormat> EeleE;

        /* renamed from: com.elvishew.xlog.flattener.PatternFlattener$EvLveE1$EvLveE1, reason: collision with other inner class name */
        /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/flattener/PatternFlattener$EvLveE1$EvLveE1.class */
        public class C0000EvLveE1 extends ThreadLocal<SimpleDateFormat> {
            public C0000EvLveE1() {
            }

            @Override // java.lang.ThreadLocal
            /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
            public SimpleDateFormat initialValue() {
                return new SimpleDateFormat(EvLveE1.this.evleeEelE, Locale.US);
            }
        }

        public EvLveE1(String str, String str2, String str3) {
            super(str, str2);
            C0000EvLveE1 c0000EvLveE1 = new C0000EvLveE1();
            this.EeleE = c0000EvLveE1;
            this.evleeEelE = str3;
            try {
                c0000EvLveE1.get().format(new Date());
            } catch (Exception e) {
                throw new IllegalArgumentException("Bad date pattern: " + str3, e);
            }
        }

        @Override // com.elvishew.xlog.flattener.PatternFlattener.EeleE
        public String EvLveE1(String str, long j, int i, String str2, String str3) {
            return str.replace(this.f70EvLveE1, this.EeleE.get().format(new Date(j)));
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/flattener/PatternFlattener$LvL11LLEl.class */
    public static class LvL11LLEl extends EeleE {
        public LvL11LLEl(String str, String str2) {
            super(str, str2);
        }

        @Override // com.elvishew.xlog.flattener.PatternFlattener.EeleE
        public String EvLveE1(String str, long j, int i, String str2, String str3) {
            return str.replace(this.f70EvLveE1, str2);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/flattener/PatternFlattener$evleeEelE.class */
    public static class evleeEelE extends EeleE {
        public evleeEelE(String str, String str2) {
            super(str, str2);
        }

        @Override // com.elvishew.xlog.flattener.PatternFlattener.EeleE
        public String EvLveE1(String str, long j, int i, String str2, String str3) {
            return str.replace(this.f70EvLveE1, str3);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/flattener/PatternFlattener$v1vLvLE.class */
    public static class v1vLvLE extends EeleE {
        public boolean evleeEelE;

        public v1vLvLE(String str, String str2, boolean z) {
            super(str, str2);
            this.evleeEelE = z;
        }

        @Override // com.elvishew.xlog.flattener.PatternFlattener.EeleE
        public String EvLveE1(String str, long j, int i, String str2, String str3) {
            return this.evleeEelE ? str.replace(this.f70EvLveE1, LogLevel.getLevelName(i)) : str.replace(this.f70EvLveE1, LogLevel.getShortLevelName(i));
        }
    }

    public PatternFlattener(String str) {
        if (str == null) {
            throw new NullPointerException("Pattern should not be null");
        }
        this.pattern = str;
        List<EeleE> parseParameters = parseParameters(parsePattern(str));
        this.parameterFillers = parseParameters;
        if (parseParameters.size() == 0) {
            throw new IllegalArgumentException("No recognizable parameter found in the pattern " + str);
        }
    }

    public static List<String> parsePattern(String str) {
        ArrayList arrayList = new ArrayList(4);
        Matcher matcher = PARAM_REGEX.matcher(str);
        while (matcher.find()) {
            arrayList.add(matcher.group(1));
        }
        return arrayList;
    }

    private static List<EeleE> parseParameters(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            EeleE parseParameter = parseParameter(it.next());
            if (parseParameter != null) {
                arrayList.add(parseParameter);
            }
        }
        return arrayList;
    }

    private static EeleE parseParameter(String str) {
        String str2 = "{" + str + "}";
        String trim = str.trim();
        EvLveE1 parseDateParameter = parseDateParameter(str2, trim);
        if (parseDateParameter != null) {
            return parseDateParameter;
        }
        v1vLvLE parseLevelParameter = parseLevelParameter(str2, trim);
        if (parseLevelParameter != null) {
            return parseLevelParameter;
        }
        LvL11LLEl parseTagParameter = parseTagParameter(str2, trim);
        if (parseTagParameter != null) {
            return parseTagParameter;
        }
        evleeEelE parseMessageParameter = parseMessageParameter(str2, trim);
        if (parseMessageParameter != null) {
            return parseMessageParameter;
        }
        return null;
    }

    public static EvLveE1 parseDateParameter(String str, String str2) {
        if (str2.startsWith("d ") && str2.length() > 2) {
            return new EvLveE1(str, str2, str2.substring(2));
        }
        if (str2.equals(PARAMETER_DATE)) {
            return new EvLveE1(str, str2, DEFAULT_DATE_FORMAT);
        }
        return null;
    }

    public static v1vLvLE parseLevelParameter(String str, String str2) {
        if (str2.equals(PARAMETER_LEVEL_SHORT)) {
            return new v1vLvLE(str, str2, false);
        }
        if (str2.equals(PARAMETER_LEVEL_LONG)) {
            return new v1vLvLE(str, str2, true);
        }
        return null;
    }

    public static LvL11LLEl parseTagParameter(String str, String str2) {
        if (str2.equals(PARAMETER_TAG)) {
            return new LvL11LLEl(str, str2);
        }
        return null;
    }

    public static evleeEelE parseMessageParameter(String str, String str2) {
        if (str2.equals(PARAMETER_MESSAGE)) {
            return new evleeEelE(str, str2);
        }
        return null;
    }

    @Override // com.elvishew.xlog.flattener.Flattener
    public CharSequence flatten(int i, String str, String str2) {
        return flatten(System.currentTimeMillis(), i, str, str2);
    }

    @Override // com.elvishew.xlog.flattener.Flattener2
    public CharSequence flatten(long j, int i, String str, String str2) {
        String str3 = this.pattern;
        Iterator<EeleE> it = this.parameterFillers.iterator();
        while (it.hasNext()) {
            str3 = it.next().EvLveE1(str3, j, i, str, str2);
        }
        return str3;
    }
}