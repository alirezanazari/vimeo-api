package ir.alirezanazari.vimeoapi.data.net.entity.player


import com.google.gson.annotations.SerializedName

data class Progressive(
    @SerializedName("width")
    val width: Int,
    @SerializedName("mime")
    val mime: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("cdn")
    val cdn: String,
    @SerializedName("quality")
    val quality: String,
    @SerializedName("id")
    val id: Int
)