package com.nearsoft.flights.flightchecker.api;


import com.nearsoft.flights.flightchecker.models.Itinerary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FlightApi {

    String ENDPOINT = "";

    @GET("")
    Call<List<Itinerary>> getIntineryList();
}
