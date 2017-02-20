package com.nearsoft.flights.flightchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.nearsoft.flights.flightchecker.api.FlightsService;
import com.nearsoft.flights.flightchecker.api.dagger.components.flights.DaggerFlightsAPIComponents;
import com.nearsoft.flights.flightchecker.api.dagger.components.flights.FlightsAPIComponents;
import com.nearsoft.flights.flightchecker.api.dagger.modules.RetrofitModule;
import com.nearsoft.flights.flightchecker.api.dagger.modules.flights.FlightsAPIModule;
import com.nearsoft.flights.flightchecker.models.APIResponse;
import com.nearsoft.flights.flightchecker.models.OriginDestinationOption;
import com.nearsoft.flights.flightchecker.presenter.FlightsAPI;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static FlightsAPIComponents flightsAPIComponents;

    @BindView(R.id.buttonAllFlights)
    Button buttonAllFlights;

    @BindView(R.id.buttonQueryFlights)
    FloatingActionButton buttonQueryFlights;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Inject
    FlightsAPI flightsAPI;

    public static FlightsAPIComponents getFlightsAPIComponents() {
        return flightsAPIComponents;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        flightsAPIComponents = DaggerFlightsAPIComponents.builder()
                .retrofitModule(new RetrofitModule(FlightsService.BASE_URL))
                .build();

        flightsAPIComponents.inject(this);

        buttonQueryFlights.setOnClickListener(this::queryFlights);
        buttonAllFlights.setOnClickListener(this::getAllFlights);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void queryFlights(View view) {
        startActivity(new Intent(this, QueryFlights.class));
    }

    protected void getAllFlights(View view) {
        Observable<APIResponse> allFlights = flightsAPI.getAllFlights();

        allFlights.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flight -> {
                    ArrayList<OriginDestinationOption> flights = new ArrayList<>(flight.getItinerary().getOriginDestinationOptions());
                    Intent intent = new Intent(this, FlightsMain.class);
                    intent.putParcelableArrayListExtra(FlightsMain.FLIGHTS, flights);
                    startActivity(intent);
                }, Throwable::printStackTrace);
    }

}
