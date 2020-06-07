package ir.alirezanazari.vimeoapi.data.net

import ir.alirezanazari.vimeoapi.data.net.entity.search.Video
import ir.alirezanazari.vimeoapi.internal.Constants.Net.DIRECTION_ASC


interface NetworkDataManager {

    suspend fun getSearchVideo(
        query: String,
        page: Int,
        direction: String = DIRECTION_ASC
    ): List<Video>?
}