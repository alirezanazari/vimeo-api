package ir.alirezanazari.vimeoapi.data.net

import ir.alirezanazari.vimeoapi.data.net.entity.comment.Comment
import ir.alirezanazari.vimeoapi.data.net.entity.search.Video


interface NetworkDataManager {

    suspend fun getSearchVideo(
        query: String,
        page: Int,
        direction: String
    ): List<Video>?

    suspend fun getVideo(
        uri: String
    ): Video?

    suspend fun getVideoComments(
        uri: String,
        page: Int
    ): List<Comment>?


}