package com.hamzasharuf.pulse.ui.fragments

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.hamzasharuf.pulse.data.api.requests.NewsRequest
import com.hamzasharuf.pulse.data.models.News
import com.hamzasharuf.pulse.data.repositories.NewsRepository
import com.hamzasharuf.pulse.utils.NewsSection
import com.hamzasharuf.pulse.utils.Resource
import com.hamzasharuf.pulse.utils.exceptions.NoInternetException
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.IllegalArgumentException

class BaseArticlesViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _allNews = MutableLiveData<Resource<List<News>>>()
    val allNews: LiveData<Resource<List<News>>>
        get() = _allNews
    var isNewsAvailable = false


    fun getNews(section: NewsSection) {
        _allNews.postValue(Resource.loading())
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                val newsList = when(section){
                    NewsSection.HOME -> newsRepository.fetchAllNews(NewsRequest())
                    NewsSection.WORLD -> newsRepository.fetchWorldNews(NewsRequest())
                    NewsSection.SCIENCE -> newsRepository.fetchScienceNews(NewsRequest())
                    NewsSection.SPORT -> newsRepository.fetchSportNews(NewsRequest())
                    NewsSection.ENVIRONMENT -> newsRepository.fetchEnvironmentNews(NewsRequest())
                    NewsSection.SOCIETY -> newsRepository.fetchSocietyNews(NewsRequest())
                    NewsSection.FASHION -> newsRepository.fetchFashionNews(NewsRequest())
                    NewsSection.BUSINESS -> newsRepository.fetchBusinessNews(NewsRequest())
                    NewsSection.CULTURE -> newsRepository.fetchCultureNews(NewsRequest())
                }
                _allNews.postValue(Resource.success(newsList))
                isNewsAvailable = true
            }.onFailure {
                Timber.d("Error --> $it")
                when(it){
                    is NoInternetException -> _allNews.postValue(Resource.error("No internet Connection"))
                    else -> _allNews.postValue(Resource.error("Something went wrong"))
                }

            }
        }
    }


}