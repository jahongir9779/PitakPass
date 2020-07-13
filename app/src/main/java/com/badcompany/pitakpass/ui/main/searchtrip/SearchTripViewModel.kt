package com.badcompany.pitakpass.ui.main.searchtrip

import androidx.lifecycle.viewModelScope
import com.badcompany.pitakpass.util.Constants
import com.badcompany.pitakpass.util.ResultWrapper
import com.badcompany.pitakpass.domain.domainmodel.Filter
import com.badcompany.pitakpass.domain.domainmodel.PassengerPost
import com.badcompany.pitakpass.domain.usecases.GetPassengerPostWithFilter
import com.badcompany.pitakpass.ui.BaseViewModel
import com.badcompany.pitakpass.util.AppPreferences
import com.badcompany.pitakpass.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import splitties.experimental.ExperimentalSplittiesApi
import javax.inject.Inject

class SearchTripViewModel @Inject constructor(val getPassengerPostWithFilter: GetPassengerPostWithFilter) :
    BaseViewModel() {


    val passengerPostsReponse = SingleLiveEvent<ResultWrapper<List<PassengerPost>>>()
    var currentPage = 0

    @ExperimentalSplittiesApi
    fun getPassengerPost(filter: Filter) {
        passengerPostsReponse.value = ResultWrapper.InProgress
        viewModelScope.launch(Dispatchers.IO) {
            val response = getPassengerPostWithFilter.execute(hashMapOf(
                Pair(Constants.TXT_TOKEN, AppPreferences.token),
                Pair(Constants.TXT_LANG, AppPreferences.language),
                Pair(Constants.TXT_FILTER, filter)))

            withContext(Dispatchers.Main) {
                passengerPostsReponse.value = response
            }

        }

    }
}