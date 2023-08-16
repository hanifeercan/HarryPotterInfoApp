package com.hercan.harrypotterinfoapp.network.model.spell

import com.google.gson.annotations.SerializedName

data class SpellData(
    @SerializedName("attributes")
    val attributes: SpellModel.Data.Attributes?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)
