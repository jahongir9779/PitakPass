package com.badcompany.pitakpass.ui.passenger_post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.badcompany.pitakpass.R
import com.badcompany.pitakpass.remote.model.OfferDTO
import com.badcompany.pitakpass.ui.EOfferStatus
import com.badcompany.pitakpass.ui.interfaces.IOnOfferActionListener
import com.badcompany.pitakpass.util.exhaustive
import kotlinx.android.synthetic.main.item_offer.view.*

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
                tvMessage.text = offer.message
                tvStatus.text = offer.status.name

                when (offer.status) {
                    EOfferStatus.ACTIVE -> {
                        tvStatus.setBackgroundResource(R.color.colorPrimaryDarkOpacityFifty)
                        ivAccept.visibility = View.VISIBLE
                        ivPhone.visibility = View.INVISIBLE
                    }
                    EOfferStatus.ACCEPTED -> {
                        ivAccept.visibility = View.INVISIBLE
                        ivPhone.visibility = View.VISIBLE
                        tvStatus.setBackgroundResource(R.color.green)
                    }
                }.exhaustive

                ivDeny.setOnClickListener {
                    onOfferActionListener.onCancelClick(offer)
                }
                ivAccept.setOnClickListener {
                    onOfferActionListener.onAcceptClick(offer)
                }
                ivPhone.setOnClickListener {
                    onOfferActionListener.onAcceptClick(offer)
                }
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