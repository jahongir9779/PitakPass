package com.badcompany.pitakpass.data

import com.badcompany.pitakpass.data.source.PassengerPostDataStoreFactory
import com.badcompany.pitakpass.domain.model.PassengerPost
import com.badcompany.pitakpass.domain.repository.PassengerPostRepository
import com.badcompany.pitakpass.domain.repository.PlaceRepository
import com.badcompany.pitakpass.util.ResponseWrapper
import javax.inject.Inject

/**
 * Provides an implementation of the [PlaceRepository] interface for communicating to and from
 * data sources
 */
class PassengerPostRepositoryImpl @Inject constructor(private val factoryPassenger: PassengerPostDataStoreFactory) :
    PassengerPostRepository {


    override suspend fun createPassengerPost(post: PassengerPost) =
        factoryPassenger.retrieveDataStore(false)
            .createPassengerPost(post)

    override suspend fun deletePassengerPost(identifier: String) =
        factoryPassenger.retrieveDataStore(false).deletePassengerPost(identifier)

    override suspend fun finishPassengerPost(identifier: String) =
        factoryPassenger.retrieveDataStore(false).finishPassengerPost(identifier)

    override suspend fun getActivePassengerPosts() =
        factoryPassenger.retrieveDataStore(false).getActivePassengerPosts()

    override suspend fun getHistoryPassengerPosts(page: Int) =
        factoryPassenger.retrieveDataStore(false).getHistoryPassengerPosts(page)

    override suspend fun getPassengerPostById(id: Long) =
        factoryPassenger.retrieveDataStore(false).getPassengerPostById(id)

    override suspend fun acceptOffer(id: Long) =
        factoryPassenger.retrieveDataStore(false).acceptOffer(id)

    override suspend fun rejectOffer(id: Long) =
        factoryPassenger.retrieveDataStore(false).rejectOffer(id)

    override suspend fun cancelMyOffer(id: Long) =
        factoryPassenger.retrieveDataStore(false).cancelMyOffer(id)


}