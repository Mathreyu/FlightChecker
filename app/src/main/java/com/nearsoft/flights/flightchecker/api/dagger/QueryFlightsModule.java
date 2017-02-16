package com.nearsoft.flights.flightchecker.api.dagger;

import com.nearsoft.flights.flightchecker.api.FlightApi;
import com.nearsoft.flights.flightchecker.presenter.QueryFlightsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = {RetrofitModule.class})
public class QueryFlightsModule {

    @Provides
    @Singleton
    public QueryFlightsPresenter provideQueryFlightsPresenter(FlightApi api){
        return new QueryFlightsPresenter(api);
    }
}