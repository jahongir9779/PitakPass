package com.badcompany.pitakpass.ui.auth.register

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.badcompany.core.ErrorWrapper
import com.badcompany.core.ResultWrapper
import com.badcompany.core.exhaustive
import com.badcompany.core.numericOnly
import com.badcompany.domain.domainmodel.User
import com.badcompany.domain.usecases.RegisterUser
import com.badcompany.pitakpass.R
import com.badcompany.pitakpass.ui.BaseViewModel
import com.badcompany.pitakpass.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val registerUser: RegisterUser) :
    BaseViewModel() {

    private val _registerForm = SingleLiveEvent<RegisterFormState>()
    val registerFormState: SingleLiveEvent<RegisterFormState> = _registerForm

    val response = SingleLiveEvent<ResultWrapper<String>>()

    fun register(user: User) {
            response.value = ResultWrapper.InProgress
            viewModelScope.launch(Dispatchers.IO)  {
                response.value = registerUser.execute(user)
            }
    }




}
