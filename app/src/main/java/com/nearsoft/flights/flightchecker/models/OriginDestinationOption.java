package com.nearsoft.flights.flightchecker.models;

import java.util.List;

/**
 * Created by Ramon on 2/8/2017.
 */

public class OriginDestinationOption {
    private List<FlightSegment> flightSegments;
    private int elapsedTime;
    private TotalFare totalFare;

    public List<FlightSegment> getFlightSegments() {
        return flightSegments;
    }

    public void setFlightSegments(List<FlightSegment> flightSegments) {
        this.flightSegments = flightSegments;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public TotalFare getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(TotalFare totalFare) {
        this.totalFare = totalFare;
    }
}
