package com.hercan.harrypotterinfoapp.network.model

import com.google.gson.annotations.SerializedName

data class Wand(
    @SerializedName("core")
    val core: String?,
)