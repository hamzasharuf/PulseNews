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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _sources: MutableLiveData<Resource<List<NewsSource>>> = MutableLiveData()
    val sources: LiveData<Resource<List<NewsSource>>>
        get() = _sources

    private val _news: MutableLiveData<Resource<List<Article>>> = MutableLiveData()
    val news: LiveData<Resource<List<Article>>>
        get() = _news

    fun getSources(){
        viewModelScope.launch(IO){
            val sourcesList = newsRepository.getNewsSources(SourcesRequest()).onEach {
                _sources.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun getNews(){
        viewModelScope.launch(IO){
            val newsList = newsRepository.getNews(NewsRequest(keyword = "Turkey")).onEach {
                _news.value = it
            }.launchIn(viewModelScope)
        }
    }

    companion object{
        private const val TAG = "HomeViewModel"
    }
}