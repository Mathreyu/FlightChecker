package com.nearsoft.flights.flightchecker.api.dagger.modules.flights;

import com.nearsoft.flights.flightchecker.api.FlightsService;
import com.nearsoft.flights.flightchecker.api.dagger.modules.RetrofitModule;
import com.nearsoft.flights.flightchecker.presenter.FlightsAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = RetrofitModule.class)
public class FlightsAPIModule {

    @Provides
    @Singleton
    public FlightsAPI providePresenter(FlightsService service){
        return new FlightsAPI(service);
    }

}
