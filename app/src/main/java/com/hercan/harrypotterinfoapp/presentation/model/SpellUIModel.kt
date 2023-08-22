package com.hercan.harrypotterinfoapp.presentation.model

import com.hercan.harrypotterinfoapp.network.model.spell.SpellData

data class SpellUIModel(
    val id: String?,
    val category: String,
    val slug: String?,
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
    val unknown = "Unknown"
    return this?.let {
        val id = this.id
        val category = this.attributes?.category ?: unknown
        val slug = this.attributes?.slug
        val creator = this.attributes?.creator ?: unknown
        val effect = this.attributes?.effect ?: unknown
        val hand = this.attributes?.hand ?: unknown
        val image = this.attributes?.image
        val incantation = this.attributes?.incantation ?: unknown
        val light = this.attributes?.light ?: unknown
        val name = this.attributes?.name ?: unknown
        val wiki = this.attributes?.wiki
        return SpellUIModel(
            id, category, slug, creator, effect, hand, image, incantation, light, name, wiki
        )
    }
}