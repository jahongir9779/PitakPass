//package com.novatec.epitak_passenger.ui.addpost.choosedatetime
//
//import androidx.lifecycle.viewModelScope
//import com.novatec.epitak_passenger.util.Constants
//import com.novatec.epitak_passenger.util.ResultWrapper
//import com.novatec.epitak_passenger.domain.domainmodel.Place
//import com.novatec.epitak_passenger.domain.usecases.GetPlacesFeed
//import com.novatec.epitak_passenger.ui.BaseViewModel
//import com.novatec.epitak_passenger.util.AppPreferences
//import com.novatec.epitak_passenger.util.SingleLiveEvent
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.Dispatchers.Main
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import splitties.experimental.ExperimentalSplittiesApi
//import javax.inject.Inject
//
//
//class ChooseDateTimeViewModel  @Inject constructor(/*private val getPlacesFeed: GetPlacesFeed*/) :
//    BaseViewModel() {
//
//    private var fromFeedJob: Job? = null
//    private var toFeedJob: Job? = null
//    val fromPlacesResponse = SingleLiveEvent<ResultWrapper<List<Place>>>()
//    val toPlacesResponse = SingleLiveEvent<ResultWrapper<List<Place>>>()
//
//
//    @ExperimentalSplittiesApi
//    fun getPlacesFeed(queryString: String, isFrom: Boolean = true) {
//        if (isFrom) fromPlacesResponse.value = ResultWrapper.InProgress
//        else toPlacesResponse.value = ResultWrapper.InProgress
//        resetFromFeedJob(isFrom)
//        viewModelScope.launch(Dispatchers.IO + if (isFrom) fromFeedJob!! else toFeedJob!!) {
//            val response =
//                getPlacesFeed.execute(hashMapOf(Pair(Constants.TXT_
//                                                     AppPreferences.token),
//                                                Pair(Constants.TXT_LANG,
//                                                     AppPreferences.language),
//                                                Pair(Constants.TXT_PLACE, queryString)))
//
//            withContext(Main) {
//                if (isFrom) fromPlacesResponse.value = response
//                else toPlacesResponse.value = response
//            }
//        }
//    }
//
//    private fun resetFromFeedJob(isFrom: Boolean) {
//        fromFeedJob?.cancel()
//        fromFeedJob = Job()
//        toFeedJob?.cancel()
//        toFeedJob = Job()
//
//    }
//
//    // A placeholder username validation check
//    private fun isCodeValid(code: String): Boolean {
//        return code.length == 5
//    }
//
//
//    // A placeholder password validation check
//    private fun isPasswordValid(password: String): Boolean {
//        return password.length > 5
//    }
//
//
//    override fun onCleared() {
//        super.onCleared()
//        fromFeedJob?.cancel()
//        toFeedJob?.cancel()
//    }
//
//
//}
