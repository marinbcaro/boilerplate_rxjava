package com.example.android.boilerplate.di;


import android.app.Application;

import com.example.android.boilerplate.network.GithubInterface;
import com.example.android.boilerplate.network.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by carolinamarin on 2/3/18.
 */
@Module
public class DataModule {

    private String BASE_URL = "https://api.github.com";
    private Application application;

    public DataModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
     OkHttpClient prvideHttpClient()

    {
        return new OkHttpClient().newBuilder().build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient)

    {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    GithubInterface provideItemService(Retrofit retrofit){
        return retrofit.create(GithubInterface.class);
    }

    @Provides
    Repository provideRepository(GithubInterface githubInterface)
    {
        return new Repository(githubInterface);
    }

}
