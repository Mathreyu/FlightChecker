package com.nearsoft.flights.flightchecker.api.dagger;

import com.nearsoft.flights.flightchecker.api.FlightApi;
import com.nearsoft.flights.flightchecker.presenter.FlightsMainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ramon on 2/16/2017.
 */

@Module(includes = RetrofitModule.class)
public class FlightsMainModule {

    @Provides
    @Singleton
    public FlightsMainPresenter providePresenter(FlightApi api){
        return new FlightsMainPresenter(api);
    }
}
