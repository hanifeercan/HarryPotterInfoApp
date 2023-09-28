package com.hercan.harrypotterinfoapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.hercan.harrypotterinfoapp.R
import com.hercan.harrypotterinfoapp.databinding.FragmentCharacterDetailBinding
import com.hercan.harrypotterinfoapp.presentation.model.CharacterUIModel
import com.hercan.harrypotterinfoapp.presentation.viewbinding.viewBinding
import com.squareup.picasso.Picasso

class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    private val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    private var character: CharacterUIModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgs()
        bindUI()
    }

    private fun getArgs() {
        if (arguments != null) {
            character = CharacterDetailFragmentArgs.fromBundle(requireArguments()).characterItem
        }
    }

    private fun bindUI() = with(binding) {
        character?.let {

            fragmentCharacterDetail.setBackgroundResource(it.houseColorId)
            icHouse.background =
                ResourcesCompat.getDrawable(resources, it.houseDrawableId, requireContext().theme)

            Picasso
                .get()
                .load(it.image)
                .error(R.drawable.iv_default_character_photo)
                .placeholder(R.drawable.iv_default_character_photo)
                .into(ivPhoto)

            if (!it.alternateNames.isNullOrEmpty()) {
                tvAlternativeNames.text = it.alternateNames
            } else {
                llAlternativeNames.visibility = View.GONE
            }

            tvCharacterName.text = it.characterName
            tvActorName.text = it.actorName
            tvSpecies.text = it.species
            tvStuffOrStudent.text = getString(it.hogwartsStaffOrStudentId)
            tvGender.text = it.gender

            if (getString(it.typeId) == getString(R.string.wizard)) {
                tvPatronus.text = it.patronus
                tvWandCore.text = it.wandCore
            } else {
                tvHeaderPatronus.visibility = View.GONE
                tvPatronus.visibility = View.GONE
                tvHeaderWandCore.visibility = View.GONE
                tvWandCore.visibility = View.GONE
            }
        }
    }
}