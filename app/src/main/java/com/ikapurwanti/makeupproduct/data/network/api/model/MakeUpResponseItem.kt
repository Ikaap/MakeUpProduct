package com.ikapurwanti.makeupproduct.data.network.api.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MakeUpResponseItem(

    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_link")
    val imageLink: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: String?,

)