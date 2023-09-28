package com.hercan.harrypotterinfoapp.network.model.potion

import com.google.gson.annotations.SerializedName

data class PotionData(
    @SerializedName("attributes")
    val attributes: Attributes?,
    @SerializedName("id")
    val id: String?,
)