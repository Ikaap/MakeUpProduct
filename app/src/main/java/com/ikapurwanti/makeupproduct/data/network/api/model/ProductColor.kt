package com.ikapurwanti.makeupproduct.data.network.api.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProductColor(
    @SerializedName("colour_name")
    val colourName: String?,
    @SerializedName("hex_value")
    val hexValue: String?
)