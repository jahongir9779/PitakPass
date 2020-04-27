package com.badcompany.pitak.ui.auth.register

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.badcompany.core.ErrorWrapper
import com.badcompany.core.ResultWrapper
import com.badcompany.core.exhaustive
import com.badcompany.domain.domainmodel.User
import com.badcompany.domain.usecases.RegisterUser
import com.badcompany.pitak.R
import com.badcompany.pitak.ui.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val registerUser: RegisterUser) :
    BaseViewModel() {

    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    fun register(user: User) {
        // can be launched in a separate asynchronous job
        /*   val result = registerRepository.login(username, password)

           if (result is ResultWrapper.Success) {
               _registerResult.value =
                   RegisterResult(success = RegisterUserView(displayName = result.value.displayName))
           } else {
               _registerResult.value = RegisterResult(error = R.string.login_failed)
           }*/

        viewModelScope.launch {
            val response = registerUser.execute(user)
            when (response) {
                is ErrorWrapper.ResponseError -> {
                    Log.d("DEBUG REGISTER", "ResponseError"+ response.toString())
                }
                is ErrorWrapper.SystemError -> {
                    Log.d("DEBUG REGISTER", "SystemError" + response.toString())
                }
                is ResultWrapper.Success -> {
                    Log.d("DEBUG REGISTER", "Success"+ response.toString())
                }
                ResultWrapper.InProgress -> {

                }
            }.exhaustive
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _registerForm.value = RegisterFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else {
            _registerForm.value = RegisterFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}