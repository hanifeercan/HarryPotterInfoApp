package com.hercan.harrypotterinfoapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hercan.harrypotterinfoapp.R
import com.hercan.harrypotterinfoapp.databinding.FragmentHomeBinding
import com.hercan.harrypotterinfoapp.presentation.adapter.CharactersAdapter
import com.hercan.harrypotterinfoapp.presentation.adapter.PotionsAdapter
import com.hercan.harrypotterinfoapp.presentation.adapter.SpellsAdapter
import com.hercan.harrypotterinfoapp.presentation.model.CharacterUIModel
import com.hercan.harrypotterinfoapp.presentation.viewbinding.viewBinding
import com.hercan.harrypotterinfoapp.viewmodule.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val charactersAdapter = CharactersAdapter()
    private val potionsAdapter = PotionsAdapter()
    private val spellsAdapter = SpellsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        bindUI()
        observeViewModelData()
    }

    private fun bindUI() = with(binding) {
        rvCharacter.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = charactersAdapter
        }

        rvPotion.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = potionsAdapter
        }

        rvSpell.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = spellsAdapter
        }

        charactersAdapter.setItemClickListener {
            navigateToCharacterDetail(it)
        }

        potionsAdapter.setItemClickListener {
            if (it.id != null) {
                navigateToPotionDetail(it.id)
            }
        }
    }

    private fun observeViewModelData() = with(binding) {
        viewModel.characters.observe(viewLifecycleOwner) {
            if (it != null) {
                charactersAdapter.submitList(it)
            }
        }

        viewModel.isOnLoadingCharacters.observe(viewLifecycleOwner) {
            if (it) {
                progressBarCharacterRv.visibility = View.VISIBLE
            } else {
                progressBarCharacterRv.visibility = View.GONE
            }
        }

        viewModel.isOnErrorCharacters.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.potions.observe(viewLifecycleOwner) {
            if (it != null) {
                potionsAdapter.submitList(it)
            }
        }

        viewModel.isOnLoadingPotions.observe(viewLifecycleOwner) {
            if (it) {
                progressBarPotionRv.visibility = View.VISIBLE
            } else {
                progressBarPotionRv.visibility = View.GONE
            }
        }

        viewModel.isOnErrorPotions.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.spells.observe(viewLifecycleOwner) {
            if (it != null) {
                spellsAdapter.submitList(it)
            }
        }

        viewModel.isOnLoadingSpells.observe(viewLifecycleOwner) {
            if (it) {
                progressBarSpellRv.visibility = View.VISIBLE
            } else {
                progressBarSpellRv.visibility = View.GONE
            }
        }

        viewModel.isOnErrorSpells.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    private fun navigateToCharacterDetail(character: CharacterUIModel) {
        findNavController().navigate(
            HomeFragmentDirections.navigateToCharacterDetailFragment(
                character
            )
        )
    }

    private fun navigateToPotionDetail(potionID: String) {
        findNavController().navigate(HomeFragmentDirections.navigateToPotionDetailFragment(potionID))
    }
}