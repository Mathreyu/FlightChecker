package com.nearsoft.flights.flightchecker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nearsoft.flights.flightchecker.api.FlightsService;
import com.nearsoft.flights.flightchecker.api.dagger.components.flights.DaggerFlightsAPIComponents;
import com.nearsoft.flights.flightchecker.api.dagger.modules.RetrofitModule;
import com.nearsoft.flights.flightchecker.models.APIResponse;
import com.nearsoft.flights.flightchecker.models.OriginDestinationOption;
import com.nearsoft.flights.flightchecker.presenter.FlightsAPI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QueryFlights extends AppCompatActivity {

    @BindView(R.id.departureQueryTime)
    TextView departureQueryTime;

    @BindView(R.id.arrivalQueryTime)
    TextView arrivalQueryTime;

    @BindView(R.id.arrivalAirportInput)
    AutoCompleteTextView arrivalAirportInput;

    @BindView(R.id.departureAirportInput)
    AutoCompleteTextView departureAirportInput;

    @BindView(R.id.search)
    Button searchButton;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    FlightsAPI flightsAPI;

    private Calendar calendar;
    private final SimpleDateFormat simpleDateformat = new SimpleDateFormat("dd - MMM - yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_flights);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Home.getFlightsAPIComponents().inject(this);

        DaggerFlightsAPIComponents.builder()
                .retrofitModule(new RetrofitModule(FlightsService.BASE_URL))
                .build().inject(this);

        calendar = Calendar.getInstance();

        String todayDate = simpleDateformat.format(calendar.getTime());
        departureQueryTime.setText(todayDate);
        arrivalQueryTime.setText(todayDate);

        searchButton.setOnClickListener((View view) -> {
           searchFlights(view);
        });
    }

    protected void openCalendar(View view) {

        DatePickerDialog.OnDateSetListener date = (view1, year, monthOfYear, dayOfMonth) -> {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            switch (view.getId()) {
                case R.id.departureQueryTime:
                    departureQueryTime.setText(simpleDateformat.format(calendar.getTime()));
                    break;
                case R.id.arrivalQueryTime:
                    arrivalQueryTime.setText(simpleDateformat.format(calendar.getTime()));
            }
            //TODO Add date validations e.g. arrivalDate >= departureDate or arrivalDate >= todayDate and departureDate >= todayDate
        };

        new DatePickerDialog(QueryFlights.this, date,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    protected void searchFlights(View view) {

        final int toastLength = Toast.LENGTH_SHORT;

        String departureAirport = departureAirportInput.getText().toString();
        String arrivalAirport = arrivalAirportInput.getText().toString();

        if ("".equals(departureAirport)) {
            Toast.makeText(this, "Select a departure airport", toastLength).show();
            return;
        } else if ("".equals(arrivalAirport)) {
            Toast.makeText(this, "Select an arrival airport", toastLength).show();
            return;
        }

        Observable<APIResponse> flightsByAirports = flightsAPI.searchFlightsByAirports(departureAirport, arrivalAirport);

        flightsByAirports.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flight -> {
                    ArrayList<OriginDestinationOption> flights = new ArrayList<>(flight.getItinerary().getOriginDestinationOptions());
                    Intent intent = new Intent(this, FlightsMain.class);
                    intent.putParcelableArrayListExtra(FlightsMain.FLIGHTS, flights);
                    startActivity(intent);
                }, Throwable::printStackTrace);
    }

}
