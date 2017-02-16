package com.nearsoft.flights.flightchecker.api.dagger;

import com.nearsoft.flights.flightchecker.api.FlightApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    private String mEndpoint;

    public RetrofitModule(String endpoint) {
        mEndpoint = endpoint;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mEndpoint)
                .build();
    }

    @Provides
    @Singleton
    public FlightApi provideAPI(Retrofit retrofit) {
        return retrofit.create(FlightApi.class);
    }

}
