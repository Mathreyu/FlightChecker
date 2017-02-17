package com.nearsoft.flights.flightchecker.api;


import com.nearsoft.flights.flightchecker.models.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface FlightsService {

    //String BASE_URL = "http://www.mocky.io/v2/58a1f958130000740a4e070d/";
    String BASE_URL = "http://192.168.136.254:8080/";

    @GET("itinerary/")
    Call<List<APIResponse>> getItineraries();

    @GET("itinerary/{id}")
    Observable<APIResponse> getItinerary(@Path("id") int id);

    @GET("itinerary/search")
    Observable<APIResponse> searchFlights(
            @Query("departureAirport") String departureAirport,
            @Query("arrivalAirport") String arrivalAirport);

}
