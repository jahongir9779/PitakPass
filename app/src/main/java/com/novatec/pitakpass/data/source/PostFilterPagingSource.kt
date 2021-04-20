package com.novatec.pitakpass.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.novatec.pitakpass.domain.model.DriverPost
import com.novatec.pitakpass.domain.model.Filter
import com.novatec.pitakpass.remote.AuthorizedApiService
import com.novatec.pitakpass.util.valueNN
import retrofit2.HttpException
import java.io.IOException

private const val POST_OFFER_STARTING_PAGE_INDEX = 0

class PostFilterPagingSource(
    private val authorizedApiService: AuthorizedApiService,
    private val filter: LiveData<Filter>
) : PagingSource<Int, DriverPost>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DriverPost> {
        val position = params.key ?: POST_OFFER_STARTING_PAGE_INDEX

        return try {
            val response =
                authorizedApiService.filterDriverPost(filter.valueNN, position, params.loadSize)
            val posts = response.data?.data
            LoadResult.Page(
                data = posts!!,
                prevKey = if (position == POST_OFFER_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (posts.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }
}