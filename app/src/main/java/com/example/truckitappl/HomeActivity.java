package com.example.truckitappl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTrucks;
    private ImageButton buttonAddDelivery;

    private List<Truck> truckList;
    private TruckAdapter truckAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize RecyclerView
        recyclerViewTrucks = findViewById(R.id.recyclerViewTrucks);
        recyclerViewTrucks.setLayoutManager(new LinearLayoutManager(this));

        // Initialize truck list and adapter
        truckList = new ArrayList<>();
        truckAdapter = new TruckAdapter(truckList);
        recyclerViewTrucks.setAdapter(truckAdapter);

        // Add sample truck data (replace with your actual data retrieval logic)
        addSampleTrucks();

        // Plus button click listener
        buttonAddDelivery = findViewById(R.id.buttonAddDelivery);
        buttonAddDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open "Add a new delivery order" screen
                Intent intent = new Intent(HomeActivity.this, AddDeliveryActivity.class);
                startActivity(intent);
            }
        });
    }

    // Sample method to add sample trucks (replace with your actual data retrieval logic)
    private void addSampleTrucks() {
        truckList.add(new Truck("Truck 1", "Spacious and reliable truck perfect for all your moving needs. Ideal for transporting furniture, appliances, and more.", R.drawable.truck1));
        truckList.add(new Truck("Truck 2", "Efficient and compact truck ideal for quick deliveries. With its agile handling and fuel efficiency, it's perfect for navigating busy city streets", R.drawable.truck2));
        truckList.add(new Truck("Truck 3", "Versatile truck suitable for various cargo types. Its sturdy design and ample storage space make it a great choice for both small and large hauls.", R.drawable.truck3));
        truckAdapter.notifyDataSetChanged();
    }

    // Create options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


    // Handle options menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_home) {
            // Home option selected
            Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.action_account) {
            // Account option selected
            Toast.makeText(this, "Account selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.action_my_orders) {
            // My Orders option selected
            Intent intent = new Intent(HomeActivity.this, MyOrdersActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
