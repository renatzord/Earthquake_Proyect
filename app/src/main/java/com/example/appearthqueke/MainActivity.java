package com.example.appearthqueke;

import androidx.appcompat.app.AppCompatActivity;
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

        binding.eqRecycler.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Earthquake> eqlist = new ArrayList<>();
        eqlist.add(new Earthquake("001", "Carci - Tulcan",+5.0, 12082022, 1005, 154.0));
        eqlist.add(new Earthquake("002", "Cuenca - Azuay",+5.0, 12082022, 1005, 154.0));
        eqlist.add(new Earthquake("003", "Quito - Pichincha",+5.0, 12082022, 1005, 154.0));
        eqlist.add(new Earthquake("004", "Manta - Manabi",+5.0, 12082022, 1005, 154.0));

        EqAdapter adapter =  new EqAdapter();

        //adapter.setOnItemClicListener(earthquake ->  Toast.makeText(MainActivity.this, earthquake.getPlace(), Toast.LENGTH_SHORT).show());
       adapter.setOnItemClicListener(earthquake->{
           //String magnitud =
           /*for (int i = 0; i < eqlist.size(); i++)
            abrirmonitor(eqlist.get(i).getId(), eqlist.get(i).getPlace(), eqlist.get(i).getMagnitud(),eqlist.get(i).getTime(),
                            eqlist.get(i).getLatitude(), eqlist.get(i).getLongitude());*/
           abrirmonitor(earthquake.getId(), earthquake.getPlace(),earthquake.getMagnitud(),earthquake.getTime(),earthquake.getLatitude(),earthquake.getLongitude());
       });

        binding.eqRecycler.setAdapter(adapter);
        adapter.submitList(eqlist);

        if (eqlist.isEmpty()){
            binding.emptyView.setVisibility(View.VISIBLE);
        }else{
            binding.emptyView.setVisibility(View.GONE);
        }

    }

    public void abrirmonitor(String id, String place, double magnitud, long time, double latitud, double longitud){
        Intent intent = new Intent(this, Monitor.class);
        Earthquake terremoto = new Earthquake(id, place, magnitud,time,latitud,longitud);
        intent.putExtra(Monitor.EARTHQUAKE_KEY, terremoto);

        startActivity(intent);
    }
}