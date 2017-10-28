package com.admin.theapp.parser;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.admin.theapp.model.HotelModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    @NonNull
    private final Context context;

    public JSONParser(@NonNull Context context) {
        this.context = context;
    }

    @NonNull
    public HotelModel parseJson(@NonNull String fileName) {
        final HotelModel output = new HotelModel();
        try {
            final JSONObject json = new JSONObject(readJson(fileName));
            modifyHotelModel(output, json);
            output.setImageName(json.getString("image"));
            output.setLat(json.getDouble("lat"));
            output.setLon(json.getDouble("lon"));
        } catch (JSONException ignored) {
        }
        return output;
    }

    private void modifyHotelModel(@NonNull HotelModel output, @NonNull JSONObject json) throws JSONException {
        output.setId(json.getInt("id"));
        output.setName(json.getString("name"));
        output.setAddress(json.getString("address"));
        output.setStars(json.getDouble("stars"));
        output.setDistance(json.getDouble("distance"));
        output.setSuitesAvailability(json.getString("suites_availability"));
    }

    @NonNull
    public List<HotelModel> parseJsonsFromArray(@NonNull String fileName) {
        final List<HotelModel> output = new ArrayList<>();
        try {
            final JSONArray jsons = new JSONArray(readJson(fileName));
            for (int i = 0; i < jsons.length(); i++) {
                JSONObject hotelJson = jsons.getJSONObject(i);
                final HotelModel hotelModel = new HotelModel();
                modifyHotelModel(hotelModel, hotelJson);
                output.add(hotelModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return output;
    }

    @NonNull
    private String readJson(@NonNull String fileName) {
        final AssetManager assetManager = context.getAssets();
        final StringBuilder output = new StringBuilder();
        try (InputStream inputStream = assetManager.open(fileName)) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (IOException ignored) {
        }
        return output.toString();
    }
}
