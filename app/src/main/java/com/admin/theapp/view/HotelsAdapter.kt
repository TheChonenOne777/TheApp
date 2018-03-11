package com.admin.theapp.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.admin.theapp.R
import com.admin.theapp.interactors.DataInteractor
import com.admin.theapp.utils.Decoder
import com.admin.theapp.utils.inflateView
import com.theapp.entities.HotelModel
import kotlinx.android.synthetic.main.hotel_item.view.*
import javax.inject.Inject

class HotelsAdapter @Inject constructor(
        private val decoder: Decoder,
        private val dataInteractor: DataInteractor
) : RecyclerView.Adapter<HotelsAdapter.ViewHolder>() {

    var hotels: MutableList<HotelModel> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflateView(R.layout.hotel_item)
        val holder = ViewHolder(view)
        view.setOnClickListener({ listener?.onClick(hotels[holder.adapterPosition]) })
        return holder
    }

    override fun getItemCount() = hotels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(hotels[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: HotelModel) = with(itemView) {
            item.imageName?.let { dataInteractor.getImageBytes(it).subscribe({ hotel_image.setImageDrawable(decoder.decode(it)) }) }
            hotel_name.text = item.name
            hotel_address.text = item.address
            hotel_stars.setStars(item.stars)
        }
    }

    interface ItemClickListener {
        fun onClick(hotelModel: HotelModel)
    }
}
