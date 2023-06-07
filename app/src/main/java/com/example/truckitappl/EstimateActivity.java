package com.example.truckitappl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EstimateActivity extends AppCompatActivity {

    private EditText editTextPickup;
    private EditText editTextDropOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);

        editTextPickup = findViewById(R.id.editTextPickup);
        editTextDropOff = findViewById(R.id.editTextDropOff);

        Button buttonMap = findViewById(R.id.buttonMap);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pickupLocation = editTextPickup.getText().toString();
                String dropOffLocation = editTextDropOff.getText().toString();

                openMap(pickupLocation, dropOffLocation);
            }
        });

        Button buttonBookNow = findViewById(R.id.buttonBookNow);
        buttonBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookNow();
            }
        });

        Button buttonCallDriver = findViewById(R.id.buttonCallDriver);
        buttonCallDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDriver();
            }
        });
    }

    private void openMap(String pickupLocation, String dropOffLocation) {
        // Open the map with the provided pickup and drop-off locations
        Uri mapUri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=" + pickupLocation + "&destination=" + dropOffLocation);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "Map application not found.", Toast.LENGTH_SHORT).show();
        }
    }

    private void bookNow() {
        // Perform booking logic and navigate to the payment page

        // For demonstration purposes, show a toast message
        Toast.makeText(this, "Booking now...", Toast.LENGTH_SHORT).show();

        // You can integrate with a payment API like Google Pay API, Stripe, PayPal, etc.
        // Redirect the user to the payment page or perform the necessary payment processing

        // Example: Redirect to PaymentActivity
        Intent paymentIntent = new Intent(EstimateActivity.this, PaymentsActivity.class);
        startActivity(paymentIntent);
    }


    private void callDriver() {
        // Perform the logic to call the driver
        // Here, we are just opening the dialer with the driver's phone number
        String phoneNumber = "1234567890";
        Uri callUri = Uri.parse("tel:" + phoneNumber);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, callUri);
        startActivity(callIntent);
    }
}

