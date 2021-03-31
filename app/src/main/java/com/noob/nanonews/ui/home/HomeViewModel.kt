package com.noob.nanonews.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noob.nanonews.data.model.NewsResponse
import com.noob.nanonews.domain.NewsRepository
import com.noob.nanonews.util.AppConst.Companion.TAG
import com.noob.nanonews.util.DispatcherProvider
import com.noob.nanonews.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepo: NewsRepository,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _topNews: MutableStateFlow<Resource<NewsResponse>> = MutableStateFlow(Resource.Empty)
    val topNews: StateFlow<Resource<NewsResponse>>
    get() = _topNews

    var topNewsPage = 1

    init {
        getTopNews("id")
    }

    fun getTopNews(countryCode: String) = viewModelScope.launch(dispatcher.io) {
        _topNews
        try {
            _topNews.value = newsRepo.getTopNews(countryCode, topNewsPage)
        } catch (e: Exception) {
            Log.e(TAG, "getTopNews: ", )
            _topNews.value = Resource.Failure(e)
        }
    }

}