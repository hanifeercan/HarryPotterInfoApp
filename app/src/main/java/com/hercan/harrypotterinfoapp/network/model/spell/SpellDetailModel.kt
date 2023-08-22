package com.hercan.harrypotterinfoapp.network.model.spell

import com.google.gson.annotations.SerializedName

data class SpellDetailModel(
    @SerializedName("data")
    val `data`: SpellData?,
)