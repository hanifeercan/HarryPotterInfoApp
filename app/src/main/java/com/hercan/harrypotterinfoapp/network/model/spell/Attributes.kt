package com.hercan.harrypotterinfoapp.network.model.spell

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("category")
    val category: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("creator")
    val creator: String?,
    @SerializedName("effect")
    val effect: String?,
    @SerializedName("hand")
    val hand: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("incantation")
    val incantation: String?,
    @SerializedName("light")
    val light: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("wiki")
    val wiki: String?
)