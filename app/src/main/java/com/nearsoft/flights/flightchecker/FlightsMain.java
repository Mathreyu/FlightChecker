package com.nearsoft.flights.flightchecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nearsoft.flights.flightchecker.api.FlightApi;
import com.nearsoft.flights.flightchecker.models.APIResponse;
import com.nearsoft.flights.flightchecker.models.FlightSegment;
import com.nearsoft.flights.flightchecker.models.Itinerary;
import com.nearsoft.flights.flightchecker.models.OriginDestinationOption;
import com.nearsoft.flights.flightchecker.views.FlightAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        rvFlights.addItemDecoration(new DividerItemDecoration(rvFlights.getContext(),
                LinearLayout.VERTICAL));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFlights(adapter);
    }

    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(FlightApi.BASE_URL)
                .build();
    }

    public void getFlights(final FlightAdapter adapter){
        Observable<APIResponse> apiService = flightApi.getIntinerary(1);

        apiService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flight -> {
                    List<FlightSegment> responses = flight.getItinerary().getOriginDestinationOptions().get(1).getFlightSegments();
                    adapter.addResults(responses);
                }, Throwable::printStackTrace);
    }
}
