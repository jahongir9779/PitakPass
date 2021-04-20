package com.novatec.pitakpass.ui.passenger_post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.novatec.pitakpass.R
import kotlinx.android.synthetic.main.dialog_finish_post.*

class DialogFinishPost : DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_finish_post, container)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNo.setOnClickListener {
            dismiss()
        }

        btnYes.setOnClickListener {
            (requireActivity() as PassengerPostActivity).finishPost()
            dismiss()
        }

    }


    override fun getTheme() = R.style.Theme_Dialog

}