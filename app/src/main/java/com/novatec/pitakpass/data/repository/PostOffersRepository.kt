package com.novatec.pitakpass.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.novatec.pitakpass.data.source.PostOffersPagingSource
import com.novatec.pitakpass.remote.AuthorizedApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostOffersRepository @Inject constructor(private val authorizedApiService: AuthorizedApiService) {

    fun getOffersForPost(id: Long) =
        Pager(config = PagingConfig(
            pageSize = 10,
            maxSize = 100,
            enablePlaceholders = false
        ),
              pagingSourceFactory = { PostOffersPagingSource(authorizedApiService, id) }).liveData
}