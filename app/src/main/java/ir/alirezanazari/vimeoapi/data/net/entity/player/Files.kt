package ir.alirezanazari.vimeoapi.data.net.entity.player


import com.google.gson.annotations.SerializedName

data class Files(
    @SerializedName("progressive")
    val progressive: List<Progressive>
)