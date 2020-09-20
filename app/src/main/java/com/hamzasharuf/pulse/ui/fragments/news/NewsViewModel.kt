package com.hamzasharuf.pulse.ui.fragments.news

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.hamzasharuf.pulse.data.api.requests.NewsRequest
import com.hamzasharuf.pulse.data.models.News
import com.hamzasharuf.pulse.data.models.NewsSection
import com.hamzasharuf.pulse.data.repositories.NewsRepository
import com.hamzasharuf.pulse.utils.exceptions.NoInternetException
import com.hamzasharuf.pulse.utils.states.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsViewModel @ViewModelInject constructor(
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
                val isCacheAvailable = checkCacheAvailability(section)
                if (isCacheAvailable) {
                    val cachedNews = newsRepository.getNews(section.sectionName)
                    _allNews.postValue(Resource.success(cachedNews))
                }
                val newsList = newsRepository.fetchNews(NewsRequest(), section)
                _allNews.postValue(Resource.success(newsList))

                // Update the database with the new data
                if (isCacheAvailable) newsRepository.updateNews(newsList, section)
                else newsRepository.insertNews(newsList, section)

                isNewsAvailable = true
            }.onFailure {
                Timber.d("Error --> $it")
                when (it) {
                    is NoInternetException -> _allNews.postValue(Resource.error("No internet Connection"))
                    else -> _allNews.postValue(Resource.error("Something went wrong"))
                }

            }
        }
    }

    private suspend fun checkCacheAvailability(section: NewsSection): Boolean =
        !newsRepository.getSingleItem(section).isNullOrBlank()


}