package com.nearsoft.flights.flightchecker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nearsoft.flights.flightchecker.api.FlightApi;
import com.nearsoft.flights.flightchecker.api.dagger.DaggerDIComponents;
import com.nearsoft.flights.flightchecker.api.dagger.RetrofitModule;
import com.nearsoft.flights.flightchecker.presenter.QueryFlightsPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Inject
    QueryFlightsPresenter queryFlightsPresenter;

    private Calendar calendar;
    private final SimpleDateFormat simpleDateformat = new SimpleDateFormat("dd - MMM - yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_flights);

        ButterKnife.bind(QueryFlights.this);
        DaggerDIComponents.builder().retrofitModule(new RetrofitModule(FlightApi.BASE_URL)).build().inject(this);

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
        queryFlightsPresenter.searchRetrofit(departureAirport, arrivalAirport);

        Intent intent = new Intent(this, FlightsMain.class);
        startActivity(intent);
    }

}
