package com.nearsoft.flights.flightchecker;

import com.nearsoft.flights.flightchecker.api.FlightApi;
import com.nearsoft.flights.flightchecker.models.APIResponse;
import com.nearsoft.flights.flightchecker.models.FlightSegment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


@Config(constants = BuildConfig.class, sdk = 21,
        manifest = "app/src/main/AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)

public class RetrofitTests {
   private FlightsMain main;
    private List<FlightSegment> mockList;

    @Mock
    private FlightApi mockApi;

    @Captor
    private ArgumentCaptor<List<FlightSegment>> callbackArgumentCaptor;

    @Before
    public void setUp(){
        Observable<APIResponse> apiService = mockApi.getItinerary(1);

        apiService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flight -> {
                    List<FlightSegment> responses = flight.getItinerary().getOriginDestinationOptions().get(0).getFlightSegments();
                    mockList.addAll(responses);
                }, Throwable::printStackTrace);
    }

    @Test
    public void shouldFillAdapter() throws Exception {
        Mockito.verify(mockApi)
                .getItinerary(1);

        int objectQuantity = 2;

    }
}