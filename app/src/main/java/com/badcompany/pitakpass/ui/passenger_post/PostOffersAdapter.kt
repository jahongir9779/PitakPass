package com.badcompany.pitakpass.ui.passenger_post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.badcompany.pitakpass.R
import com.badcompany.pitakpass.remote.model.OfferDTO
import com.badcompany.pitakpass.ui.interfaces.IOnOfferActionListener
import com.badcompany.pitakpass.util.loadCircleImageUrl
import com.badcompany.pitakpass.util.loadImageUrl
import kotlinx.android.synthetic.main.activity_passenger_post.*
import kotlinx.android.synthetic.main.item_offer.view.*
import kotlinx.android.synthetic.main.item_offer.view.ivCarPhoto
import kotlinx.android.synthetic.main.item_offer.view.tvCarInfo
import java.text.DecimalFormat

class PostOffersAdapter(val onOfferActionListener: IOnOfferActionListener) :
    PagingDataAdapter<OfferDTO, PostOffersAdapter.OfferViewHolder>(OFFER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_offer, parent, false)
        return OfferViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem, onOfferActionListener)
    }

    class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(offer: OfferDTO, onOfferActionListener: IOnOfferActionListener) {
            itemView.apply {
                if (offer.message.isNullOrBlank()) {
                    tvMessage.visibility = View.GONE
                } else {
                    tvMessage.visibility = View.VISIBLE
                    tvMessage.text = offer.message
                }

                offer.price?.also {
                    tvOfferingPrice.text =
                        DecimalFormat("#,###").format(it) + " " + context.getString(R.string.sum)
                } ?: run {
                    tvOfferingPrice.text =
                        context.getString(R.string.your_price)
                }

                tvName.text = offer.profile.name + " " + offer.profile.surname

                offer.car?.image?.link?.let {
                    ivCarPhoto.loadImageUrl(it)
                }

                offer.profile.image?.link?.let {
                    ivAvatar.loadCircleImageUrl(it)
                }

                offer.profile.rating?.let {
                    ratingBarDriver.rating = it
                }

                offer.car?.let {
                    var hasAC = ""

                    it.airConditioner?.let {
                        if (it) hasAC = ", " + context.getString(R.string.air_conditioner)
                    }

                    tvCarInfo.text = it.carModel?.name + ", "+
                            it.carYear.toString() + ", "+
                            it.carColor?.name + ", "+
                            it.carNumber + ", "+
                            it.fuelType +
                            hasAC

                }
                ivDeny.setOnClickListener {
                    onOfferActionListener.onCancelClick(offer)
                }
                ivAccept.setOnClickListener {
                    onOfferActionListener.onAcceptClick(offer)
                }
//                ivPhone.setOnClickListener {
//                    onOfferActionListener.onPhoneCallClick(offer)
//                }
            }
        }
    }

    companion object {

        private val OFFER_COMPARATOR = object : DiffUtil.ItemCallback<OfferDTO>() {
            override fun areItemsTheSame(oldItem: OfferDTO, newItem: OfferDTO) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: OfferDTO, newItem: OfferDTO) =
                oldItem == newItem

        }
    }

}