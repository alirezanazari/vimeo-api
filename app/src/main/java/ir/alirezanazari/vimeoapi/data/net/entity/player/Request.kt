package ir.alirezanazari.vimeoapi.data.net.entity.player


import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("files")
    val files: Files,
    /*@SerializedName("referrer")
    val referrer: String?,*/
    @SerializedName("session")
    val session: String
)