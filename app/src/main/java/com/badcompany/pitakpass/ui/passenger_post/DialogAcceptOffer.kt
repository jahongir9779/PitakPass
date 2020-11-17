package com.badcompany.pitakpass.ui.passenger_post

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.badcompany.pitakpass.R
import com.badcompany.pitakpass.remote.model.OfferDTO
import kotlinx.android.synthetic.main.dialog_finish_post.*

const val ARG_OFFER = "OFFER"

class DialogAcceptOffer : DialogFragment() {


    private lateinit var offer: OfferDTO

    override fun onAttach(context: Context) {
        super.onAttach(context)
        offer = requireArguments().getParcelable(ARG_OFFER)!!
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_accept_offer, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNo.setOnClickListener {
            dismiss()
        }

        btnYes.setOnClickListener {
            (requireActivity() as PassengerPostActivity).acceptOffer(offer)
            dismiss()
        }

    }

    override fun getTheme() = R.style.Theme_Dialog
}