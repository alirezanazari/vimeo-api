package ir.alirezanazari.vimeoapi.data.net.entity.search


import com.google.gson.annotations.SerializedName

data class Pictures(
    @SerializedName("uri")
    val uri: String?,
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("sizes")
    val sizes: List<Picture>,
    @SerializedName("resource_key")
    val resourceKey: String
)