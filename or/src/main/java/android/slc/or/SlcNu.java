package android.slc.or;

import android.util.Log;

import androidx.collection.SimpleArrayMap;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SlcNu {
    private final static SlcNu SLC_NU = new SlcNu();
    private final SimpleArrayMap<String, Retrofit> otherRetrofit = new SimpleArrayMap<>();
    protected Retrofit mDefRetrofit;

    private SlcNu() {
    }

    public static OkHttpClient newDefOkHttpClientInstance() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Log.d("OkHttp", message));
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
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

    public void putRetrofit(String key, Retrofit retrofit) {
        otherRetrofit.put(key, retrofit);
    }

    public Retrofit getRetrofit(String key) {
        return otherRetrofit.get(key);
    }

    public void setBaseUrl(String url) {
        if (mDefRetrofit == null) {
            mDefRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(newDefOkHttpClientInstance())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        } else {
            mDefRetrofit = mDefRetrofit.newBuilder().baseUrl(url).build();
        }
    }

    public void setDefRetrofit(Retrofit defRetrofit) {
        mDefRetrofit = defRetrofit;
    }

    public <T> T create(final Class<T> service) {
        return create(mDefRetrofit, service);
    }

    public <T> T create(String key, final Class<T> service) {
        return create(getRetrofit(key), service);
    }

    public <T> T create(Retrofit retrofit, final Class<T> service) {
        return retrofit.create(service);
    }
}
