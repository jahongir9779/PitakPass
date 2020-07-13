package com.badcompany.pitakpass.data.source

import com.badcompany.pitakpass.util.ResultWrapper
import com.badcompany.pitakpass.data.model.DriverPostEntity
import com.badcompany.pitakpass.data.repository.DriverPostDataStore
import com.badcompany.pitakpass.data.repository.DriverPostRemote
import com.badcompany.pitakpass.data.repository.PlaceDataStore
import com.badcompany.pitakpass.domain.domainmodel.DriverPost
import javax.inject.Inject

/**
 * Implementation of the [PlaceDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class DriverPostRemoteDataStore @Inject constructor(private val driverPostRemote: DriverPostRemote) :
    DriverPostDataStore {

    override suspend fun createDriverPost(token: String,
                                          post: DriverPostEntity): ResultWrapper<String> {
        return driverPostRemote.createDriverPost(token, post)
    }

    override suspend fun deleteDriverPost(token: String,
                                          identifier: String): ResultWrapper<String> {
        return driverPostRemote.deleteDriverPost(token, identifier)

    }

    override suspend fun finishDriverPost(token: String,
                                          identifier: String): ResultWrapper<String> {
        return driverPostRemote.finishDriverPost(token, identifier)

    }

    override suspend fun getActiveDriverPosts(token: String,
                                              lang: String): ResultWrapper<List<DriverPostEntity>> {

        return driverPostRemote.getActiveDriverPosts(token, lang)

    }

    override suspend fun getHistoryDriverPosts(token: String,
                                               lang: String,
                                               page: Int): ResultWrapper<List<DriverPostEntity>> {
        return driverPostRemote.getHistoryDriverPosts(token, lang,page)
    }


}