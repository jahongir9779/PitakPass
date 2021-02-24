package com.badcompany.pitakpass.domain.repository

import com.badcompany.pitakpass.domain.model.DriverPost
import com.badcompany.pitakpass.domain.model.PassengerOffer
import com.badcompany.pitakpass.remote.model.ObjRating
import com.badcompany.pitakpass.util.ResponseWrapper

interface DriverPostRepository {

    //    suspend fun filterDriverPost(filter: Filter): ResultWrapper<List<DriverPost>>
    suspend fun getDriverPostById(id: Long): ResponseWrapper<DriverPost>
    suspend fun joinARide(myOffer: PassengerOffer): ResponseWrapper<Any>
    suspend fun getMyRatingForDriver(id: Long): ResponseWrapper<ObjRating?>
    suspend fun editMyRatingForDriver(ratingId:Long,id: Long, rating : Float): ResponseWrapper<ObjRating?>
    suspend fun postMyRatingForDriver(id: Long, rating : Float): ResponseWrapper<ObjRating>


}