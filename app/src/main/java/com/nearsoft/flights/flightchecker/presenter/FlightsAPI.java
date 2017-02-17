package com.nearsoft.flights.flightchecker.presenter;


import com.nearsoft.flights.flightchecker.api.FlightsService;
import com.nearsoft.flights.flightchecker.models.APIResponse;

import rx.Observable;

public class FlightsAPI {

    private FlightsService service;

    public FlightsAPI(FlightsService service) {
        this.service = service;
    }

    public Observable<APIResponse> getAllFlights() {
        return service.getItinerary(1);
    }

    public Observable<APIResponse> searchFlightsByAirports(String departureAirport, String arrivalAirport){
        return service.searchFlights(departureAirport, arrivalAirport);
    }

}
