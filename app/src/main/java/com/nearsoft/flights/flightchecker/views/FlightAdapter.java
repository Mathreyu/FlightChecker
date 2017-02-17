package com.nearsoft.flights.flightchecker.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nearsoft.flights.flightchecker.R;
import com.nearsoft.flights.flightchecker.models.FlightSegment;
import com.nearsoft.flights.flightchecker.models.OriginDestinationOption;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {

    private List<OriginDestinationOption> flightList;

    public FlightAdapter() {
        flightList = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OriginDestinationOption flight = flightList.get(position);
        holder.bind(flight);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View flightItem = inflater.inflate(R.layout.flight_item, parent, false);
        return new ViewHolder(flightItem);
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public void addResults(List<OriginDestinationOption> flights) {
        flightList.addAll(flights);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.arrivalAirport)
        TextView arrivalAirport;
        @BindView(R.id.departureAirport)
        TextView departureAirport;
        @BindView(R.id.arrivalTime)
        TextView arrivalTime;
        @BindView(R.id.departureTime)
        TextView departureTime;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.flightHeader)
        ImageView flightHeader;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        private void bind(OriginDestinationOption flight) {

            arrivalAirport.setText(flight.getArrivalAirport());
            departureAirport.setText(flight.getDepartureAirport());
            arrivalTime.setText(flight.getArrivalDateTime());
            departureTime.setText(flight.getDepartureDateTime());
            price.setText(flight.getAmount() + " " + flight.getCurrencyCode());

            Picasso.with(itemView.getContext()).load(flight.getArrivalCityImage()).into(flightHeader);
        }
    }
}
