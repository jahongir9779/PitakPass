package com.badcompany.pitakpass.ui.auth.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.badcompany.pitakpass.util.*
import com.badcompany.pitakpass.domain.model.User
import com.badcompany.pitakpass.R
import com.badcompany.pitakpass.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment @Inject constructor() :
    Fragment(R.layout.fragment_register) {

    val args: RegisterFragmentArgs by navArgs()

    lateinit var navController: NavController

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.cancelActiveJobs()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupObservers()
        phone.setMaskedText(args.phone.numericOnly().substring(3, args.phone.numericOnly().length))

//        username.afterTextChanged {
//            viewModel.loginDataChanged(
//                username.text.toString(),
//                password.text.toString()
//            )
//        }

//        password.apply {
//            afterTextChanged {
//                viewModel.loginDataChanged(
//                    username.text.toString(),
//                    password.text.toString()
//                )
//            }
//
//            setOnEditorActionListener { _, actionId, _ ->
//                when (actionId) {
//                    EditorInfo.IME_ACTION_DONE ->
//                        viewModel.register(User(
//                            phone.text.toString(),
//                            name.text.toString(),
//                            surname.text.toString(),
//                            username.text.toString(),
//                            password.text.toString(),
//                            false)
//                        )
//                }
//                false
//            }
//        }
        register.isEnabled = true

        navController = findNavController()

        register.setOnClickListener {
            viewModel.register(User(phone.text.toString().numericOnly(),
                name.text.toString(),
                surname.text.toString(),
                Constants.ROLE_PASSENGER))
//            navController.navigate(R.id.action_navRegisterFragment_to_navPhoneConfirmFragment)
        }

//        register.setOnClickListener {
//            register.startAnimation()
//            viewModel.register(User(
//                phone.text.toString(),
//                name.text.toString(),
//                surname.text.toString(),/*
//                    username.text.toString(),
//                    password.text.toString(),*/
//                false)
//            )
//        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AuthActivity).showActionBar()
    }

    private fun setupObservers() {

        viewModel.registerFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            register.isEnabled = loginState.isDataValid

//            if (loginState.usernameError != null) {
//                username.error = getString(loginState.usernameError)
//            }
//            if (loginState.passwordError != null) {
//                password.error = getString(loginState.passwordError)
//            }
        })

        viewModel.regResponse.observe(viewLifecycleOwner, Observer {
            val response = it ?: return@Observer
//
////            loading.visibility = View.GONE
//            if (loginResult.error != null) {
//                showLoginFailed(loginResult.error)
//            }
//            if (loginResult.success != null) {
//                updateUiWithUser(loginResult.success)
//            }

            when (response) {
                is ErrorWrapper.ResponseError -> {
                    register.revertAnimation()
                    /*  if (response.code == -1) {
                          val action =
                              RegisterFragmentDirections.actionNavRegisterFragmentToNavPhoneConfirmFragment(
                                  response., viewModel.phoneNum )
                          findNavController().navigate(action)
                      } else */if (response.code == Constants.errPhoneFormat) {
                        phone.error = getString(R.string.incorrect_phone_number_format)
//                        errorMessage.visibility = View.VISIBLE
//                        errorMessage.text = response.message
                    } else {
                        errorMessage.visibility = View.VISIBLE
                        errorMessage.text = response.message
                    }
                }
                is ErrorWrapper.SystemError -> {
                    errorMessage.visibility = View.VISIBLE
                    errorMessage.text = "SYSTEM ERROR"
                    register.revertAnimation()
                }
                is ResultWrapper.Success -> {
                    register.revertAnimation()
                    val action =
                        RegisterFragmentDirections.actionNavRegisterFragmentToNavPhoneConfirmFragment(
                            response.value,
                            args.phone)
                    findNavController().navigate(action)
                }
                ResultWrapper.InProgress -> {
                    errorMessage.visibility = View.INVISIBLE
                    register.startAnimation()
                }
            }.exhaustive


        })
    }



}


