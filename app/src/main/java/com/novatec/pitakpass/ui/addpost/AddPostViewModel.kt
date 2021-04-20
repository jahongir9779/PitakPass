package com.novatec.pitakpass.ui.addpost

import androidx.hilt.lifecycle.ViewModelInject
import com.novatec.pitakpass.domain.model.CarDetails
import com.novatec.pitakpass.domain.model.Place
import com.novatec.pitakpass.App
import com.novatec.pitakpass.ui.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import splitties.init.appCtx
import javax.inject.Inject

/**
 * Created by jahon on 28-Apr-20
 */
class AddPostViewModel  @ViewModelInject constructor() :
    BaseViewModel() {

    var isEditing: Boolean = false
    var id: Long? = null
    var placeFrom: Place? = null
    var placeTo: Place? = null
    var departureDate: String? = null
    var timeFirstPart = false
    var timeSecondPart = false
    var timeThirdPart = false
    var timeFourthPart = false
    var price: Int? = null
    var seat: Int? = null
    var note: String? = null


    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
//        (appCtx as App).releaseAddCarComponent()
    }
}