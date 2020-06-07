package ir.alirezanazari.vimeoapi.data.net

import ir.alirezanazari.vimeoapi.data.net.entity.search.Video


interface NetworkDataManager {

    suspend fun getSearchVideo(
        query: String,
        page: Int,
        direction: String
    ): List<Video>?
}