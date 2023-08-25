package com.hercan.harrypotterinfoapp.ui.home

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
import com.hercan.harrypotterinfoapp.presentation.model.PotionUIModel
import com.hercan.harrypotterinfoapp.presentation.model.SpellUIModel
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoriteCharacterModel
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoritePotionModel
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoriteSpellModel
import com.hercan.harrypotterinfoapp.presentation.viewbinding.viewBinding
import com.hercan.harrypotterinfoapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val charactersAdapter = CharactersAdapter()
    private val potionsAdapter = PotionsAdapter()
    private val spellsAdapter = SpellsAdapter()

    private var characters: List<CharacterUIModel>? = null
    private var potions: List<PotionUIModel?>? = null
    private var spells: List<SpellUIModel?>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        bindUI()
        observeViewModelData()
    }

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
                viewModel.getCharacters(characterFilterArray[position].toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun bindUI() = with(binding) {

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

        charactersAdapter.updateFavorite { character, isFavorite ->
            updateFavoriteCharacter(character, isFavorite)
        }

        potionsAdapter.updateFavorite { potion, isFavorite ->
            updateFavoritePotion(potion, isFavorite)
        }

        spellsAdapter.updateFavorite { spell, isFavorite ->
            updateFavoriteSpell(spell, isFavorite)
        }
    }

    private fun observeViewModelData() = with(binding) {
        viewModel.characters.observe(viewLifecycleOwner) {
            if (it != null) {
                charactersAdapter.submitList(it)
                characters = it
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
                potions = it
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
                spells = it
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

        viewModel.allFavoriteCharacters.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                characters?.map { character ->
                    insertFavoriteCharacter(character)
                }
                viewModel.getFavoriteCharacters()
            } else {
                charactersAdapter.favoriteCharacters = it
            }

        }

        viewModel.allFavoritePotions.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                potions?.map { potion ->
                    if (potion != null) insertFavoritePotion(potion)
                }
                viewModel.getFavoritePotions()
            } else {
                potionsAdapter.favoritePotions = it
            }
        }

        viewModel.allFavoriteSpells.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                spells?.map { spell ->
                    if (spell != null) insertFavoriteSpell(spell)
                }
                viewModel.getFavoriteSpells()
            } else {
                spellsAdapter.favoriteSpells = it
            }
        }
    }

    private fun insertFavoriteCharacter(character: CharacterUIModel) {
        viewModel.insertFavoriteCharacter(character, false)
    }

    private fun updateFavoriteCharacter(character: FavoriteCharacterModel, isFavorite: Boolean) {
        viewModel.updateFavoriteCharacter(character.id, isFavorite)
        viewModel.getFavoriteCharacters()
    }

    private fun insertFavoritePotion(potion: PotionUIModel) {
        viewModel.insertFavoritePotion(potion, false)
    }

    private fun updateFavoritePotion(potion: FavoritePotionModel, isFavorite: Boolean) {
        viewModel.updateFavoritePotion(potion.id, isFavorite)
        viewModel.getFavoritePotions()
    }

    private fun insertFavoriteSpell(spell: SpellUIModel) {
        viewModel.insertFavoriteSpell(spell, false)
    }

    private fun updateFavoriteSpell(spell: FavoriteSpellModel, isFavorite: Boolean) {
        viewModel.updateFavoriteSpell(spell.id, isFavorite)
        viewModel.getFavoriteSpells()
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