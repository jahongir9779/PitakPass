package com.novatec.pitakpass.ui.addpost.carandtext

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.novatec.pitakpass.R
import com.novatec.pitakpass.domain.model.CarDetails
import com.novatec.pitakpass.ui.addpost.AddPostViewModel
import com.novatec.pitakpass.ui.viewgroups.ErrorTextItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_car_and_note.*
import splitties.experimental.ExperimentalSplittiesApi


@AndroidEntryPoint
class CarAndTextFragment : Fragment(R.layout.fragment_car_and_note) {

    val args: CarAndTextFragmentArgs by navArgs()


    private var selectedCar: CarDetails? = null
    private val adapter = GroupAdapter<GroupieViewHolder>()

    private val activityViewModel: AddPostViewModel by activityViewModels()

    private val viewModel: CarAndTextViewModel by viewModels()

    lateinit var navController: NavController


    @ExperimentalSplittiesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.ISFROMPOSTPREVIEW) {
            noteInput.setText(activityViewModel.note)
        }

        setupListeners()
//        setupCarList()
        setupObservers()

        navController = findNavController()
//        confirm.isEnabled = true
//
//        code.setText(args.password)
//
//        confirm.setOnClickListener {
//            viewModel.confirm(args.phone, code.text.toString())
//        }

//        viewModel.getCars()
//        updateNextButtonState()
    }

//    private fun setupCarList() {
//        carsList.layoutManager =
//            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
//        carsList.setHasFixedSize(true)
//        carsList.adapter = adapter
//
//    }


    @ExperimentalSplittiesApi
    private fun setupListeners() {

        navNext.setOnClickListener {
//            activityViewModel.car = selectedCar
            activityViewModel.note =
                if (!noteInput.text.isNullOrBlank()) noteInput.text.toString() else ""


            navController.navigate(if (args.ISFROMPOSTPREVIEW) R.id.action_carAndTextFragment_to_previewFragment else R.id.action_carAndTextFragment_to_previewFragment)
        }

//        navBack.setOnClickListener {
//            requireActivity().onBackPressed()
//        }
    }


    @ExperimentalSplittiesApi
    private fun setupObservers() {
//        viewModel.carsResponse.observe(viewLifecycleOwner, Observer {
//            val response = it ?: return@Observer
//
//            when (response) {
//                is ErrorWrapper.ResponseError -> {
//                    showErrorMessage(response.message)
//                }
//                is ErrorWrapper.SystemError -> {
//                    showErrorMessage(response.err.localizedMessage)
//                }
//                is ResultWrapper.Success -> {
//                    populateCarsList(response.value)
//                }
//                ResultWrapper.InProgress -> {
//                    adapter.clear()
//                    adapter.add(LoadingItem())
//                }
//            }.exhaustive
//
//        })

    }

    private fun showErrorMessage(message: String?) {

        adapter.clear()
        adapter.add(ErrorTextItem(message))
        adapter.notifyDataSetChanged()

    }

//    private fun populateCarsList(cars: List<CarDetails>) {
//        adapter.clear()
//        cars.forEach { car ->
//            if (car.def!!) {
//                selectedCar = car
//            }
//            adapter.add(CarItemSelectionView(car, object : MyItemClickListener {
//                override fun onClick(pos: Int) {
//                    super.onClick(pos)
//                    for (i in 0 until adapter.itemCount) {
//                        (adapter.getItem(i) as CarItemSelectionView).car.def = false
//                    }
//                    car.def = true
//                    adapter.notifyDataSetChanged()
//                }
//            }))
//        }
//        updateNextButtonState()
//    }

    private fun updateNextButtonState() {
        navNext.isEnabled = selectedCar != null

        if (navNext.isEnabled) {
            val bg = navNext.background
            bg.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorPrimary),
                              PorterDuff.Mode.SRC_ATOP)
            navNext.background = bg
        } else {
            val bg = navNext.background
            bg.setColorFilter(ContextCompat.getColor(requireContext(), R.color.ic_grey),
                              PorterDuff.Mode.SRC_ATOP)
            navNext.background = bg
        }
    }


    @ExperimentalSplittiesApi
    override fun onResume() {
        super.onResume()
//        viewModel.getCars()
//        (activity as AddPostActivity).showActionBar()
    }


}



