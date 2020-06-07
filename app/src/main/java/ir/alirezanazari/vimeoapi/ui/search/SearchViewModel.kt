package ir.alirezanazari.vimeoapi.ui.search

import androidx.lifecycle.viewModelScope
import ir.alirezanazari.vimeoapi.data.net.entity.search.Video
import ir.alirezanazari.vimeoapi.data.repository.VideoRepository
import ir.alirezanazari.vimeoapi.internal.Constants.Net.DIRECTION_ASC
import ir.alirezanazari.vimeoapi.internal.SingleLiveEvent
import ir.alirezanazari.vimeoapi.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val videoRepository: VideoRepository
) : BaseViewModel() {

    val searchResponse = SingleLiveEvent<List<Video>>()
    var isLoading = false

    fun searchVideo(query: String, page: Int, direction: String = DIRECTION_ASC) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            setLoaderState(true)

            val response = videoRepository.getSearchVideo(query, page, direction)
            if (response != null) {
                setLoaderState(false)
                searchResponse.postValue(response)
            } else {
                setLoaderState(false, isEffectRetry = true)
            }
            isLoading = false
        }
    }
}
