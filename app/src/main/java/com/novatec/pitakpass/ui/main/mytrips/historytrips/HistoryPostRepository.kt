package com.novatec.pitakpass.ui.main.mytrips.historytrips

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.novatec.pitakpass.remote.AuthorizedApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryPostRepository @Inject constructor(private val authorizedApiService: AuthorizedApiService) {

    fun getHistoryPosts() =
        Pager(config = PagingConfig(
            pageSize = 25,
            maxSize = 100,
            enablePlaceholders = false
        ),
              pagingSourceFactory = {
                  Log.d("", "")
                  HistoryPostPagingSource(authorizedApiService)
              }).liveData
}