package com.hercan.harrypotterinfoapp.network.model.character

import com.google.gson.annotations.SerializedName

data class Wand(
    @SerializedName("core")
    val core: String?,
)