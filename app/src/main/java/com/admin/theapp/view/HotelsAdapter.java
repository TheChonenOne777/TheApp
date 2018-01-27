package com.admin.theapp.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.admin.theapp.R;
import com.admin.theapp.interactors.DataInteractor;
import com.admin.theapp.ui.widget.StarsView;
import com.admin.theapp.utils.Decoder;
import com.theapp.entities.HotelModel;
import com.theapp.tools.adapters.DisposableMaybeObserverAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.HotelViewHolder> {

    @NonNull
    private final Decoder        decoder;
    @NonNull
    private final DataInteractor dataInteractor;

    @Nullable
    private ItemClickListener onClickCallback;

    @Inject
    HotelsAdapter(@NonNull Decoder decoder,
                  @NonNull DataInteractor dataInteractor) {
        this.decoder = decoder;
        this.dataInteractor = dataInteractor;
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
        holder.stars.setStars(hotelModel.getStars());
        if (!TextUtils.isEmpty(hotelModel.getImageName())) {
            dataInteractor.getBytes(hotelModel.getImageName()).subscribe(new DisposableMaybeObserverAdapter<byte[]>() {
                @Override
                public void onSuccess(@NonNull byte[] bytes) {
                    holder.image.setImageDrawable(decoder.decode(bytes));
                }
            });
        }
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
        notifyItemRangeChanged(0, hotels.size());
    }

    void setOnClickCallback(@Nullable ItemClickListener onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    class HotelViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView  name;
        private final TextView  address;
        private final StarsView stars;

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
