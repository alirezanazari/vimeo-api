package ir.alirezanazari.vimeoapi.data.repository

import ir.alirezanazari.vimeoapi.data.net.entity.search.Video


interface VideoRepository {

    suspend fun getSearchVideo(query: String, page: Int, direction: String): List<Video>?
}