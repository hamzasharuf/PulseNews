package com.hamzasharuf.pulse.ui.sources

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.hamzasharuf.pulse.data.api.requests.SourcesRequest
import com.hamzasharuf.pulse.data.models.NewsSource
import com.hamzasharuf.pulse.data.repositories.NewsRepository
import com.hamzasharuf.pulse.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SourcesViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository
) : ViewModel() {

    // Provide news sources
    private val _sources: MutableLiveData<Resource<List<NewsSource>>> = MutableLiveData()
    val sources: LiveData<Resource<List<NewsSource>>>
        get() = _sources

    // Prevent sources from being fetched over and over every time the fragment is loaded
    var isSourcesListAvailable = false

    /**
     * Fetch the news publications available on the api
     */
    fun getSources() {
        viewModelScope.launch(Dispatchers.IO) {
                newsRepository.getNewsSources(SourcesRequest()).onEach {
                _sources.value = it
            }
                .onCompletion { isSourcesListAvailable = true }
                .launchIn(viewModelScope)
        }
    }
}