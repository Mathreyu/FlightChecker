package com.nearsoft.flights.flightchecker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightSegment implements Parcelable{

    private String airlineCode;
    private String arrivalAirport;
    private String arrivalDateTime;
    private String arrivalTimeZone;
    private String departureAirport;
    private String departureDateTime;
    private String departureTimeZone;
    private Integer flightNumber;
    private Integer stopQuantity;

    protected FlightSegment(Parcel in) {
        airlineCode = in.readString();
        arrivalAirport = in.readString();
        arrivalDateTime = in.readString();
        arrivalTimeZone = in.readString();
        departureAirport = in.readString();
        departureDateTime = in.readString();
        departureTimeZone = in.readString();
        flightNumber = in.readInt();
        stopQuantity = in.readInt();
    }

    public static final Creator<FlightSegment> CREATOR = new Creator<FlightSegment>() {
        @Override
        public FlightSegment createFromParcel(Parcel in) {
            return new FlightSegment(in);
        }

        @Override
        public FlightSegment[] newArray(int size) {
            return new FlightSegment[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(airlineCode);
        dest.writeString(arrivalAirport);
        dest.writeString(arrivalDateTime);
        dest.writeString(arrivalTimeZone);
        dest.writeString(departureAirport);
        dest.writeString(departureDateTime);
        dest.writeString(departureTimeZone);
        dest.writeInt(flightNumber);
        dest.writeInt(stopQuantity);
    }
}
