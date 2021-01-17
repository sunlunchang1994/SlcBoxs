package android.slc.or;

import android.content.Context;
import android.util.Log;

import androidx.collection.SimpleArrayMap;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SlcNu {
    private static final SlcNu SLC_NU = new SlcNu();
    private final static SimpleArrayMap<String, SlcNu> otherSlcNu = new SimpleArrayMap<>();
    protected Context mAppContext;
    protected OkHttpClient mGlobalOkHttpClient;
    protected Retrofit mGlobalRetrofit;

    private SlcNu() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Log.d("OkHttp", message));
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        mGlobalOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();
    }

    /**
     * 获取默认的实例
     *
     * @return
     */
    public static SlcNu getInstance() {
        return SLC_NU;
    }

    /**
     * 获取一个新的SlcNu
     *
     * @param key
     * @return
     */
    public static SlcNu getInstance(String key) {
        SlcNu slcNu = otherSlcNu.get(key);
        if (slcNu == null) {
            synchronized (SlcNu.class) {
                slcNu = otherSlcNu.get(key);
                if (slcNu == null) {
                    slcNu = new SlcNu();
                    otherSlcNu.put(key, slcNu);
                }
            }
        }
        return slcNu;
    }

    public void init(Context context) {
        mAppContext = context.getApplicationContext();
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.mGlobalOkHttpClient = okHttpClient;
        if (mGlobalRetrofit != null && mGlobalRetrofit.callFactory() != okHttpClient) {
            mGlobalRetrofit = mGlobalRetrofit.newBuilder().client(this.mGlobalOkHttpClient).build();
        }
    }

    public OkHttpClient getGlobalOkHttpClient() {
        return mGlobalOkHttpClient;
    }

    public void setBaseUrl(String url) {
        if (mGlobalRetrofit == null) {
            mGlobalRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(mGlobalOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        } else {
            mGlobalRetrofit = mGlobalRetrofit.newBuilder().baseUrl(url).build();
        }
    }

    public void setGlobalRetrofit(Retrofit globalRetrofit) {
        mGlobalRetrofit = globalRetrofit;
    }

    public <T> T create(final Class<T> service) {
        return create(mGlobalRetrofit, service);
    }

    public <T> T create(Retrofit retrofit, final Class<T> service) {
        return retrofit.create(service);
    }
}
