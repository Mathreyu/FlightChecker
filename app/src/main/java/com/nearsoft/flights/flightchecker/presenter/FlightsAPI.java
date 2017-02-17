package com.nearsoft.flights.flightchecker.presenter;


import com.nearsoft.flights.flightchecker.api.FlightsService;
import com.nearsoft.flights.flightchecker.models.APIResponse;
import com.nearsoft.flights.flightchecker.models.FlightSegment;
import com.nearsoft.flights.flightchecker.views.FlightAdapter;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FlightsAPI {

    private FlightsService service;

    public FlightsAPI(FlightsService service) {
        this.service = service;
    }

    public void getAllFlights(final FlightAdapter adapter) {
        Observable<APIResponse> apiService = service.getItinerary(1);

        apiService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flight -> {
                    List<FlightSegment> responses = flight.getItinerary().getOriginDestinationOptions().get(0).getFlightSegments();
                    //adapter.addResults(responses);
                }, Throwable::printStackTrace);
    }

    public void searchFlightsByAirports(String departureAirport, String arrivalAirport){
        Observable<APIResponse> apiService = service.searchFlights(
                departureAirport, arrivalAirport);

        apiService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flight -> {
                    List<FlightSegment> responses = flight.getItinerary().getOriginDestinationOptions().get(0).getFlightSegments();
                    //adapter.addResults(responses);
                }, Throwable::printStackTrace);
    }

}
