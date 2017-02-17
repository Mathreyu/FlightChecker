package com.nearsoft.flights.flightchecker.api.dagger.modules.flights;

import com.nearsoft.flights.flightchecker.api.FlightsService;
import com.nearsoft.flights.flightchecker.api.dagger.components.flights.DaggerFlightsAPIComponents;
import com.nearsoft.flights.flightchecker.api.dagger.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class FlightsRetrofitModule {

    public FlightsRetrofitModule() {
        DaggerFlightsAPIComponents.builder()
                .retrofitModule(new RetrofitModule(FlightsService.BASE_URL))
                .build();
    }

    @Provides
    @Singleton
    public FlightsService provideAPI(Retrofit retrofit) {
        return retrofit.create(FlightsService.class);
    }

}