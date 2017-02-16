package com.nearsoft.flights.flightchecker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.nearsoft.flights.flightchecker.api.FlightApi;
import com.nearsoft.flights.flightchecker.models.APIResponse;
import com.nearsoft.flights.flightchecker.models.OriginDestinationOption;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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

    private Calendar calendar;
    private final SimpleDateFormat simpleDateformat = new SimpleDateFormat("dd - MMM - yyyy", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_flights);

        ButterKnife.bind(QueryFlights.this);
        calendar = Calendar.getInstance();

        String todayDate = simpleDateformat.format(calendar.getTime());
        departureQueryTime.setText(todayDate);
        arrivalQueryTime.setText(todayDate);
    }

    protected void openCalendar(View view) {

        DatePickerDialog.OnDateSetListener date = (view1, year, monthOfYear, dayOfMonth) -> {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            switch(view.getId()) {
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

    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(FlightApi.BASE_URL)
                .build();
    }

    protected void searchFlights(View view) {

        final int toastLenght = Toast.LENGTH_SHORT;

        if ("".equals(departureAirportInput.getText().toString())) {
            Toast.makeText(this, "Select a departure airport", toastLenght).show();
            return;
        } else if ("".equals(arrivalAirportInput.getText().toString())) {
            Toast.makeText(this, "Select an arrival airport", toastLenght).show();
            return;
        }

        final Retrofit retrofit = provideRetrofit();
        final FlightApi flightApi = retrofit.create(FlightApi.class);

        Observable<APIResponse> apiService = flightApi.searchFlights(
                departureAirportInput.getText().toString(),
                arrivalAirportInput.getText().toString());

        apiService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flight -> {
                    ArrayList<OriginDestinationOption> flights = new ArrayList<>(flight.getItinerary().getOriginDestinationOptions());
                    Intent intent = new Intent(this, FlightsMain.class);
                    intent.putParcelableArrayListExtra(FlightsMain.FLIGHTS, flights);
                    startActivity(intent);
                }, Throwable::printStackTrace);

    }

}
