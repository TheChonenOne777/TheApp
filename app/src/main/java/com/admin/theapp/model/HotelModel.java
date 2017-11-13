package com.admin.theapp.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class HotelModel {
    private long   id;
    @NonNull
    private String name;
    @NonNull
    private String address;
    private double stars;
    private double distance;
    @Nullable
    private String imageName;
    @Nullable
    private String suitesAvailability;
    private double lat;
    private double lon;

    public HotelModel() {
        this.name = "";
        this.address = "";
        this.imageName = "";
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    public long getId() {
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

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Nullable
    public String getImageName() {
        return imageName;
    }

    public void setImageName(@NonNull String imageName) {
        this.imageName = imageName;
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
        if (Double.compare(that.stars, stars) != 0) {
            return false;
        }
        if (Double.compare(that.distance, distance) != 0) {
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
        if (!address.equals(that.address)) {
            return false;
        }
        if (imageName != null ? !imageName.equals(that.imageName) : that.imageName != null) {
            return false;
        }
        return suitesAvailability != null ? suitesAvailability.equals(that.suitesAvailability) : that.suitesAvailability == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        temp = Double.doubleToLongBits(stars);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(distance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (imageName != null ? imageName.hashCode() : 0);
        result = 31 * result + (suitesAvailability != null ? suitesAvailability.hashCode() : 0);
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.format("HotelModel{id=%d, name='%s', address='%s', stars=%s, distance=%s, imageName='%s', suitesAvailability='%s', lat=%s, lon=%s}", id, name, address, stars, distance, imageName, suitesAvailability, lat, lon);
    }
}
