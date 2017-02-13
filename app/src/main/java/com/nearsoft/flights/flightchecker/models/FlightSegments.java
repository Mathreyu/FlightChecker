package com.nearsoft.flights.flightchecker.models;



public class FlightSegments {
    private String airlineCode;
    private String arrivalAirport;
    private String arrivalDateTime;
    private String arrivalTimeZone;
    private String departureAirport;
    private String departureDateTime;
    private String departureTimeZone;
    private int flightNumber;
    private int stopQuantity;

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

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getStopQuantity() {
        return stopQuantity;
    }

    public void setStopQuantity(int stopQuantity) {
        this.stopQuantity = stopQuantity;
    }
}
