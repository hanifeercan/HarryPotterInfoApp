package com.hercan.harrypotterinfoapp.network.model.spell

import com.google.gson.annotations.SerializedName

data class SpellData(
    @SerializedName("attributes")
    val attributes: Attributes?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)
