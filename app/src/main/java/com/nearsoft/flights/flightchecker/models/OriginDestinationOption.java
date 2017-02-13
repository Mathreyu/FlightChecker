package com.nearsoft.flights.flightchecker.models;

import java.util.List;


public class OriginDestinationOption {
    private List<FlightSegments> flightSegments;
    private int elapsedTime;
    private double amount;
    private String currencyCode;

    public List<FlightSegments> getFlightSegmentses() {
        return flightSegments;
    }

    public void setFlightSegmentses(List<FlightSegments> flightSegments) {
        this.flightSegments = flightSegments;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
