package com.nearsoft.flights.flightchecker.models;

import java.util.List;

/**
 * Created by Ramon on 2/8/2017.
 */

public class OriginDestinationOption {

    private Double amount;
    private String currencyCode;
    private Integer elapsedTime;
    private List<FlightSegment> flightSegments;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public List<FlightSegment> getFlightSegments() {
        return flightSegments;
    }

    public void setFlightSegments(List<FlightSegment> flightSegments) {
        this.flightSegments = flightSegments;
    }

}
