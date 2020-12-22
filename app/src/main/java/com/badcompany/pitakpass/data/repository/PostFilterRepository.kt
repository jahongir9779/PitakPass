package com.badcompany.pitakpass.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.badcompany.pitakpass.data.source.PostFilterPagingSource
import com.badcompany.pitakpass.domain.model.Filter
import com.badcompany.pitakpass.remote.AuthorizedApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostFilterRepository @Inject constructor(private val authorizedApiService: AuthorizedApiService) {

    fun getFilteredPosts(filter: Filter) =
        Pager(config = PagingConfig(
            pageSize = 10,
            maxSize = 100,
            enablePlaceholders = false
        ),
              pagingSourceFactory = { PostFilterPagingSource(authorizedApiService, filter) }).liveData
}