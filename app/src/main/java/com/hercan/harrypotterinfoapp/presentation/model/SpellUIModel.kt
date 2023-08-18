package com.hercan.harrypotterinfoapp.presentation.model

import com.hercan.harrypotterinfoapp.network.model.spell.SpellData

data class SpellUIModel(
    val id: String?,
    val category: String,
    val creator: String,
    val effect: String,
    val hand: String,
    val image: String?,
    val incantation: String,
    val light: String,
    val name: String,
    val wiki: String?
)

fun SpellData?.toSpellUIModel(): SpellUIModel? {
    return this?.let {
        val id = this.id
        val category = this.attributes?.category ?: "Unknown"
        val creator = this.attributes?.creator ?: "Unknown"
        val effect = this.attributes?.effect ?: "Unknown"
        val hand = this.attributes?.hand ?: "Unknown"
        val image = this.attributes?.image
        val incantation = this.attributes?.incantation ?: "Unknown"
        val light = this.attributes?.light ?: "Unknown"
        val name = this.attributes?.name ?: "Unknown"
        val wiki = this.attributes?.wiki
        return SpellUIModel(
            id, category, creator, effect, hand, image, incantation, light, name, wiki
        )
    }
}