package ir.alirezanazari.vimeoapi.data.net.entity.search


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("modified_time")
    val modifiedTime: String,
    @SerializedName("pictures")
    val pictures: Pictures
)