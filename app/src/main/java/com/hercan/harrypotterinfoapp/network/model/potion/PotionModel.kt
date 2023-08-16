package com.hercan.harrypotterinfoapp.network.model.potion

import com.google.gson.annotations.SerializedName
import com.hercan.harrypotterinfoapp.network.model.potion.PotionData

data class PotionModel(
    @SerializedName("data")
    val `data`: List<PotionData?>?,
)