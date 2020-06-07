package ir.alirezanazari.vimeoapi.data.net.entity.search


import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("link_with_play_button")
    val linkWithPlayButton: String
)