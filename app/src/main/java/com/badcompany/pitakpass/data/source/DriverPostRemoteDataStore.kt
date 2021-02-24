package com.badcompany.pitakpass.data.source

import com.badcompany.pitakpass.data.repository.DriverPostDataStore
import com.badcompany.pitakpass.data.repository.DriverPostRemote
import com.badcompany.pitakpass.data.repository.PlaceDataStore
import com.badcompany.pitakpass.domain.model.PassengerOffer
import javax.inject.Inject

/**
 * Implementation of the [PlaceDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class DriverPostRemoteDataStore @Inject constructor(private val postRemote: DriverPostRemote) :
    DriverPostDataStore {

    override suspend fun getPostById(id: Long) = postRemote.getPostById(id)
    override suspend fun joinARide(myOffer: PassengerOffer) = postRemote.joinARide(myOffer)
    override suspend fun getMyRatingForDriver(id: Long) = postRemote.getMyRatingForDriver(id)

    override suspend fun editMyRatingForDriver(ratingId: Long, id: Long, rating: Float) =
        postRemote.editMyRatingForDriver(ratingId, id, rating)

    override suspend fun postMyRatingForDriver(id: Long, rating: Float) =
        postRemote.postMyRatingForDriver(id, rating)

}