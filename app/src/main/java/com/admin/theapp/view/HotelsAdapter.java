package com.admin.theapp.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.admin.theapp.R;
import com.admin.theapp.model.HotelModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.HotelViewHolder> {

    @NonNull
    private final Context           context;
    @Nullable
    private       ItemClickListener onClickCallback;

    @NonNull
    private List<HotelModel> hotels = new ArrayList<>();

    public HotelsAdapter(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item, parent, false);
        final HotelViewHolder holder = new HotelViewHolder(view);
        view.setOnClickListener(v -> {
            if (onClickCallback != null) {
                onClickCallback.onClick(getHotelModel(holder.getAdapterPosition()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(HotelViewHolder holder, int position) {
        HotelModel hotelModel = getHotelModel(position);
        holder.name.setText(hotelModel.getName());
        holder.address.setText(hotelModel.getAddress());
        holder.stars.setText(String.valueOf(hotelModel.getStars()));
        holder.image.setImageDrawable(getImageFromAssets(hotelModel.getImageName()));
    }

    @Nullable
    private Drawable getImageFromAssets(String imageName) {
        try {
            return Drawable.createFromStream(context.getAssets().open(imageName), null);
        } catch (IOException ignored) {
        }
        return null;
    }

    @NonNull
    private HotelModel getHotelModel(int position) {
        return hotels.get(position);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public void setData(List<HotelModel> newList) {
        hotels = newList;
        notifyDataSetChanged();
    }

    public void setOnClickCallback(@Nullable ItemClickListener onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    class HotelViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView  name;
        private final TextView  address;
        private final TextView  stars;

        public HotelViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.hotel_image);
            name = view.findViewById(R.id.hotel_details_name);
            address = view.findViewById(R.id.hotel_address);
            stars = view.findViewById(R.id.hotel_stars);
        }
    }

    public interface ItemClickListener {
        void onClick(HotelModel hotelModel);
    }
}
