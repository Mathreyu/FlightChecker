package com.nearsoft.flights.flightchecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nearsoft.flights.flightchecker.api.FlightApi;
import com.nearsoft.flights.flightchecker.api.dagger.DaggerDIComponents;
import com.nearsoft.flights.flightchecker.api.dagger.RetrofitModule;
import com.nearsoft.flights.flightchecker.models.FlightSegment;
import com.nearsoft.flights.flightchecker.presenter.FlightsMainPresenter;
import com.nearsoft.flights.flightchecker.views.FlightAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlightsMain extends AppCompatActivity {
    @BindView(R.id.rvFlights)
    RecyclerView rvFlights;
    @Inject
    FlightsMainPresenter flightsMainPresenter;

    List<FlightSegment> flightSegments;

    private FlightAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights_main);
        ButterKnife.bind(this);
        DaggerDIComponents.builder().retrofitModule(new RetrofitModule(FlightApi.BASE_URL)).build().inject(this);

        adapter = new FlightAdapter();
        rvFlights.setLayoutManager(new GridLayoutManager(this, 1));
        rvFlights.setAdapter(adapter);
        flightsMainPresenter.getFlights(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
