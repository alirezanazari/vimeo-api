package ir.alirezanazari.vimeoapi.data.repository

import ir.alirezanazari.vimeoapi.data.net.NetworkDataManager
import ir.alirezanazari.vimeoapi.data.net.entity.comment.Comment
import ir.alirezanazari.vimeoapi.data.net.entity.search.Video

/**
 * Could implement database , cache in future
 */
class VideoRepositoryImpl(
    private val net: NetworkDataManager
) : VideoRepository {

    override suspend fun getSearchVideo(query: String, page: Int, direction: String): List<Video>? {
        return net.getSearchVideo(query, page, direction)
    }

    override suspend fun getVideo(uri: String): Video? {
        return net.getVideo(uri)
    }

    override suspend fun getVideoComments(uri: String, page: Int): List<Comment>? {
        return net.getVideoComments(uri, page)
    }
}