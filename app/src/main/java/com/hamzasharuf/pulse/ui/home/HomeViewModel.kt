package com.hamzasharuf.pulse.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.hamzasharuf.pulse.data.api.requests.NewsRequest
import com.hamzasharuf.pulse.data.api.requests.SourcesRequest
import com.hamzasharuf.pulse.data.models.Article
import com.hamzasharuf.pulse.data.models.NewsSource
import com.hamzasharuf.pulse.data.repositories.NewsRepository
import com.hamzasharuf.pulse.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository
) : ViewModel() {

    // Provide news through a LiveData
    private val _news: MutableLiveData<Resource<List<Article>>> = MutableLiveData()
    val news: LiveData<Resource<List<Article>>>
        get() = _news


    // Prevent news from being fetched over and over every time the fragment is loaded
    var isNewsAvailable = false

    /**
     * Fetch the news from the api and cache it in the Database
     */
    fun getNews() {
        viewModelScope.launch(IO) {
            newsRepository.getNews(NewsRequest(keyword = "Madrid")).onEach {
                _news.value = it
            }
                .onCompletion { isNewsAvailable = true }
                .launchIn(viewModelScope)
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}