package com.nearsoft.flights.flightchecker.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Itinerary {

    @SerializedName("originDestinations")
    private List<OriginDestinationOption> originDestinationOptions;

    public List<OriginDestinationOption> getOriginDestinationOptions() {
        return originDestinationOptions;
    }

    public void setOriginDestinationOptions(List<OriginDestinationOption> originDestinationOptions) {
        this.originDestinationOptions = originDestinationOptions;
    }
}
