package ir.alirezanazari.vimeoapi.data.net.entity.comment


import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("uri")
    val uri: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("created_on")
    val createdOn: String,
    @SerializedName("user")
    val user: User
)