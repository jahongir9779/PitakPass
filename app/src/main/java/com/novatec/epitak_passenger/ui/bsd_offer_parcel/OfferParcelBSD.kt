package com.novatec.epitak_passenger.ui.bsd_offer_parcel

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.asksira.bsimagepicker.BSImagePicker
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.novatec.epitak_passenger.R
import com.novatec.epitak_passenger.domain.model.PassengerPost
import com.novatec.epitak_passenger.ui.driver_post.jump_in.ARG_DRIVER_POST
import com.novatec.epitak_passenger.ui.driver_post.jump_in.JumpInViewModel
import com.novatec.epitak_passenger.ui.viewgroups.ActivePostItem
import com.novatec.epitak_passenger.util.BSDExpanded
import com.novatec.epitak_passenger.util.ErrorWrapper
import com.novatec.epitak_passenger.util.ResultWrapper
import com.novatec.epitak_passenger.util.exhaustive
import com.novatec.epitak_passenger.viewobjects.DriverPostViewObj
import com.novatec.epitak_passenger.viewobjects.UserOfferViewObj
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bsd_offer_parcel.*
import splitties.experimental.ExperimentalSplittiesApi
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class OfferParcelBSD : BSDExpanded(),
    BSImagePicker.OnSingleImageSelectedListener,
    BSImagePicker.ImageLoaderDelegate {


    private val adapter = GroupAdapter<GroupieViewHolder>()

    private lateinit var driverPost: DriverPostViewObj
    val viewModel: JumpInViewModel by viewModels()

    override fun getTheme() = R.style.Theme_Dialog

    override fun onAttach(context: Context) {
        super.onAttach(context)
        driverPost = requireArguments().getParcelable(ARG_DRIVER_POST)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bsd_offer_parcel, container)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        edtPrice.hint =
//            DecimalFormat("#,###").format(driverPost.price) + " " + getString(R.string.sum)
        rvMyPosts.adapter = adapter
        attachListeners()
        subscribeObservers()
        driverPost.myLastOffer?.let { showOfferSent(driverPost.myLastOffer!!) } ?: run {
            viewModel.getActivePosts()
        }

    }

    private fun showOfferSent(myLastOffer: UserOfferViewObj) {
        viewModel.offeringPostId.value = myLastOffer.repliedPostId
        rvContainer.isVisible = false
        lblSelectPost.isVisible = false
        cardLastOffer.isVisible = true
        tvLastOfferPrice.text = getString(R.string.price) + " " + myLastOffer.priceInt.toString()
        tvLastOfferRepliedPostId.text =
            getString(R.string.attached_post_id) + " " + myLastOffer.repliedPostId.toString()
        tvLastOfferMessage.text = getString(R.string.message) + " " + myLastOffer.message
        btnSendOffer.text = getString(R.string.update_offer)
    }


    private fun subscribeObservers() {

        viewModel.isOffering.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) btnSendOffer.startAnimation() else btnSendOffer.revertAnimation()

        }

        viewModel.hasFinished.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished) dismiss()
        }

        viewModel.activePostsResponse.observe(viewLifecycleOwner) {
            val response = it ?: return@observe
            when (response) {
                is ErrorWrapper.ResponseError -> {
                    Snackbar.make(
                        scrollView,
                        response.message ?: getString(R.string.response_error),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is ErrorWrapper.SystemError -> {
                    Snackbar.make(
                        scrollView,
                        response.err.localizedMessage ?: getString(R.string.system_error),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is ResultWrapper.Success -> {
                    loadData(response.value.filter { myPost ->
                        myPost.departureDate == driverPost.departureDate && myPost.postStatus.isOfferable()
                    })
                }
                ResultWrapper.InProgress -> {
                }
            }.exhaustive
        }
    }

    @ExperimentalSplittiesApi
    private fun loadData(orders: List<PassengerPost>) {
        adapter.clear()
        if (orders.isEmpty()) {
            lblSelectPost.text = getString(R.string.we_will_create_an_order_for_you)
            rvContainer.visibility = View.GONE
        } else {
            lblSelectPost.text = getString(R.string.select_your_trip_if_you_have_one)
            rvContainer.visibility = View.VISIBLE
        }

        orders.forEach { post ->
            adapter.add(ActivePostItem(post) {
                lblSelectPost.visibility = View.GONE
                tvSelectedPost.visibility = View.VISIBLE
                ivClearSelected.visibility = View.VISIBLE
                tvSelectedPost.text = getString(R.string.offering_post_id, post.id)
                viewModel.setOfferingPost(post.id)

            })
        }

    }

    private fun attachListeners() {

        imageContainer.setOnClickListener {

        }


        edtPrice.doOnTextChanged { text, start, before, count ->
        }

        btnSendOffer.setOnClickListener {
            viewModel.offerParcel(
                driverPost.id,
                if (edtPrice.text.isNullOrBlank()) driverPost.price else edtPrice.text.toString()
                    .toInt(),
                messageInput.text.toString(),
                driverPost
            )
        }

        ivClearSelected.setOnClickListener {
            viewModel.setOfferingPost(null)
            lblSelectPost.visibility = View.VISIBLE
            tvSelectedPost.visibility = View.GONE
            ivClearSelected.visibility = View.GONE
        }
    }


    override fun onSingleImageSelected(uri: Uri, tag: String?) {
        val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
        val out = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out)
        viewModel.uploadParcelImage(out.toByteArray())
    }

    override fun loadImage(imageUri: Uri?, ivImage: ImageView) {
        Glide.with(this).load(imageUri).into(ivImage)
    }

}