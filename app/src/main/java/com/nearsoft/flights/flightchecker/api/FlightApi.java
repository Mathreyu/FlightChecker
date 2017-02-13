package com.nearsoft.flights.flightchecker.api;


import com.nearsoft.flights.flightchecker.models.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FlightApi {

    String ENDPOINT = "";

    @GET("itinerary/{id}")
    Call<APIResponse> getIntinerary(@Path("id") int id);

}
