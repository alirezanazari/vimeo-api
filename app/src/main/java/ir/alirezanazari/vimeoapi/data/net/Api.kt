package ir.alirezanazari.vimeoapi.data.net

import ir.alirezanazari.vimeoapi.data.net.entity.comment.CommentsResponse
import ir.alirezanazari.vimeoapi.data.net.entity.player.VideoPlayerResponse
import ir.alirezanazari.vimeoapi.data.net.entity.search.SearchVideoResponse
import ir.alirezanazari.vimeoapi.data.net.entity.search.Video
import ir.alirezanazari.vimeoapi.internal.Constants.Net.DIRECTION_ASC
import ir.alirezanazari.vimeoapi.internal.Constants.Net.REQUEST_PAGE_COUNT
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface Api {

    @GET("videos")
    fun getSearchVideo(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") count: Int = REQUEST_PAGE_COUNT,
        @Query("direction") direction: String = DIRECTION_ASC
    ): Deferred<SearchVideoResponse>

    @GET("videos/{uri}")
    fun getVideo(
        @Path("uri") uri: String
    ): Deferred<Video>

    @GET
    fun getVideoFile(
        @Url url: String
    ): Deferred<VideoPlayerResponse>

    @GET("videos/{uri}/comments")
    fun getVideoComments(
        @Path("uri") uri: String,
        @Query("page") page: Int,
        @Query("per_page") count: Int = REQUEST_PAGE_COUNT
    ): Deferred<CommentsResponse>
}