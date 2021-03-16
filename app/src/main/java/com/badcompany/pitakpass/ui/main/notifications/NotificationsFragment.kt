package com.badcompany.pitakpass.ui.main.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.badcompany.pitakpass.R
import dagger.hilt.android.AndroidEntryPoint
import splitties.experimental.ExperimentalSplittiesApi
import javax.inject.Inject

@AndroidEntryPoint
class NotificationsFragment @Inject constructor() : Fragment(R.layout.fragment_notifications) {

    private val viewModel: NotificationsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.cancelActiveJobs()
    }

    @ExperimentalSplittiesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        subscribeObservers()
        setupViews()
    }

    @ExperimentalSplittiesApi
    private fun setupViews() {

    }


    private fun subscribeObservers() {


    }


    private fun setupListeners() {




    }



}

