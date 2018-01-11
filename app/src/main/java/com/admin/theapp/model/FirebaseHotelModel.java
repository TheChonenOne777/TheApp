package com.admin.theapp.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class FirebaseHotelModel {
    private long   id;
    private String name;
    private String address;
    private double stars;
    private double distance;
    private String suites_availability;
    private double lat;
    private double lon;
    private String image;

    public FirebaseHotelModel() {
    }

    public FirebaseHotelModel(long id, String name, String address, double stars, double distance, String suites_availability, double lat, double lon, String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.stars = stars;
        this.distance = distance;
        this.suites_availability = suites_availability;
        this.lat = lat;
        this.lon = lon;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getSuites_availability() {
        return suites_availability;
    }

    public double getStars() {
        return stars;
    }

    public double getDistance() {
        return distance;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getImage() {
        return image;
    }
}
