package com.example.truckitappl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyOrderViewHolder> {

    private List<Truck> myOrdersList;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(Truck order);
    }

    public MyOrderAdapter(List<Truck> myOrdersList) {
        this.myOrdersList = myOrdersList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item, parent, false);
        return new MyOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder holder, int position) {
        Truck order = myOrdersList.get(position);

        holder.imageViewTruck.setImageResource(order.getImageResource());
        holder.textViewTruckName.setText(order.getName());
        holder.textViewTruckDetails.setText(order.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(order);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return myOrdersList.size();
    }

    static class MyOrderViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewTruck;
        TextView textViewTruckName, textViewTruckDetails;

        public MyOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewTruck = itemView.findViewById(R.id.imageViewTruck);
            textViewTruckName = itemView.findViewById(R.id.textViewName);
            textViewTruckDetails = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
