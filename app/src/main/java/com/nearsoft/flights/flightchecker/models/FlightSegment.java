package com.nearsoft.flights.flightchecker.models;

/**
 * Created by Ramon on 2/8/2017.
 */

public class FlightSegment {

    private String airlineCode;
    private String arrivalAirport;
    private String arrivalDateTime;
    private String arrivalTimeZone;
    private String departureAirport;
    private String departureDateTime;
    private String departureTimeZone;
    private Integer flightNumber;
    private Integer stopQuantity;

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getArrivalTimeZone() {
        return arrivalTimeZone;
    }

    public void setArrivalTimeZone(String arrivalTimeZone) {
        this.arrivalTimeZone = arrivalTimeZone;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getDepartureTimeZone() {
        return departureTimeZone;
    }

    public void setDepartureTimeZone(String departureTimeZone) {
        this.departureTimeZone = departureTimeZone;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Integer getStopQuantity() {
        return stopQuantity;
    }

    public void setStopQuantity(Integer stopQuantity) {
        this.stopQuantity = stopQuantity;
    }

}
