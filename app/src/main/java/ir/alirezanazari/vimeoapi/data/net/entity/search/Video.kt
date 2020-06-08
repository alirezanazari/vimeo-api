package ir.alirezanazari.vimeoapi.data.net.entity.search


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("uri")
    val uri: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("embed")
    val embed: Embed,
    @SerializedName("modified_time")
    val modifiedTime: String,
    @SerializedName("pictures")
    val pictures: Pictures
)