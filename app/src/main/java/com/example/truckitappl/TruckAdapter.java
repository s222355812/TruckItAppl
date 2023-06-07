package com.example.truckitappl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TruckAdapter extends RecyclerView.Adapter<TruckAdapter.TruckViewHolder> {

    private List<Truck> truckList;

    public TruckAdapter(List<Truck> truckList) {
        this.truckList = truckList;
    }

    @NonNull
    @Override
    public TruckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_truck, parent, false);
        return new TruckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruckViewHolder holder, int position) {
        Truck truck = truckList.get(position);

        holder.imageViewTruck.setImageResource(truck.getImageResource());
        holder.textViewName.setText(truck.getName());
        holder.textViewDescription.setText(truck.getDescription());
    }

    @Override
    public int getItemCount() {
        return truckList.size();
    }

    static class TruckViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewTruck;
        TextView textViewName;
        TextView textViewDescription;

        public TruckViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewTruck = itemView.findViewById(R.id.imageViewTruck);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}

