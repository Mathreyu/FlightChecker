package com.nearsoft.flights.flightchecker.presenter;

import com.nearsoft.flights.flightchecker.api.FlightApi;
import com.nearsoft.flights.flightchecker.models.APIResponse;
import com.nearsoft.flights.flightchecker.models.FlightSegment;
import com.nearsoft.flights.flightchecker.views.FlightAdapter;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FlightsMainPresenter {

    private FlightApi service;

    public FlightsMainPresenter(FlightApi api) {
        service = api;
    }

    public void getFlights(final FlightAdapter adapter) {
        Observable<APIResponse> apiService = service.getItinerary(1);

        apiService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flight -> {
                    List<FlightSegment> responses = flight.getItinerary().getOriginDestinationOptions().get(0).getFlightSegments();
                    adapter.addResults(responses);
                }, Throwable::printStackTrace);
    }

}
