package com.example.appearthqueke.API;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

//Sigleton '  Se mantiene aCTIVO'
public class EqApiClient {
    public static final  EqApiClient ourInstace = new EqApiClient();
    private EqService service;

    public static EqApiClient getInstance(){ return  ourInstace;}
    private  EqApiClient(){

    }
// Cambiar e nombre de la calse con refractor
    public interface EqService {
        @GET("all_hour.geojson")
        Call<String> getEarthquakes();
    }
//Importar librerias de retrofit y get con alt + Enter

    private final Retrofit retrofit = new Retrofit.Builder() //Le hicimos metodo
            .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
            .addConverterFactory(ScalarsConverterFactory.create())
            //.addConverterFactory(MoshiConverterFactory.create())
            .build();

    public EqService getService(){
        if(service == null){
             service = retrofit.create(EqService.class);
        }
        return  service;
    }


}
