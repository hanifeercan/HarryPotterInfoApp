package com.hercan.harrypotterinfoapp.network.model.spell


import com.google.gson.annotations.SerializedName

data class SpellModel(
    @SerializedName("data")
    val `data`: List<SpellData?>?,
)