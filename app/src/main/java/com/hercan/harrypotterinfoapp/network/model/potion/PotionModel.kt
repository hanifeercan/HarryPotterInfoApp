package com.hercan.harrypotterinfoapp.network.model.potion

import com.google.gson.annotations.SerializedName

data class PotionModel(
    @SerializedName("data")
    val data: List<PotionData?>?,
)