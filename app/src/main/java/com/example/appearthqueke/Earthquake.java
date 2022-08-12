package com.example.appearthqueke;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Earthquake implements Parcelable {
    private String id;
    private String place;
    private double magnitud;
    private long time;
    private double latitude;
    private double longitude;

    public Earthquake(String id, String place, Double magnitud, long time, double latitude, double longitude) {
        this.id = id;
        this.place = place;
        this.magnitud = magnitud;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Double getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(Double magnitud) {
        this.magnitud = magnitud;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Earthquake that = (Earthquake) o;
        return Double.compare(that.magnitud, magnitud) == 0 && time == that.time && Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && id.equals(that.id) && place.equals(that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, magnitud, time, latitude, longitude);
    }

    //************************************************************************************************************
    protected Earthquake(Parcel in){
        id = in.readString();
        place = in.readString();
        magnitud = in.readDouble();
        time  = in.readLong();
        latitude = in.readDouble();
        longitude = in.readDouble();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(id);
        dest.writeString(place);
        dest.writeDouble(magnitud);
        dest.writeLong(time);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    public static final Creator<Earthquake> CREATOR = new Creator<Earthquake>() {
        @Override
        public Earthquake createFromParcel(Parcel in) {
            return new Earthquake(in);
        }

        @Override
        public Earthquake[] newArray(int size) {
            return new Earthquake[size];
        }
    };
}
