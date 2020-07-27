package com.badcompany.pitakpass.data.repository

import com.badcompany.pitakpass.util.ResultWrapper
import com.badcompany.pitakpass.domain.model.Place


interface PlaceDataStore {
    suspend fun getPlacesAutocomplete(token: String,lang: String, queryString:String): ResultWrapper<List<Place>>

}