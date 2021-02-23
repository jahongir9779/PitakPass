package com.badcompany.pitakpass.ui.main.mytrips.historytrips

import androidx.paging.PagingSource
import com.badcompany.pitakpass.domain.model.PassengerPost
import com.badcompany.pitakpass.remote.AuthorizedApiService

private const val POST_OFFER_STARTING_PAGE_INDEX = 0

class HistoryPostPagingSource(private val authorizedApiService: AuthorizedApiService) :
    PagingSource<Int, PassengerPost>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PassengerPost> {
        val position = params.key ?: POST_OFFER_STARTING_PAGE_INDEX

        return try {
            val response =
                authorizedApiService.getHistoryPosts(position, params.loadSize)
            val posts = response.data?.data
            LoadResult.Page(
                data = posts!!,
                prevKey = if (position == POST_OFFER_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (posts.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }

    }
}