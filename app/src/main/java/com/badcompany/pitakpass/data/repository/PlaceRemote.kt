package com.badcompany.pitakpass.data.repository

import com.badcompany.pitakpass.util.ResultWrapper
import com.badcompany.pitakpass.domain.model.Place

interface PlaceRemote {

   suspend fun getPlacesAutocomplete( queryString:String): ResultWrapper<List<Place>>

}