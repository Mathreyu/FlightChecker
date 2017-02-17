package com.nearsoft.flights.flightchecker.api.dagger.components.flights;

import com.nearsoft.flights.flightchecker.FlightsMain;
import com.nearsoft.flights.flightchecker.Home;
import com.nearsoft.flights.flightchecker.QueryFlights;
import com.nearsoft.flights.flightchecker.api.dagger.modules.flights.FlightsAPIModule;
import com.nearsoft.flights.flightchecker.api.dagger.modules.flights.FlightsRetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {FlightsRetrofitModule.class, FlightsAPIModule.class})
public interface FlightsAPIComponents {

    void inject(Home home);

    void inject(QueryFlights queryFlights);

}
