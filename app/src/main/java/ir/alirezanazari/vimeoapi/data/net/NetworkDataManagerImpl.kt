package ir.alirezanazari.vimeoapi.data.net

import ir.alirezanazari.vimeoapi.data.net.entity.comment.Comment
import ir.alirezanazari.vimeoapi.data.net.entity.search.Video
import ir.alirezanazari.vimeoapi.internal.Logger
import retrofit2.HttpException
import java.io.IOException

class NetworkDataManagerImpl(
    private val api: Api
) : NetworkDataManager {

    override suspend fun getSearchVideo(query: String, page: Int, direction: String): List<Video>? {
        return try {
            val response = api.getSearchVideo(query, page, direction = direction).await()
            return response.data
        } catch (e: HttpException) {
            Logger.showLog("Error fetch search result : ${e.message()}")
            null
        } catch (ex: IOException) {
            Logger.showLog("Error in connection : ${ex.message}")
            null
        }
    }

    override suspend fun getVideo(uri: String): Video? {
        return try {
            return api.getVideo(uri).await()
        } catch (e: HttpException) {
            e.printStackTrace()
            Logger.showLog("Error fetch video : ${e.message()}")
            null
        } catch (ex: IOException) {
            Logger.showLog("Error in connection : ${ex.message}")
            null
        }
    }

    override suspend fun getVideoComments(uri: String, page: Int): List<Comment>? {
        return try {
            val response = api.getVideoComments(uri, page).await()
            return response.comments
        } catch (e: HttpException) {
            e.printStackTrace()
            Logger.showLog("Error fetch comments : ${e.message()}")
            null
        } catch (ex: IOException) {
            Logger.showLog("Error in connection : ${ex.message}")
            null
        }
    }
}