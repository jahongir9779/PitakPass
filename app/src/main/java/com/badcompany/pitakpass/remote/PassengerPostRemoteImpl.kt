package com.badcompany.remote

import com.badcompany.core.ErrorWrapper
import com.badcompany.core.ResultWrapper
import com.badcompany.data.model.FilterEntity
import com.badcompany.data.model.PassengerPostEntity
import com.badcompany.data.repository.PassengerPostRemote
import com.badcompany.remote.mapper.FilterMapper
import com.badcompany.remote.mapper.PassengerPostMapper
import javax.inject.Inject

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [BufferooRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class PassengerPostRemoteImpl @Inject constructor(private val apiService: ApiService,
                                                  private val postMapper: PassengerPostMapper,
                                                  private val filterMapper: FilterMapper) :
    PassengerPostRemote {

    override suspend fun filterPassengerPost(token: String,
                                             lang: String,
                                             filter: FilterEntity): ResultWrapper<List<PassengerPostEntity>> {
        return try {
            val response =
                apiService.filterPassengerPost(token, lang, filterMapper.mapFromEntity(filter))
            if (response.code == 1) {
                val posts = arrayListOf<PassengerPostEntity>()
                response.data?.data?.forEach { posts.add(postMapper.mapToEntity(it)) }
                ResultWrapper.Success(posts)
            } else ErrorWrapper.ResponseError(response.code, response.message)
        } catch (e: Exception) {
            ErrorWrapper.SystemError(e)
        }
    }


}