package com.example.truckitappl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddDeliveryActivity extends AppCompatActivity {

    private EditText editTextReceiverName;
    private DatePicker datePickerPickupDate;
    private EditText editTextLocation;
    private Spinner spinnerGoodsType;
    private Spinner spinnerVehicleType;
    private Button buttonCreateDelivery;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delivery);

        editTextReceiverName = findViewById(R.id.editTextReceiverName);
        datePickerPickupDate = findViewById(R.id.datePickerPickupDate);
        editTextLocation = findViewById(R.id.editTextLocation);
        spinnerGoodsType = findViewById(R.id.spinnerGoodsType);
        spinnerVehicleType = findViewById(R.id.spinnerVehicleType);
        buttonCreateDelivery = findViewById(R.id.buttonCreateDelivery);

        // Set up the goods type spinner with options
        ArrayAdapter<CharSequence> goodsTypeAdapter = ArrayAdapter.createFromResource(
                this, R.array.goods_types, android.R.layout.simple_spinner_item);
        goodsTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGoodsType.setAdapter(goodsTypeAdapter);

        // Set up the vehicle type spinner with options
        ArrayAdapter<CharSequence> vehicleTypeAdapter = ArrayAdapter.createFromResource(
                this, R.array.vehicle_types, android.R.layout.simple_spinner_item);
        vehicleTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVehicleType.setAdapter(vehicleTypeAdapter);

        // ...

        buttonCreateDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected values
                String receiverName = editTextReceiverName.getText().toString();
                int day = datePickerPickupDate.getDayOfMonth();
                int month = datePickerPickupDate.getMonth() + 1; // Month is zero-based
                int year = datePickerPickupDate.getYear();
                String location = editTextLocation.getText().toString();
                String goodsType = spinnerGoodsType.getSelectedItem().toString();
                String vehicleType = spinnerVehicleType.getSelectedItem().toString();



                // Pass the new delivery order information back to MyOrdersActivity
                Intent intent = new Intent();
                intent.putExtra("receiverName", receiverName);
                intent.putExtra("day", day);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                intent.putExtra("location", location);
                intent.putExtra("goodsType", goodsType);
                intent.putExtra("vehicleType", vehicleType);
                setResult(RESULT_OK, intent);

                // Show a toast message to indicate the successful creation of the delivery order
                Toast.makeText(AddDeliveryActivity.this, "Delivery created", Toast.LENGTH_SHORT).show();

                // Finish the activity and return to the previous screen
                finish();
            }
        });

    }
}

