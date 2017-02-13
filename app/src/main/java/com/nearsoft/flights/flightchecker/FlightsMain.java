package com.nearsoft.flights.flightchecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nearsoft.flights.flightchecker.api.FlightApi;
import com.nearsoft.flights.flightchecker.models.APIResponse;
import com.nearsoft.flights.flightchecker.models.FlightSegment;
import com.nearsoft.flights.flightchecker.models.Itinerary;
import com.nearsoft.flights.flightchecker.views.FlightAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlightsMain extends AppCompatActivity {
    @BindView(R.id.rvFlights)
    RecyclerView rvFlights;
    List<FlightSegment> flightSegments;

    private FlightAdapter adapter;
    private Retrofit retrofit;
    private FlightApi flightApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights_main);
        ButterKnife.bind(this);
        retrofit = provideRetrofit();
        flightApi = retrofit.create(FlightApi.class);

        adapter = new FlightAdapter();
        rvFlights.setLayoutManager(new LinearLayoutManager(this));
        rvFlights.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFlights(adapter);
    }

    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(FlightApi.ENDPOINT)
                .build();
    }

    public void getFlights(final FlightAdapter adapter){
        Call<APIResponse> apiService = flightApi.getIntinerary(1);

        apiService.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                flightSegments = response.body().getItinerary().getOriginDestinationOptions().get(0).getFlightSegments();
                adapter.addResults(flightSegments);
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.d("Retrofit","failed to load.");
            }
        });
    }
}
