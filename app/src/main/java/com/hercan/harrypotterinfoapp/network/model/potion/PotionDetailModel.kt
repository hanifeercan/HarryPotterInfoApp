package com.hercan.harrypotterinfoapp.network.model.potion

import com.google.gson.annotations.SerializedName

data class PotionDetailModel(
    @SerializedName("data")
    val `data`: PotionData?,
)