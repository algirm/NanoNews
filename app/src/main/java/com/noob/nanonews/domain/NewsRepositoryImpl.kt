package com.noob.nanonews.domain

import com.noob.nanonews.data.local.ArticleDatabase
import com.noob.nanonews.data.model.NewsResponse
import com.noob.nanonews.data.remote.NewsApi
import com.noob.nanonews.domain.NewsRepository
import com.noob.nanonews.util.Resource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getTopNews(
        countryCode: String,
        pageNumber: Int
    ): Resource<NewsResponse> {
        val response = newsApi.getTopNews(countryCode, pageNumber)
        if (response.isSuccessful) {
            return Resource.Success(response.body()?:
            return Resource.Failure(Exception(response.message())))
        } else {
            return Resource.Failure(Exception(response.message()))
        }
    }

}