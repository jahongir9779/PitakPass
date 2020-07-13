package com.badcompany.pitakpass.ui.addpost.preview

import androidx.lifecycle.viewModelScope
import com.badcompany.pitakpass.util.Constants
import com.badcompany.pitakpass.util.ResultWrapper
import com.badcompany.pitakpass.domain.domainmodel.DriverPost
import com.badcompany.pitakpass.domain.usecases.CreateDriverPost
import com.badcompany.pitakpass.ui.BaseViewModel
import com.badcompany.pitakpass.util.AppPreferences
import com.badcompany.pitakpass.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import splitties.experimental.ExperimentalSplittiesApi
import javax.inject.Inject


class PreviewViewModel @Inject constructor(private val createDriverPost: CreateDriverPost) :
    BaseViewModel() {

    val createResponse = SingleLiveEvent<ResultWrapper<String>>()


    @ExperimentalSplittiesApi
    fun createDriverPost(driverPost: DriverPost) {
        createResponse.value = ResultWrapper.InProgress
        viewModelScope.launch(Dispatchers.IO) {
            val response = createDriverPost.execute(
                hashMapOf(Pair(Constants.TXT_TOKEN, AppPreferences.token),
                          Pair(Constants.TXT_DRIVER_POST, driverPost)))

            withContext(Main) {
                createResponse.value = response
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
    }


}
