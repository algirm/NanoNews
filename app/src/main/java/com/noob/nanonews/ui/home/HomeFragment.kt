package com.noob.nanonews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.noob.nanonews.R
import com.noob.nanonews.ui.base.BaseFragment
import com.noob.nanonews.ui.view.NewsAdapter
import com.noob.nanonews.util.AppConst.Companion.TAG
import com.noob.nanonews.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_home,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        loadData()
    }

    private fun loadData() {
        lifecycleScope.launchWhenResumed {
            homeViewModel.topNews.collect { response ->
                if (response !is Resource.Loading) progress_bar.hide()
                when(response) {
                    is Resource.Failure -> toastError(response.throwable)
                    is Resource.Success -> newsAdapter.differ.submitList(response.data.articles.toList())
                    Resource.Empty -> newsAdapter.differ.submitList(emptyList())
                    Resource.Loading -> progress_bar.show()
                }
            }
        }
    }

    private fun init() {
        newsAdapter = NewsAdapter()
        home_rv.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}