package com.badcompany.pitakpass.ui.main.searchtrip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.badcompany.pitakpass.R
import com.badcompany.pitakpass.domain.model.DriverPost
import com.badcompany.pitakpass.remote.model.OfferDTO
import com.badcompany.pitakpass.ui.EOfferStatus
import com.badcompany.pitakpass.ui.driver_post.DriverPostActivity
import com.badcompany.pitakpass.ui.driver_post.join_a_ride.ARG_DRIVER_POST
import com.badcompany.pitakpass.ui.interfaces.IOnOfferActionListener
import com.badcompany.pitakpass.util.exhaustive
import com.badcompany.pitakpass.util.loadCircleImageUrl
import com.badcompany.pitakpass.util.loadImageUrl
import com.badcompany.pitakpass.viewobjects.DriverPostViewObj
import kotlinx.android.synthetic.main.item_driver_post.view.*
import kotlinx.android.synthetic.main.item_offer.view.*
import splitties.activities.start
import kotlin.to

class PostFilterAdapter() :
    PagingDataAdapter<DriverPost, PostFilterAdapter.DriverPostViewHolder>(FILTER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverPostViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_driver_post, parent, false)
        return DriverPostViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: DriverPostViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    class DriverPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: DriverPost) {
            itemView.apply {
                date.text = post.departureDate
                from.text = post.from.regionName
                to.text = post.to.regionName
                price.text = context.getString(R.string.price_and_seats_format,
                                               post.price.toString(), post.seat.toString())

                if (!post.remark.isBlank()) {
                    note.visibility = View.VISIBLE
                    note.text = post.remark
                } else {
                    note.visibility = View.GONE
                }

                card.setOnClickListener {
                    context.start<DriverPostActivity> {
                        putExtra(ARG_DRIVER_POST, DriverPostViewObj.mapFromDriverPostModel(post))
                    }
                }

                post.car?.image?.link?.let {
                    ivCarPhoto.loadImageUrl(it)
                }

                post.profileDTO?.image?.link?.let {
                    ivDriver.loadCircleImageUrl(it)
                }

                post.car?.let {
                    tvCarInfo.text = it.carModel?.name + "\n"
                    it.carYear.toString() + "\n"
                    it.carColor?.name + "\n"
                    it.carNumber + "\n"
                    it.fuelType
                }

                post.profileDTO?.let {
                    tvDriverName.text = it.name + " " + it.surname
                }

            }
        }
    }

    companion object {

        private val FILTER_COMPARATOR = object : DiffUtil.ItemCallback<DriverPost>() {
            override fun areItemsTheSame(oldItem: DriverPost, newItem: DriverPost) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DriverPost, newItem: DriverPost) =
                oldItem == newItem

        }
    }

}