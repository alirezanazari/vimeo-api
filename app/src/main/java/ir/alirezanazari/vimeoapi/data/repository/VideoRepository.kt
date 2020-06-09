package ir.alirezanazari.vimeoapi.data.repository

import ir.alirezanazari.vimeoapi.data.net.entity.comment.Comment
import ir.alirezanazari.vimeoapi.data.net.entity.player.Progressive
import ir.alirezanazari.vimeoapi.data.net.entity.search.Video


interface VideoRepository {

    suspend fun getSearchVideo(query: String, page: Int, direction: String): List<Video>?

    suspend fun getVideo(uri: String): Video?

    suspend fun getVideoComments(uri: String, page: Int): List<Comment>?

    suspend fun getVideoMP4File(url: String): List<Progressive>?
}