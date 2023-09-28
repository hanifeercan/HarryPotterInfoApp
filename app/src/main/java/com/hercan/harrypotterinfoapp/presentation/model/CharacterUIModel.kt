package com.hercan.harrypotterinfoapp.presentation.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.hercan.harrypotterinfoapp.R
import com.hercan.harrypotterinfoapp.network.model.character.CharacterModel
import com.hercan.harrypotterinfoapp.presentation.utils.toFirstCharUpperCase
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterUIModel(
    val id: String?,
    val actorName: String,
    @DrawableRes
    val aliveId: Int,
    val alternateNames: String?,
    @StringRes
    val typeId: Int,
    val species: String,
    val gender: String,
    @StringRes
    val hogwartsStaffOrStudentId: Int,
    @DrawableRes
    val houseDrawableId: Int,
    @ColorRes
    val houseColorId: Int,
    val image: String?,
    val characterName: String,
    val patronus: String,
    val wandCore: String,
) : Parcelable

fun CharacterModel.toCharacterUIModel(): CharacterUIModel {
    val unknown = "Unknown"
    return this.let {
        val id = it.id
        val actorName = if (it.actor.isNullOrEmpty()) unknown else it.actor.toFirstCharUpperCase()
        val aliveId = if (it.alive == true) {
            R.drawable.ic_live
        } else {
            R.drawable.ic_dead
        }
        val alternateNames = it.alternateNames.toAlternativeNames()

        val typeId = if (it.wizard == true) {
            R.string.wizard
        } else {
            if (it.ancestry == "squib") {
                R.string.squib
            } else if (it.species == "human") {
                R.string.muggle
            } else {
                R.string.animal
            }
        }
        val species = if (it.species.isNullOrEmpty()) unknown else it.species.toFirstCharUpperCase()
        val gender = if (it.gender.isNullOrEmpty()) unknown else it.gender.toFirstCharUpperCase()

        val hogwartsStaffOrStudentId = when {
            it.hogwartsStudent == true -> R.string.hogwarts_student
            it.hogwartsStaff == true -> R.string.hogwarts_stuff
            else -> R.string.not_a_student_or_stuff
        }

        val (houseDrawableId, houseColorId) = when (it.house) {
            "Gryffindor" -> {
                Pair(
                    R.drawable.ic_gryffindor,
                    R.color.gryffindor_red
                )
            }
            "Slytherin" -> {
                Pair(
                    R.drawable.ic_slytherin,
                    R.color.slytherin_green
                )
            }
            "Hufflepuff" -> {
                Pair(
                    R.drawable.ic_hufflepuff,
                    R.color.hufflepuff_yellow
                )
            }
            "Ravenclaw" -> {
                Pair(
                    R.drawable.ic_ravenclaw,
                    R.color.ravenclaw_blue
                )
            }
            else -> {
                Pair(
                    R.drawable.ic_hp,
                    R.color.bg_main
                )
            }
        }

        val image = if (it.image?.isEmpty() == true) null else it.image
        val characterName = if (it.name.isNullOrEmpty()) unknown else it.name.toFirstCharUpperCase()
        val patronus =
            if (it.patronus.isNullOrEmpty()) unknown else it.patronus.toFirstCharUpperCase()
        val wandCore =
            if (it.wand?.core.isNullOrEmpty()) unknown else it.wand?.core!!.toFirstCharUpperCase()

        CharacterUIModel(
            id,
            actorName,
            aliveId,
            alternateNames,
            typeId,
            species,
            gender,
            hogwartsStaffOrStudentId,
            houseDrawableId,
            houseColorId,
            image,
            characterName,
            patronus,
            wandCore,
        )
    }
}

fun List<String?>?.toAlternativeNames(): String {
    var str = ""
    this?.forEachIndexed { index, s ->
        str += s
        if (index != (this.size - 1)) {
            str += "\n\t\t\t\t"
        }
    }
    return str
}