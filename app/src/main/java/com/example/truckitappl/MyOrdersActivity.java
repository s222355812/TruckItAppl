package com.example.truckitappl;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMyOrders;
    private MyOrderAdapter myOrderAdapter;
    private List<Truck> myOrdersList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        recyclerViewMyOrders = findViewById(R.id.recyclerViewOrders);
        recyclerViewMyOrders.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);

        myOrdersList = getMyOrders(); // Retrieve the list of delivery orders

        // Create and set the adapter
        myOrderAdapter = new MyOrderAdapter(myOrdersList);
        recyclerViewMyOrders.setAdapter(myOrderAdapter);

        myOrderAdapter.setOnItemClickListener(order -> {
            // Open OrderDetailsActivity for the selected order
            Intent intent = new Intent(MyOrdersActivity.this, OrderDetailsActivity.class);
            intent.putExtra("pickupDetails", order.getName());
            intent.putExtra("goodsDescription", order.getDescription());
            intent.putExtra("truckImageResource", order.getImageResource());
            startActivity(intent);
        });

    }

    private List<Truck> getMyOrders() {
        List<Truck> orders = new ArrayList<>();


        orders.add(new Truck("Order 1", "OrderType:Furniture, Vehicle:Truck", R.drawable.truck1));


        return orders;
    }
}
