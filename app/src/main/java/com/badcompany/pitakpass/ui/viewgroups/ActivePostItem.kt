package com.badcompany.pitakpass.ui.viewgroups

import android.view.View
import androidx.core.content.ContextCompat
import com.badcompany.pitakpass.R
import com.badcompany.pitakpass.domain.model.PassengerPost
import com.badcompany.pitakpass.ui.EPostStatus
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_active_post.view.*
import java.text.DecimalFormat


class ActivePostItem(var post: PassengerPost/*, var onPostActionListener: IOnPostActionListener*/,
                     var onClick: () -> Unit) :
    Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            date.text = post.departureDate
            from.text = post.from.regionName
            to.text = post.to.regionName
            price.text = post.price.toString()

            post.remark?.also {
                note.visibility = View.VISIBLE
                note.text = post.remark
            } ?: run {
                note.visibility = View.GONE
            }

            price.text =
                DecimalFormat("#,###").format(post.price) + " " + context.getString(R.string.sum)

            val currentStatusStr = when (post.postStatus) {
                EPostStatus.WAITING_FOR_START -> {
                    status.backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.colorNavIdle)

                    context.getString(R.string.waiting)
                }
                EPostStatus.START -> {
                    status.backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.colorPrimary)

                    context.getString(R.string.on_the_way)
                }
                EPostStatus.CANCELED -> {
                    context.getString(R.string.canceled)
                }
                EPostStatus.FINISHED -> {
                    context.getString(R.string.completed)
                }
                EPostStatus.REJECTED -> {
                    context.getString(R.string.rejected)
                }
                EPostStatus.CREATED -> {
                    status.backgroundTintList =
                        ContextCompat.getColorStateList(context, R.color.neutralColor)
                    context.getString(R.string.boarding)
                }
                EPostStatus.SYSTEM_REJECTED -> {
                    context.getString(R.string.rejected)
                }
            }

            status.text = currentStatusStr


            if (viewHolder.itemView.findViewById<View>(R.id.progress) != null) {
                cl_parent.removeView(viewHolder.itemView.findViewById(R.id.progress))
            }

            cl_parent.setOnClickListener {
                onClick()
            }
        }
    }

    override fun getLayout() = R.layout.item_active_post
}
