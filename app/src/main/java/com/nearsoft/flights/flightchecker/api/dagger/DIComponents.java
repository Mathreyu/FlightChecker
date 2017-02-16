package com.nearsoft.flights.flightchecker.api.dagger;

import com.nearsoft.flights.flightchecker.FlightsMain;
import com.nearsoft.flights.flightchecker.QueryFlights;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ramon on 2/16/2017.
 */

@Singleton
@Component (modules = {RetrofitModule.class, FlightsMainModule.class, QueryFlightsModule.class})
public interface DIComponents {

    void inject(QueryFlights queryFlights);

    void inject(FlightsMain flightsMain);
}
