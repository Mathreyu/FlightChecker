package com.nearsoft.flights.flightchecker.models;

import java.util.List;

/**
 * Created by Ramon on 2/8/2017.
 */

public class Itinerary {

    private List<OriginDestinationOption> originDestinationOptions;

    public List<OriginDestinationOption> getOriginDestinationOptions() {
        return originDestinationOptions;
    }

    public void setOriginDestinationOptions(List<OriginDestinationOption> originDestinationOptions) {
        this.originDestinationOptions = originDestinationOptions;
    }

}
