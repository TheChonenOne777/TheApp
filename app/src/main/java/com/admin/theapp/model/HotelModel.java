package com.admin.theapp.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class HotelModel {
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String address;
    private int stars;
    private double distance;
    @Nullable
    private String image;
    @Nullable
    private String suitesAvailability;
    private double lat;
    private double lon;

    public HotelModel() {
        this.name = "";
        this.address = "";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    public void setImage(@Nullable String image) {
        this.image = image;
    }

    @Nullable
    public String getSuitesAvailability() {
        return suitesAvailability;
    }

    public void setSuitesAvailability(@Nullable String suitesAvailability) {
        this.suitesAvailability = suitesAvailability;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final HotelModel that = (HotelModel) o;

        if (id != that.id) {
            return false;
        }
        if (Double.compare(that.lat, lat) != 0) {
            return false;
        }
        if (Double.compare(that.lon, lon) != 0) {
            return false;
        }
        if (!name.equals(that.name)) {
            return false;
        }
        return address.equals(that.address);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.format("HotelModel{id=%d," +
                                     "\nname='%s'," +
                                     "\naddress='%s'," +
                                     "\nstars=%d," +
                                     "\ndistance=%s," +
                                     "\nimage='%s'," +
                                     "\nsuitesAvailability='%s'," +
                                     "\nlat=%s," +
                                     "\nlon=%s}",
                             id, name, address, stars, distance, image, suitesAvailability, lat, lon);
    }
}
