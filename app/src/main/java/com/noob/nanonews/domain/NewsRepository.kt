package com.noob.nanonews.domain

import com.noob.nanonews.data.model.NewsResponse
import com.noob.nanonews.util.Resource

interface NewsRepository {

    suspend fun getTopNews(
        countryCode: String,
        pageNumber: Int
    ): Resource<NewsResponse>

}