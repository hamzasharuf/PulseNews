package com.hamzasharuf.pulse.ui.fragments

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.hamzasharuf.pulse.data.api.requests.NewsRequest
import com.hamzasharuf.pulse.data.models.News
import com.hamzasharuf.pulse.data.repositories.NewsRepository
import com.hamzasharuf.pulse.utils.Constants
import com.hamzasharuf.pulse.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BaseArticlesViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _allNews = MutableLiveData<Resource<List<News>>>()
    val allNews: LiveData<Resource<List<News>>>
        get() = _allNews
    var isNewsAvailable = false


    fun getAllNews(section: String) {
        _allNews.postValue(Resource.loading())
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                val newsList = when(section){
                    Constants.HOME_SECTION -> newsRepository.fetchAllNews(NewsRequest())
                    Constants.WORLD_SECTION -> newsRepository.fetchWorldNews(NewsRequest())
                    Constants.SCIENCE_SECTION -> newsRepository.fetchScienceNews(NewsRequest())
                    Constants.SPORT_SECTION -> newsRepository.fetchSportNews(NewsRequest())
                    Constants.ENVIRONMENT_SECTION -> newsRepository.fetchEnvironmentNews(NewsRequest())
                    Constants.SOCIETY_SECTION -> newsRepository.fetchSocietyNews(NewsRequest())
                    Constants.FASHION_SECTION -> newsRepository.fetchFashionNews(NewsRequest())
                    Constants.BUSINESS_SECTION -> newsRepository.fetchBusinessNews(NewsRequest())
                    Constants.CULTURE_SECTION -> newsRepository.fetchCultureNews(NewsRequest())
                    else -> throw IllegalArgumentException("Unknown section: $section")
                }
                _allNews.postValue(Resource.success(newsList))
                isNewsAvailable = true
            }.onFailure {
                _allNews.postValue(Resource.error("Error --> $it"))
            }
        }
    }

    fun getFashionNews() {
        _allNews.postValue(Resource.loading())
        viewModelScope.launch(IO) {
            kotlin.runCatching {
                val newsList = newsRepository.fetchAllNews(NewsRequest("fashion"))
                _allNews.postValue(Resource.success(newsList))
                isNewsAvailable = true
            }.onFailure {
                _allNews.postValue(Resource.error("Error --> $it"))
            }
        }
    }

}