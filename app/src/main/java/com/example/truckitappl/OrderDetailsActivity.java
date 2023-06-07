package com.example.truckitappl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailsActivity extends AppCompatActivity {

    private ImageView imageViewTruck;
    private TextView textViewPickupDetails;
    private TextView textViewGoodsDescription;
    private Button buttonEstimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        imageViewTruck = findViewById(R.id.imageViewTruck);
        textViewPickupDetails = findViewById(R.id.textViewPickupDetails);
        textViewGoodsDescription = findViewById(R.id.textViewGoodsDescription);
        buttonEstimate = findViewById(R.id.buttonEstimate);

        // Get the order details from the intent
        Intent intent = getIntent();
        String pickupDetails = intent.getStringExtra("pickupDetails");
        String goodsDescription = intent.getStringExtra("goodsDescription");
        int truckImageResource = intent.getIntExtra("truckImageResource", R.drawable.truck1);

        // Set the order details in the views
        imageViewTruck.setImageResource(truckImageResource);
        textViewPickupDetails.setText(pickupDetails);
        textViewGoodsDescription.setText(goodsDescription);

        // Set a click listener on the "Estimate" button
        buttonEstimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEstimateActivity();
            }
        });
    }

    private void openEstimateActivity() {

        String pickupLocation = "Melbourne,Australia";
        String dropOffLocation = "Perth,Australia";


        Intent intent = new Intent(OrderDetailsActivity.this, EstimateActivity.class);
        intent.putExtra("pickupLocation", pickupLocation);
        intent.putExtra("dropOffLocation", dropOffLocation);
        startActivity(intent);
    }
}
