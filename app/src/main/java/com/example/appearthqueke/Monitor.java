package com.example.appearthqueke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appearthqueke.databinding.ActivityMonitorBinding;

public class Monitor extends AppCompatActivity {
    public static final String EARTHQUAKE_KEY = "Earthquake";

    ActivityMonitorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMonitorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        Earthquake terremoto = extras.getParcelable(EARTHQUAKE_KEY);
        binding.setMonitor(terremoto);

        String lat = String.valueOf(terremoto.getLatitude());
        String lon = String.valueOf(terremoto.getLongitude());
        String tim = String.valueOf(terremoto.getTime());

        binding.magnitudMonitor.setText(terremoto.getMagnitud().toString());
        binding.LatitudMonitor.setText("Latitud: "+lat+"°");
        binding.longitudMonitor.setText("Longitud: "+lon+"°");
        binding.placeMonitor.setText(terremoto.getPlace());
        binding.timeMonitor.setText(tim);
    }
}