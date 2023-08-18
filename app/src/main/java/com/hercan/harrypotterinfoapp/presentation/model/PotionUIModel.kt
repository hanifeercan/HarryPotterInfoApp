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
    return this?.let {
        val id = this.id
        val characteristics = this.attributes?.characteristics ?: "Unknown"
        val difficulty = this.attributes?.difficulty ?: "Unknown"
        val effect = this.attributes?.effect ?: "Unknown"
        val image = this.attributes?.image
        val ingredients = this.attributes?.ingredients ?: "Unknown"
        val inventors = this.attributes?.inventors ?: "Unknown"
        val manufacturers = this.attributes?.manufacturers ?: "Unknown"
        val name = this.attributes?.name ?: "Unknown"
        val sideEffects = this.attributes?.sideEffects ?: "Unknown"
        val time = this.attributes?.time ?: "Unknown"
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