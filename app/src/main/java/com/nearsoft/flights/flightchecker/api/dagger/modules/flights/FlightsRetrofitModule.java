package com.nearsoft.flights.flightchecker.api.dagger.modules.flights;

import com.nearsoft.flights.flightchecker.api.FlightsService;
import com.nearsoft.flights.flightchecker.api.dagger.components.flights.DaggerFlightsAPIComponents;
import com.nearsoft.flights.flightchecker.api.dagger.modules.RetrofitModule;
import com.nearsoft.flights.flightchecker.presenter.FlightsAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class FlightsRetrofitModule {

    @Provides
    @Singleton
    public FlightsService provideAPI(Retrofit retrofit) {
        return retrofit.create(FlightsService.class);
    }

}