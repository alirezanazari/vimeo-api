package ir.alirezanazari.vimeoapi.data.net.entity.player


import com.google.gson.annotations.SerializedName

data class VideoPlayerResponse(
    @SerializedName("cdn_url")
    val cdnUrl: String,
    @SerializedName("request")
    val request: Request,
    @SerializedName("player_url")
    val playerUrl: String
)