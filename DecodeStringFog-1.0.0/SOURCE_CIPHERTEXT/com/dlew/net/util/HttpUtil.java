package com.dlew.net.util;

import android.net.Uri;
import android.text.TextUtils;
import com.dlew.StringFog;
import com.dlew.hardware.PhoneInfoWrapper;
import com.elvishew.xlog.XLog;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Random;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/HttpUtil.class */
public class HttpUtil extends SafeRunnable {
    private static final int DEFAULT_CONNECTION_TIMEOUT = 60000;
    private static final int DEFAULT_READ_TIMEOUT = 180000;
    private static final String ENCODE = StringFog.decrypt("N+ZZOi0=\n", "YrIfFxUJ7Ys=\n");
    private static final String DEFAULT_CONTENT_TYPE = StringFog.decrypt("g1JbmASI7KeLTUXbFcb6pJUPTZsfhqCmkE5Omg6E6baG\n", "4iIr9G3rjdM=\n");
    private int mConnectionTimeout;
    private int mReadTimeout;
    private String mContentType;
    private String mUrl;
    private IReqParam mIReqParam;
    private RequestMethod mRequestMethod;
    private RespCallback mRespCallback;
    private String mUserAgent;
    private HttpURLConnection mConn;
    private File mFilePath;
    private int mSC;
    private String mPostData;
    private FileOutputStream mFOS;
    private InputStream mIS;
    private BufferedReader mBR;
    private boolean isOnMainThread;
    private String mAuthorization;
    private String mProductVersionname;
    private String mAccessToken;
    private String mSign;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/HttpUtil$EvLveE1.class */
    public class EvLveE1 implements Runnable {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ String f29EvLveE1;

        public EvLveE1(String str) {
            this.f29EvLveE1 = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            HttpUtil.this.mRespCallback.onSuccess(HttpUtil.this.mSC, this.f29EvLveE1);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/HttpUtil$RequestMethod.class */
    public enum RequestMethod {
        GET(StringFog.decrypt("GJw+\n", "X9lq2zXE0vo=\n")),
        POST(StringFog.decrypt("ztWsWQ==\n", "npr/Db/SqkQ=\n"));

        private String mMethod;

        RequestMethod(String str) {
            this.mMethod = str;
        }

        public String getMethod() {
            return this.mMethod;
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/HttpUtil$RespCallback.class */
    public interface RespCallback {
        void onSuccess(int i, String str);

        void onError(int i, Throwable th);
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/HttpUtil$v1vLvLE.class */
    public class v1vLvLE implements Runnable {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ Throwable f30EvLveE1;

        public v1vLvLE(Throwable th) {
            this.f30EvLveE1 = th;
        }

        @Override // java.lang.Runnable
        public void run() {
            HttpUtil.this.mRespCallback.onError(HttpUtil.this.mSC, this.f30EvLveE1);
        }
    }

    public HttpUtil(String str, File file) {
        this.mConnectionTimeout = 60000;
        this.mReadTimeout = DEFAULT_READ_TIMEOUT;
        this.mContentType = StringFog.decrypt("zAMwAug8hZPEHC5B+XKTkNpeJgHzMsmS3x8lAOIwgILJ\n", "rXNAboFf5Oc=\n");
        this.mUserAgent = System.getProperty(StringFog.decrypt("7vei4nAvqa7o9w==\n", "hoPWkl5Ozss=\n"));
        this.mUrl = str;
        this.mFilePath = file;
        this.mRequestMethod = RequestMethod.GET;
    }

    private void postData() throws IOException {
        OutputStream outputStream = this.mConn.getOutputStream();
        outputStream.write(this.mPostData.getBytes(ENCODE));
        outputStream.close();
    }

    private void convertParamToPostData() {
        if (this.mIReqParam == null) {
            return;
        }
        Uri.Builder builder = new Uri.Builder();
        for (Map.Entry<String, Object> entry : this.mIReqParam.getParams().entrySet()) {
            builder.appendQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
        }
        this.mPostData = builder.build().getEncodedQuery();
    }

    private void createConn() throws IOException {
        String str = this.mUrl;
        if (this.mIReqParam != null && this.mRequestMethod != RequestMethod.POST) {
            Uri.Builder buildUpon = Uri.parse(str).buildUpon();
            buildUpon.build().getEncodedQuery();
            for (Map.Entry<String, Object> entry : this.mIReqParam.getParams().entrySet()) {
                buildUpon.appendQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
            }
            str = buildUpon.toString();
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        this.mConn = httpURLConnection;
        httpURLConnection.setRequestMethod(this.mRequestMethod.name());
        this.mConn.setUseCaches(false);
        this.mConn.setConnectTimeout(this.mConnectionTimeout);
        this.mConn.setReadTimeout(this.mReadTimeout);
        this.mConn.setRequestProperty(StringFog.decrypt("DHt0XIINp9wgeg==\n", "TxQaMudu07U=\n"), StringFog.decrypt("Qw4qdLQ=\n", "IGJFB9Fg0XU=\n"));
        this.mConn.setRequestProperty(StringFog.decrypt("Wnd6YPR8TA==\n", "GR8bEocZOH8=\n"), ENCODE);
        this.mConn.setRequestProperty(StringFog.decrypt("6HYbILT68CLTcQ==\n", "vQV+Upm7l0c=\n"), PhoneInfoWrapper.GetUserAgent());
        this.mConn.setRequestProperty(StringFog.decrypt("3THNdh/PndDKJ9Nn\n", "nl6jAnqh6f0=\n"), this.mContentType);
        this.mConn.setRequestProperty(StringFog.decrypt("LCq3oApW8Hg=\n", "XEbW1Gw5ghU=\n"), StringFog.decrypt("Nw==\n", "BgBsS3Xa1fM=\n"));
        this.mConn.setRequestProperty(StringFog.decrypt("aPCj\n", "GpnHTO5eNV8=\n"), PhoneInfoWrapper.getGAID() + String.valueOf(new Random().nextInt(100000000) + 0));
        this.mConn.setRequestProperty(StringFog.decrypt("A5RsmJKmpQ==\n", "QPwN9vzDyTE=\n"), PhoneInfoWrapper.getConversionData().channel);
        if (!TextUtils.isEmpty(this.mAuthorization)) {
            this.mConn.setRequestProperty(StringFog.decrypt("L08Pdn3RtYgPThJxfA==\n", "bjp7HhKj3PI=\n"), this.mAuthorization);
        }
        if (!TextUtils.isEmpty(this.mProductVersionname)) {
            this.mConn.setRequestProperty(StringFog.decrypt("1DCqFcnfohjBMLYY09K4D8kn\n", "pELFcby81m4=\n"), this.mProductVersionname);
        }
        if (!TextUtils.isEmpty(this.mAccessToken)) {
            this.mConn.setRequestProperty(StringFog.decrypt("4jdc8HO38qXoMVE=\n", "g1Q/lQDEhso=\n"), this.mAccessToken);
        }
        if (!TextUtils.isEmpty(this.mSign)) {
            this.mConn.setRequestProperty(StringFog.decrypt("IkGxvA==\n", "USjW0iuGg+8=\n"), this.mSign);
        }
        if (this.mRequestMethod == RequestMethod.POST) {
            this.mConn.setDoOutput(true);
            convertParamToPostData();
            if (this.mPostData != null) {
                postData();
            }
        }
    }

    private void closeSilently(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    public HttpUtil setParams(IReqParam iReqParam) {
        this.mIReqParam = iReqParam;
        return this;
    }

    public HttpUtil setAuthorization(String str) {
        this.mAuthorization = str;
        return this;
    }

    public HttpUtil setSign(String str) {
        this.mSign = str;
        return this;
    }

    public HttpUtil setProductVersionname(String str) {
        this.mProductVersionname = str;
        return this;
    }

    public HttpUtil setConnectionTimeout(int i) {
        this.mConnectionTimeout = i;
        return this;
    }

    public HttpUtil setReadTimeout(int i) {
        this.mReadTimeout = i;
        return this;
    }

    public HttpUtil setContentType(String str) {
        this.mContentType = str;
        return this;
    }

    public HttpUtil setAccessToken(String str) {
        this.mAccessToken = str;
        return this;
    }

    public void setPostData(String str) {
        this.mPostData = str;
    }

    public void setUserAgent(String str) {
        this.mUserAgent = str;
    }

    public HttpUtil setRespCallback(RespCallback respCallback) {
        this.mRespCallback = respCallback;
        return this;
    }

    public void executeSync(boolean z) {
        this.isOnMainThread = z;
        ThreadExecutorProxy.getInstance().execute(false, (Runnable) this);
    }

    public void executeAsyn(boolean z) {
        this.isOnMainThread = z;
        ThreadExecutorProxy.getInstance().execute(true, (Runnable) this);
    }

    public void execute(boolean z) {
        this.isOnMainThread = z;
        ThreadExecutorProxy.getInstance().execute(this);
    }

    @Override // com.dlew.net.util.SafeRunnable
    public void doInBackground() throws Throwable {
        String sb;
        createConn();
        int responseCode = this.mConn.getResponseCode();
        this.mSC = responseCode;
        if (responseCode >= 200 && responseCode < 400) {
            if (this.mFilePath != null) {
                File file = new File(this.mFilePath.getParent(), String.format(StringFog.decrypt("KIt885c=\n", "De8j1uRBPS8=\n"), Long.valueOf(System.currentTimeMillis()), this.mFilePath.getName()));
                this.mFOS = new FileOutputStream(file);
                this.mIS = this.mConn.getInputStream();
                this.mConn.getInputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = this.mIS.read(bArr);
                    if (read == -1) {
                        break;
                    } else {
                        this.mFOS.write(bArr, 0, read);
                    }
                }
                this.mFOS.flush();
                sb = this.mFilePath.getAbsolutePath();
                file.renameTo(this.mFilePath);
            } else {
                this.mIS = this.mConn.getInputStream();
                this.mBR = new BufferedReader(new InputStreamReader(this.mIS, ENCODE), 1048576);
                StringBuilder sb2 = new StringBuilder();
                while (true) {
                    String readLine = this.mBR.readLine();
                    if (readLine == null) {
                        break;
                    } else {
                        sb2.append(readLine);
                    }
                }
                sb = sb2.toString();
            }
            RespCallback respCallback = this.mRespCallback;
            if (respCallback != null) {
                if (this.isOnMainThread) {
                    ThreadExecutorProxy.getInstance().runOnMainThread(new EvLveE1(sb));
                    return;
                } else {
                    respCallback.onSuccess(this.mSC, sb);
                    return;
                }
            }
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.mConn.getErrorStream(), ENCODE));
        StringBuilder sb3 = new StringBuilder();
        while (true) {
            String readLine2 = bufferedReader.readLine();
            if (readLine2 == null) {
                XLog.e(StringFog.decrypt("OQBbdNuE22xIR0M9UCY=\n", "0a/skmoGMvg=\n") + this.mUrl + StringFog.decrypt("yiimu/c+\n", "6mvJ35IEnTY=\n") + this.mSC + StringFog.decrypt("CyAeWF/vMP1ZDFY=\n", "K2VsKjCdEJI=\n") + sb3.toString());
                XLog.e(StringFog.decrypt("GqjEOABoeglr79xxi8o=\n", "8gdz3rHqk50=\n") + this.mUrl + StringFog.decrypt("FFaSZAXW\n", "NBX9AGDsz70=\n") + this.mSC + StringFog.decrypt("E7LBAIXBqd5dhdALmsez\n", "M/ezcuqzibs=\n") + EncryptUtil.Decrypt(sb3.toString(), this.mUrl));
                throw new IllegalStateException(StringFog.decrypt("h8su8iYmr/mB2xm9ai7c19SWUL1wc7+00o5N8BEAr6jUnF2ta2Ps4YbaCPM2Y/z3yQ==\n", "9KhtnUJDj5Q=\n") + this.mSC);
            }
            sb3.append(readLine2);
        }
    }

    @Override // com.dlew.net.util.SafeRunnable
    public void handleError(Throwable th) {
        RespCallback respCallback = this.mRespCallback;
        if (respCallback != null) {
            if (this.isOnMainThread) {
                ThreadExecutorProxy.getInstance().runOnMainThread(new v1vLvLE(th));
            } else {
                respCallback.onError(this.mSC, th);
            }
        }
    }

    @Override // com.dlew.net.util.SafeRunnable
    public void handleFinally() {
        closeSilently(this.mFOS);
        closeSilently(this.mBR);
        closeSilently(this.mIS);
        this.mConn.disconnect();
    }

    public HttpUtil(String str, RequestMethod requestMethod) {
        this.mConnectionTimeout = 60000;
        this.mReadTimeout = DEFAULT_READ_TIMEOUT;
        this.mContentType = StringFog.decrypt("46WKXnfk7sjrupQdZqr4y/X4nF1s6qLJ8LmfXH3o69nm\n", "gtX6Mh6Hj7w=\n");
        this.mUserAgent = System.getProperty(StringFog.decrypt("O8xVDBY5r+w9zA==\n", "U7ghfDhYyIk=\n"));
        this.mUrl = str;
        this.mRequestMethod = requestMethod;
    }
}