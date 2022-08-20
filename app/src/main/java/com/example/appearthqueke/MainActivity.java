package com.example.appearthqueke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appearthqueke.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.eqRecycler.setLayoutManager(new LinearLayoutManager(this));


        EqAdapter adapter =  new EqAdapter();

        //adapter.setOnItemClicListener(earthquake ->  Toast.makeText(MainActivity.this, earthquake.getPlace(), Toast.LENGTH_SHORT).show());
       adapter.setOnItemClicListener(earthquake->{

           abrirmonitor(earthquake.getId(), earthquake.getPlace(),earthquake.getMagnitud(),earthquake.getTime(),earthquake.getLatitude(),earthquake.getLongitude());
       });

        binding.eqRecycler.setAdapter(adapter);

        viewModel.getEqList().observe(this,eqList->{
                adapter.submitList(eqList);

            if (eqList.isEmpty()){
                binding.emptyView.setVisibility(View.VISIBLE);
            }else{
                binding.emptyView.setVisibility(View.GONE);
            }


    });
        viewModel.getEarthquakes();


    }

    public void abrirmonitor(String id, String place, double magnitud, long time, double latitud, double longitud){
        Intent intent = new Intent(this, Monitor.class);
        Earthquake terremoto = new Earthquake(id, place, magnitud,time,latitud,longitud);
        intent.putExtra(Monitor.EARTHQUAKE_KEY, terremoto);

        startActivity(intent);
    }
}