package com.hercan.harrypotterinfoapp.network.model

import com.google.gson.annotations.SerializedName

data class PotionData(
    @SerializedName("attributes")
    val attributes: Attributes?,
    @SerializedName("id")
    val id: String?,
)