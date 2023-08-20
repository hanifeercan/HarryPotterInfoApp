package com.hercan.harrypotterinfoapp.presentation.model

import com.hercan.harrypotterinfoapp.network.model.potion.PotionData

data class PotionUIModel(
    val id: String?,
    val characteristics: String,
    val difficulty: String,
    val effect: String,
    val image: String?,
    val ingredients: String,
    val inventors: String,
    val manufacturers: String,
    val name: String,
    val sideEffects: String,
    val time: String,
    val wiki: String?
)

fun PotionData?.toPotionUIModel(): PotionUIModel? {
    val unknown = "Unknown"
    return this?.let {
        val id = this.id
        val characteristics = this.attributes?.characteristics ?: unknown
        val difficulty = this.attributes?.difficulty ?: unknown
        val effect = this.attributes?.effect ?: unknown
        val image = this.attributes?.image
        val ingredients = this.attributes?.ingredients ?: unknown
        val inventors = this.attributes?.inventors ?: unknown
        val manufacturers = this.attributes?.manufacturers ?: unknown
        val name = this.attributes?.name ?: unknown
        val sideEffects = this.attributes?.sideEffects ?: unknown
        val time = this.attributes?.time ?: unknown
        val wiki = this.attributes?.wiki
        return PotionUIModel(
            id,
            characteristics,
            difficulty,
            effect,
            image,
            ingredients,
            inventors,
            manufacturers,
            name,
            sideEffects,
            time,
            wiki
        )
    }
}