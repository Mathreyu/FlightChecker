package com.nearsoft.flights.flightchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nearsoft.flights.flightchecker.models.OriginDestinationOption;
import com.nearsoft.flights.flightchecker.presenter.FlightsAPI;
import com.nearsoft.flights.flightchecker.views.FlightAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlightsMain extends AppCompatActivity {

    @BindView(R.id.rvFlights)
    RecyclerView rvFlights;

    @Inject
    FlightsAPI flightsAPI;

    private FlightAdapter adapter;


    static final String FLIGHTS = "com.nearsoft.flights.flightchecker.flightsMain.flights";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights_main);
        ButterKnife.bind(this);

        adapter = new FlightAdapter();
        rvFlights.setLayoutManager(new GridLayoutManager(this, 1));
        rvFlights.setAdapter(adapter);

        Intent intent = getIntent();
        ArrayList<OriginDestinationOption> flights = intent.getParcelableArrayListExtra(FLIGHTS);

        adapter.addResults(flights);
        flightsAPI.getAllFlights(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /*public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(FlightsService.BASE_URL)
                .build();
    }

    public void getFlights(final FlightAdapter adapter){
        Observable<APIResponse> apiService = flightApi.getItinerary(1);

        apiService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flight -> {
                    List<OriginDestinationOption> responses = flight.getItinerary().getOriginDestinationOptions();
                    adapter.addResults(responses);
                }, Throwable::printStackTrace);
    }*/

}
