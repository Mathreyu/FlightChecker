package com.nearsoft.flights.flightchecker.models;

public class Itinerary {
    private OriginDestinationOption[] originDestinations;

    public OriginDestinationOption[] getOriginDestinations() {
        return originDestinations;
    }

    public void setOriginDestinations(OriginDestinationOption[] originDestinations) {
        this.originDestinations = originDestinations;
    }
}
