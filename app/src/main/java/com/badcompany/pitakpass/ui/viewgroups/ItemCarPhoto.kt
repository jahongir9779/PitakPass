package com.badcompany.pitakpass.ui.viewgroups

import com.badcompany.pitakpass.domain.model.PhotoBody
import com.badcompany.pitakpass.R
import com.badcompany.pitakpass.util.loadImageUrl
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_car_photo.view.*

class ItemCarPhoto(val photoBody: PhotoBody, val deleteClickListener: OnItemClickListener) :
    Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.iv_delete.setOnClickListener {
            deleteClickListener.onItemClick(this, it)
        }
        viewHolder.itemView.iv_car_photo.loadImageUrl(photoBody.link!!)
    }

    override fun getLayout() = R.layout.item_car_photo
}
