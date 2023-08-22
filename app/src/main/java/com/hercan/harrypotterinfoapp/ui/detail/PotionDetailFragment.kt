package com.hercan.harrypotterinfoapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hercan.harrypotterinfoapp.R
import com.hercan.harrypotterinfoapp.databinding.FragmentPotionDetailBinding
import com.hercan.harrypotterinfoapp.presentation.model.PotionUIModel
import com.hercan.harrypotterinfoapp.presentation.viewbinding.viewBinding
import com.hercan.harrypotterinfoapp.viewmodel.PotionDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PotionDetailFragment : Fragment(R.layout.fragment_potion_detail) {
    private val viewModel: PotionDetailViewModel by viewModels()
    private var potionSlug: String? = null
    private var potion: PotionUIModel? = null
    private val binding by viewBinding(FragmentPotionDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgs()
        observeViewModelData()
    }

    private fun getArgs() {
        if (arguments != null) {
            potionSlug = PotionDetailFragmentArgs.fromBundle(requireArguments()).potionSlug
            if (potionSlug != null) {
                viewModel.getPotionWithSlug(potionSlug!!)
            }
        }
    }

    private fun observeViewModelData() {
        viewModel.potion.observe(viewLifecycleOwner) {
            potion = it
            bindUI()
        }
    }

    private fun bindUI() = with(binding) {
        potion?.let { potion ->

            Picasso
                .get()
                .load(potion.image)
                .error(R.drawable.iv_default_potion_photo)
                .placeholder(R.drawable.iv_default_potion_photo)
                .into(ivPhoto)

            val unknown = getString(R.string.unknown)

            if (potion.inventors == unknown && potion.manufacturers == unknown) {
                llInventorsAndManufacturers.visibility = View.GONE
            } else {
                tvInventors.text = potion.inventors
                tvManufacturers.text = potion.manufacturers
            }

            if (potion.ingredients == unknown) {
                llIngredients.visibility = View.GONE
            } else {
                tvIngredients.text = potion.ingredients
            }

            tvName.text = potion.name
            tvCharacteristics.text = potion.characteristics
            tvDifficulty.text = potion.difficulty
            tvEffect.text = potion.effect
            tvSideEffect.text = potion.sideEffects
            tvTime.text = potion.time

            if (potion.wiki != null) {
                btnWikipedia.setOnClickListener {
                    val uri = Uri.parse(potion.wiki)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            } else {
                btnWikipedia.visibility = View.GONE
            }
        }
    }
}