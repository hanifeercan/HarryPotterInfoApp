package com.hercan.harrypotterinfoapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hercan.harrypotterinfoapp.R
import com.hercan.harrypotterinfoapp.databinding.FragmentSpellDetailBinding
import com.hercan.harrypotterinfoapp.presentation.model.SpellUIModel
import com.hercan.harrypotterinfoapp.presentation.viewbinding.viewBinding
import com.hercan.harrypotterinfoapp.viewmodel.SpellDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpellDetailFragment : Fragment(R.layout.fragment_spell_detail) {

    private val viewModel: SpellDetailViewModel by viewModels()
    private var spellSlug: String? = null
    private var spell: SpellUIModel? = null
    private val binding by viewBinding(FragmentSpellDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgs()
        observeViewModelData()
    }

    private fun getArgs() {
        if (arguments != null) {
            spellSlug = SpellDetailFragmentArgs.fromBundle(requireArguments()).spellSlug
            if (spellSlug != null) {
                viewModel.getSpellWithSlug(spellSlug!!)
            }
        }
    }

    private fun observeViewModelData() {
        viewModel.spell.observe(viewLifecycleOwner) {
            spell = it
            bindUI()
        }
    }

    private fun bindUI() = with(binding) {
        spell?.let { spell ->

            Picasso
                .get()
                .load(spell.image)
                .error(R.drawable.iv_default_spell_photo)
                .placeholder(R.drawable.iv_default_spell_photo)
                .into(ivPhoto)

            val unknown = getString(R.string.unknown)

            if (spell.creator == unknown) {
                llCreator.visibility = View.GONE
            } else {
                tvCreator.text = spell.creator
            }

            tvName.text = spell.name
            tvCategory.text = spell.category
            tvIncantation.text = spell.incantation
            tvHand.text = spell.hand
            tvEffect.text = spell.effect
            tvLight.text = spell.light

            if (spell.wiki != null) {
                btnWikipedia.setOnClickListener {
                    val uri = Uri.parse(spell.wiki)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            } else {
                btnWikipedia.visibility = View.GONE
            }
        }
    }
}