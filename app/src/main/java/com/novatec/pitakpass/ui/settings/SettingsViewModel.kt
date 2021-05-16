package com.novatec.pitakpass.ui.settings

import javax.inject.Inject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.novatec.pitakpass.domain.repository.UserRepository
import com.novatec.pitakpass.ui.BaseViewModel
import com.novatec.pitakpass.util.ResponseError
import com.novatec.pitakpass.util.ResponseSuccess
import com.novatec.pitakpass.util.exhaustive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class SettingsViewModel @Inject constructor(val userRepository: UserRepository) :
    BaseViewModel() {

//    val feedbackResponse = MutableLiveData<String>()
//    val errorString = MutableLiveData<String>()
//    val isLoading = MutableLiveData<Boolean>()
//
//    fun sendFeedback(feedback: String) {
//        isLoading.value = true
//        viewModelScope.launch(IO) {
//            val resp = userRepository.sendFeedback(feedback)
//            withContext(Main) {
//                isLoading.value = false
//                when (resp) {
//                    is ResponseError -> {
//                        errorString.value = resp.message
//                    }
//                    is ResponseSuccess -> {
//                        feedbackResponse.value = ""
//                    }
//                }.exhaustive
//            }
//        }
//    }

}
