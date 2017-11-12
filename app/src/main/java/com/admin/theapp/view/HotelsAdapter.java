package com.admin.theapp.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.admin.theapp.R;
import com.admin.theapp.model.HotelModel;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.HotelViewHolder> {

    @NonNull
    private static final String PATH_TO_IMAGE = "hotels/";
    private static final long   ONE_MEGABYTE  = 1024 * 1024;

    @Nullable
    private ItemClickListener onClickCallback;
    @NonNull
    private Context           context;

    @NonNull
    private final StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    HotelsAdapter(@NonNull Context context) {
        this.context = context;
    }

    @NonNull
    private List<HotelModel> hotels = new ArrayList<>();

    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        HotelModel hotelModel = getHotelModel(position);
        holder.name.setText(hotelModel.getName());
        holder.address.setText(hotelModel.getAddress());
        holder.stars.setText(String.valueOf(hotelModel.getStars()));
        if (hotelModel.getImageName() != null) {
            setImageToHolder(hotelModel.getImageName(), holder);
        }
    }

    private void setImageToHolder(@NonNull String imageName, @NonNull HotelViewHolder holder) {
        storageReference.child(PATH_TO_IMAGE + imageName)
                        .getBytes(ONE_MEGABYTE)
                        .addOnSuccessListener(bytes -> holder.image.setImageDrawable(new BitmapDrawable(
                                context.getResources(), BitmapFactory.decodeByteArray(bytes, 0, bytes.length))
                        )).addOnFailureListener(e -> Log.e("Failed to load image: ", e.getMessage()));
    }

    @NonNull
    private HotelModel getHotelModel(int position) {
        return hotels.get(position);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public void setData(@NonNull List<HotelModel> newList) {
        hotels = newList;
        notifyDataSetChanged();
    }

    void setOnClickCallback(@Nullable ItemClickListener onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    class HotelViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView  name;
        private final TextView  address;
        private final TextView  stars;

        HotelViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.hotel_image);
            name = view.findViewById(R.id.hotel_details_name);
            address = view.findViewById(R.id.hotel_address);
            stars = view.findViewById(R.id.hotel_stars);
        }
    }

    public interface ItemClickListener {
        void onClick(@NonNull HotelModel hotelModel);
    }
}
