package com.admin.theapp.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class HotelModel {
    private final long   id;
    @NonNull
    private final String name;
    @NonNull
    private final String address;
    private final double stars;
    private final double distance;
    @Nullable
    private final String imageName;
    @Nullable
    private final String suitesAvailability;
    private final double lat;
    private final double lon;

    public HotelModel(long id,
                      @NonNull String name,
                      @NonNull String address,
                      double stars,
                      double distance,
                      @Nullable String imageName,
                      @Nullable String suitesAvailability,
                      double lat,
                      double lon) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.stars = stars;
        this.distance = distance;
        this.imageName = imageName;
        this.suitesAvailability = suitesAvailability;
        this.lat = lat;
        this.lon = lon;
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

    public double getDistance() {
        return distance;
    }

    @Nullable
    public String getImageName() {
        return imageName;
    }

    @Nullable
    public String getSuitesAvailability() {
        return suitesAvailability;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
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

        return id == that.id
                && Double.compare(that.stars, stars) == 0
                && Double.compare(that.distance, distance) == 0
                && Double.compare(that.lat, lat) == 0 && Double.compare(that.lon, lon) == 0
                && name.equals(that.name)
                && address.equals(that.address)
                && (imageName != null ? imageName.equals(that.imageName) : that.imageName == null)
                && (suitesAvailability != null ? suitesAvailability.equals(that.suitesAvailability) : that.suitesAvailability == null);
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
}
