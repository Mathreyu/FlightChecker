package com.nearsoft.flights.flightchecker.models;

/**
 * Created by Ramon on 2/8/2017.
 */

public class FlightSegment {

    private Airport departureAirport;
    private Airport arrivalAirport;
    private MarketingAirline marketingAirline;
    private Timezone arrivalTimeZone;
    private Timezone departureTimeZone;
    private int stopQuantity;
    private int elapsedTime;
    private Equipment equipment;
    private String departureDateTime;
    private String arrivalDateTime;
    private OperatingAirline operatingAirline;

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public MarketingAirline getMarketingAirline() {
        return marketingAirline;
    }

    public void setMarketingAirline(MarketingAirline marketingAirline) {
        this.marketingAirline = marketingAirline;
    }

    public Timezone getArrivalTimeZone() {
        return arrivalTimeZone;
    }

    public void setArrivalTimeZone(Timezone arrivalTimeZone) {
        this.arrivalTimeZone = arrivalTimeZone;
    }

    public Timezone getDepartureTimeZone() {
        return departureTimeZone;
    }

    public void setDepartureTimeZone(Timezone departureTimeZone) {
        this.departureTimeZone = departureTimeZone;
    }

    public int getStopQuantity() {
        return stopQuantity;
    }

    public void setStopQuantity(int stopQuantity) {
        this.stopQuantity = stopQuantity;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public OperatingAirline getOperatingAirline() {
        return operatingAirline;
    }

    public void setOperatingAirline(OperatingAirline operatingAirline) {
        this.operatingAirline = operatingAirline;
    }
}
