package ir.alirezanazari.vimeoapi.ui.video

import androidx.lifecycle.viewModelScope
import ir.alirezanazari.vimeoapi.data.net.entity.comment.Comment
import ir.alirezanazari.vimeoapi.data.net.entity.search.Video
import ir.alirezanazari.vimeoapi.data.repository.VideoRepository
import ir.alirezanazari.vimeoapi.internal.SingleLiveEvent
import ir.alirezanazari.vimeoapi.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoViewModel(
    private val videoRepository: VideoRepository
) : BaseViewModel() {

    val videoResponse = SingleLiveEvent<Video>()
    val commentsResponse = SingleLiveEvent<List<Comment>>()
    private var isVideoInfoLoaded = false
    private var isCommentsLoaded = false

    fun getVideoInfo(uri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            setLoaderState(true)

            //get video info
            if (!isVideoInfoLoaded){
                val video = videoRepository.getVideo(uri)
                if (video != null) {
                    videoResponse.postValue(video)
                    isVideoInfoLoaded = true
                } else {
                    isVideoInfoLoaded = false
                }
            }

            //get video's comments
            if (!isCommentsLoaded){
                val comments = videoRepository.getVideoComments(uri, 1)
                if (comments != null) {
                    commentsResponse.postValue(comments)
                    isCommentsLoaded = true
                } else {
                    isCommentsLoaded = false
                }
            }

            //gone loader
            if (isCommentsLoaded && isVideoInfoLoaded) {
                setLoaderState(false)
            } else {
                setLoaderState(false, isEffectRetry = true)
            }
        }
    }
}
