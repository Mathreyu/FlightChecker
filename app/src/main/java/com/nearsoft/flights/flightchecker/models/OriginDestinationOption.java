package com.nearsoft.flights.flightchecker.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class OriginDestinationOption implements Parcelable{

    private String arrivalAirport;
    private String departureAirport;
    private Double amount;
    private String currencyCode;
    private Integer elapsedTime;
    private List<FlightSegment> flightSegments;

    protected OriginDestinationOption(Parcel in) {
        arrivalAirport = in.readString();
        departureAirport = in.readString();
        amount = in.readDouble();
        currencyCode = in.readString();
        elapsedTime = in.readInt();
        flightSegments = new ArrayList<>();
        in.readTypedList(flightSegments, FlightSegment.CREATOR);
    }

    public static final Creator<OriginDestinationOption> CREATOR = new Creator<OriginDestinationOption>() {
        @Override
        public OriginDestinationOption createFromParcel(Parcel in) {
            return new OriginDestinationOption(in);
        }

        @Override
        public OriginDestinationOption[] newArray(int size) {
            return new OriginDestinationOption[size];
        }
    };

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(arrivalAirport);
        dest.writeString(departureAirport);
        dest.writeDouble(amount);
        dest.writeString(currencyCode);
        dest.writeInt(elapsedTime);
        dest.writeTypedList(flightSegments);
    }

}
