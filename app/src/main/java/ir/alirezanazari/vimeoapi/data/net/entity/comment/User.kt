package ir.alirezanazari.vimeoapi.data.net.entity.comment


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("uri")
    val uri: String,
    @SerializedName("name")
    val name: String
)