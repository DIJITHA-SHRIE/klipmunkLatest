package com.telcco.klipmunk;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.klipmunk.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <S> S createService(Class<S> serviceClass) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        //return retrofit.create(serviceClass);
        return new Retrofit.Builder()
                .baseUrl("https://api.klipmunk.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(serviceClass);
    }
}
