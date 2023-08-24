package com.hercan.harrypotterinfoapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hercan.harrypotterinfoapp.R
import com.hercan.harrypotterinfoapp.databinding.FragmentHomeBinding
import com.hercan.harrypotterinfoapp.presentation.adapter.CharactersAdapter
import com.hercan.harrypotterinfoapp.presentation.adapter.PotionsAdapter
import com.hercan.harrypotterinfoapp.presentation.adapter.SpellsAdapter
import com.hercan.harrypotterinfoapp.presentation.model.CharacterUIModel
import com.hercan.harrypotterinfoapp.presentation.room.FavoriteDatabase
import com.hercan.harrypotterinfoapp.presentation.viewbinding.viewBinding
import com.hercan.harrypotterinfoapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var potionsAdapter: PotionsAdapter
    private lateinit var spellsAdapter: SpellsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        bindUI()
        observeViewModelData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bindSpinner() = with(binding) {

        val characterFilterArray = resources.getStringArray(R.array.character_filter_array)
        val spinner = spinnerCharacter
        val spinnerAdapter =
            ArrayAdapter(requireContext(), R.layout.item_spinner, characterFilterArray)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?, position: Int, id: Long
            ) {

                charactersAdapter.submitList(listOf())
                charactersAdapter.notifyDataSetChanged()
                viewModel.getCharacters(characterFilterArray[position].toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun bindUI() = with(binding) {
        val favoriteDB = FavoriteDatabase.getInstance(requireContext())
        charactersAdapter = CharactersAdapter(favoriteDB)
        potionsAdapter = PotionsAdapter(favoriteDB)
        spellsAdapter = SpellsAdapter(favoriteDB)

        rvCharacter.apply {
            adapter = charactersAdapter
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
        }

        rvPotion.apply {
            adapter = potionsAdapter
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
        }

        rvSpell.apply {
            adapter = spellsAdapter
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
        }

        bindSpinner()

        charactersAdapter.setItemClickListener {
            navigateToCharacterDetail(it)
        }

        potionsAdapter.setItemClickListener {
            if (it.slug != null) {
                navigateToPotionDetail(it.slug)
            }
        }

        spellsAdapter.setItemClickListener {
            if (it.slug != null) {
                navigateToSpellDetail(it.slug)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeViewModelData() = with(binding) {
        viewModel.characters.observe(viewLifecycleOwner) {
            if (it != null) {
                charactersAdapter.notifyDataSetChanged()
                charactersAdapter.submitList(it)
                // charactersAdapter.notifyDataSetChanged()
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
            Toast.makeText(requireContext(), it ?: "error", Toast.LENGTH_LONG).show()
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
            Toast.makeText(requireContext(), it ?: "error", Toast.LENGTH_LONG).show()
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
            Toast.makeText(requireContext(), it ?: "error", Toast.LENGTH_LONG).show()
        }
    }

    private fun navigateToCharacterDetail(character: CharacterUIModel) {
        findNavController().navigate(
            HomeFragmentDirections.navigateToCharacterDetailFragment(
                character
            )
        )
    }

    private fun navigateToPotionDetail(potionSlug: String) {
        findNavController().navigate(
            HomeFragmentDirections.navigateToPotionDetailFragment(
                potionSlug
            )
        )
    }

    private fun navigateToSpellDetail(spellSlug: String) {
        findNavController().navigate(HomeFragmentDirections.navigateToSpellDetailFragment(spellSlug))
    }
}