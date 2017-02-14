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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {

    private List<FlightSegment> flightList;

    public FlightAdapter() {
        flightList = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FlightSegment flight = flightList.get(position);
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

    public void addResults(List<FlightSegment> flights) {
        flightList.addAll(flights);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.arrivalAirport)
        TextView arrivalAirport;
        @BindView(R.id.departureAirport)
        TextView departureAirport;
        //TODO: retrive price from DestinationOptions
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.flightNumber)
        TextView flightNumber;
        @BindView(R.id.plane)
        ImageView plane;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        private void bind(FlightSegment flight) {

            arrivalAirport.setText(flight.getArrivalAirport());
            departureAirport.setText(flight.getDepartureAirport());
            flightNumber.setText(String.valueOf(flight.getFlightNumber()));
            price.setText("200");
            Picasso.with(itemView.getContext()).load("https://cdn0.iconfinder.com/data/icons/kameleon-free-pack-rounded/110/Paper-Plane-512.png").into(plane);

        }
    }
}
