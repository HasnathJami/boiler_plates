package com.jsn.msnhope.data.remote.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("category")
    private val _category: String?,
    @SerializedName("description")
    private val _description: String?,
    @SerializedName("id")
     val _id: Int?,
    @SerializedName("image")
    val _image: String?,
    @SerializedName("price")
    val _price: Double?,
    @SerializedName("rating")
    val _rating: Rating?,
    @SerializedName("title")
    val _title: String?
) {
    val category: String get() = _category ?: ""
    val description: String get() = _description ?: ""
}