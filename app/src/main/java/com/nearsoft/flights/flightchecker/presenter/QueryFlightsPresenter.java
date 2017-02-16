package com.nearsoft.flights.flightchecker.presenter;

import com.nearsoft.flights.flightchecker.api.FlightApi;
import com.nearsoft.flights.flightchecker.models.APIResponse;
import com.nearsoft.flights.flightchecker.models.FlightSegment;
import com.nearsoft.flights.flightchecker.views.FlightAdapter;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ramon on 2/16/2017.
 */

public class QueryFlightsPresenter {
    private FlightApi service;

    public QueryFlightsPresenter(FlightApi api){
        service = api;
    }

    public void searchRetrofit( String departureAirport, String arrivalAirport){
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
