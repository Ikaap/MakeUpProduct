package com.ikapurwanti.makeupproduct.model

import com.google.gson.annotations.SerializedName
import com.ikapurwanti.makeupproduct.data.network.api.model.MakeUpResponseItem
import com.ikapurwanti.makeupproduct.data.network.api.model.ProductColor

data class MakeUpViewParam(
    val description: String?,
    val id: Int?,
    val imageLink: String?,
    val name: String?,
    val price: String?,
)

fun MakeUpResponseItem.toMakeUpViewParam() = MakeUpViewParam (
    description = this.description,
    id = this.id,
    imageLink = this.imageLink,
    name = this.name,
    price = this.price
)

fun Collection<MakeUpResponseItem>.toMakeUpViewParams() = this.map {
    it.toMakeUpViewParam()
}
