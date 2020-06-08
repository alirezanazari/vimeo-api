package ir.alirezanazari.vimeoapi.data.net.entity.search


import com.google.gson.annotations.SerializedName

data class SearchVideoResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("data")
    val data: List<Video>
)