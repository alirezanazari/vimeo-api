package ir.alirezanazari.vimeoapi.data.net.entity.comment


import com.google.gson.annotations.SerializedName

data class CommentsResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("data")
    val comments: List<Comment>
)