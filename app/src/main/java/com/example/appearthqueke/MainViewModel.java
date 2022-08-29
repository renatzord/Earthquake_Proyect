package com.example.appearthqueke;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appearthqueke.API.EqApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {


    public LiveData<List<Earthquake>> getEqList() {
        return eqList;
    }
    private MutableLiveData<List<Earthquake>> eqList = new MutableLiveData<>();



    public void getEarthquakes(){
        /*ArrayList<Earthquake> eqList = new ArrayList<>();
        eqList.add(new Earthquake("001", "Carchi - Tulcan",+5.0, 12082022, 1005, 154.0));
        eqList.add(new Earthquake("002", "Cuenca - Azuay",+5.0, 12082022, 1005, 154.0));
        eqList.add(new Earthquake("003", "Quito - Pichincha",+5.0, 12082022, 1005, 154.0));
        eqList.add(new Earthquake("004", "Manta - Manabi",+5.0, 12082022, 1005, 154.0));

        this.eqList.setValue(eqList);*/

//Acceder al sevicio
        EqApiClient.EqService service = EqApiClient.getInstance().getService();
        service.getEarthquakes().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Log.d("MainViewModel", response.body());
              List<Earthquake> earthquakeslist= parseEarthquake(response.body());
              eqList.setValue(earthquakeslist);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Error de Datos", t.getMessage());
            }
        });

    }


    private List<Earthquake> parseEarthquake(String responseString){
        ArrayList<Earthquake> eqlist = new ArrayList<>();
        try {
            JSONObject jsonResponce = new JSONObject(responseString);
            JSONArray featuresJSONArray = jsonResponce.getJSONArray("features");
            for(int i=0;i<featuresJSONArray.length();i++){
                JSONObject jasonFeatures = featuresJSONArray.getJSONObject(i);
                String id = jasonFeatures.getString("id");

                JSONObject jsonPropeties = jasonFeatures.getJSONObject("properties");
                double madnitude = jsonPropeties.getDouble("mag");
                DecimalFormat df = new DecimalFormat("#.00");
                double R = madnitude;
                df.format(R);
                String place = jsonPropeties.getString("place");
                long time = jsonPropeties.getLong("time");

                JSONObject jsonGeometry = jasonFeatures.getJSONObject("geometry");
                JSONArray coordinatesJSONArray = jsonGeometry.getJSONArray("coordinates");
                double longitude = coordinatesJSONArray.getDouble(0);
                double latitude = coordinatesJSONArray.getDouble(1);

                Earthquake earthquake= new Earthquake(id, place,R,time,latitude,longitude);
                eqlist.add(earthquake);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return eqlist;
    }


}
