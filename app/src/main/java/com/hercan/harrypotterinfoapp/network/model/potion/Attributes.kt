package com.hercan.harrypotterinfoapp.network.model.potion

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("characteristics")
    val characteristics: String?,
    @SerializedName("difficulty")
    val difficulty: String?,
    @SerializedName("effect")
    val effect: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("ingredients")
    val ingredients: String?,
    @SerializedName("inventors")
    val inventors: String?,
    @SerializedName("manufacturers")
    val manufacturers: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("side_effects")
    val sideEffects: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("wiki")
    val wiki: String?
)