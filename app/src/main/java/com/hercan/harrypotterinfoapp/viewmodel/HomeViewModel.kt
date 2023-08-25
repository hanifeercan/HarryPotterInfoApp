package com.hercan.harrypotterinfoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hercan.harrypotterinfoapp.network.model.character.CharacterModel
import com.hercan.harrypotterinfoapp.network.model.potion.PotionData
import com.hercan.harrypotterinfoapp.network.model.spell.SpellData
import com.hercan.harrypotterinfoapp.network.repository.characters.CharactersRepositoryImpl
import com.hercan.harrypotterinfoapp.network.repository.potterdb.PotterDBRepositoryImpl
import com.hercan.harrypotterinfoapp.network.utils.Resource
import com.hercan.harrypotterinfoapp.network.utils.Status
import com.hercan.harrypotterinfoapp.presentation.model.*
import com.hercan.harrypotterinfoapp.presentation.room.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CharactersRepositoryImpl,
    private val potterRepository: PotterDBRepositoryImpl
) :
    ViewModel() {

    private val _characters = MutableLiveData<List<CharacterUIModel>?>()
    val characters: LiveData<List<CharacterUIModel>?> = _characters

    private val _isOnLoadingCharacters: MutableLiveData<Boolean> = MutableLiveData()
    val isOnLoadingCharacters: LiveData<Boolean> = _isOnLoadingCharacters

    private val _isOnErrorCharacters: MutableLiveData<String> = MutableLiveData()
    val isOnErrorCharacters: LiveData<String> = _isOnErrorCharacters

    private val _potions = MutableLiveData<List<PotionUIModel?>?>()
    val potions: LiveData<List<PotionUIModel?>?> = _potions

    private val _isOnLoadingPotions: MutableLiveData<Boolean> = MutableLiveData()
    val isOnLoadingPotions: LiveData<Boolean> = _isOnLoadingPotions

    private val _isOnErrorPotions: MutableLiveData<String> = MutableLiveData()
    val isOnErrorPotions: LiveData<String> = _isOnErrorPotions

    private val _spells = MutableLiveData<List<SpellUIModel?>?>()
    val spells: LiveData<List<SpellUIModel?>?> = _spells

    private val _isOnLoadingSpells: MutableLiveData<Boolean> = MutableLiveData()
    val isOnLoadingSpells: LiveData<Boolean> = _isOnLoadingSpells

    private val _isOnErrorSpells: MutableLiveData<String> = MutableLiveData()
    val isOnErrorSpells: LiveData<String> = _isOnErrorSpells

    private val _allFavoriteCharacters: MutableLiveData<List<FavoriteCharacterModel>> =
        MutableLiveData()
    val allFavoriteCharacters: LiveData<List<FavoriteCharacterModel>> = _allFavoriteCharacters

    private val _allFavoritePotions: MutableLiveData<List<FavoritePotionModel>> =
        MutableLiveData()
    val allFavoritePotions: LiveData<List<FavoritePotionModel>> = _allFavoritePotions

    private val _allFavoriteSpells: MutableLiveData<List<FavoriteSpellModel>> =
        MutableLiveData()
    val allFavoriteSpells: LiveData<List<FavoriteSpellModel>> = _allFavoriteSpells

    init {
        getCharacters()
        getPotions()
        getSpells()
        getFavoriteCharacters()
        getFavoritePotions()
        getFavoriteSpells()
    }

    fun getCharacters(house: String? = null) {
        viewModelScope.launch {
            repository.getAllCharacters()
                .onStart {
                    _isOnLoadingCharacters.postValue(true)
                }.map {
                    val filteredList = it.data?.filter { characterModel ->
                        when (house) {
                            null, "All" -> {
                                characterModel.house != null
                            }
                            "Unknown" -> {
                                characterModel.house == ""
                            }
                            else -> {
                                characterModel.house == house
                            }
                        }
                    }
                    return@map Resource.success(filteredList)
                }
                .onCompletion {
                    _isOnLoadingCharacters.postValue(false)
                }.catch {
                    _isOnErrorCharacters.postValue(it.localizedMessage)
                }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        val characters = it.data?.map(CharacterModel::toCharacterUIModel)
                        _characters.postValue(characters)
                    }
                }
        }
    }

    fun insertFavoriteCharacter(characterUIModel: CharacterUIModel, isFavorite: Boolean) =
        viewModelScope.launch {
            repository.insert(characterUIModel.toFavoriteCharacterModel(isFavorite))
        }

    fun updateFavoriteCharacter(id: String, isFavorite: Boolean) = viewModelScope.launch {
        repository.update(id, isFavorite)
    }

    fun insertFavoritePotion(potionUIModel: PotionUIModel, isFavorite: Boolean) =
        viewModelScope.launch {
            potterRepository.insertPotion(potionUIModel.toFavoritePotionModel(isFavorite))
        }

    fun updateFavoritePotion(id: String, isFavorite: Boolean) = viewModelScope.launch {
        potterRepository.updatePotion(id, isFavorite)
    }

    fun insertFavoriteSpell(spellUIModel: SpellUIModel, isFavorite: Boolean) =
        viewModelScope.launch {
            potterRepository.insertSpell(spellUIModel.toFavoriteSpellModel(isFavorite))
        }

    fun updateFavoriteSpell(id: String, isFavorite: Boolean) = viewModelScope.launch {
        potterRepository.updateSpell(id, isFavorite)
    }

    fun getFavoriteCharacters() {
        viewModelScope.launch {
            repository.allFavoriteCharacters.collect {
                _allFavoriteCharacters.postValue(it)
            }
        }
    }

    fun getFavoritePotions() {
        viewModelScope.launch {
            potterRepository.allFavoritePotions.collect {
                _allFavoritePotions.postValue(it)
            }
        }
    }

    fun getFavoriteSpells() {
        viewModelScope.launch {
            potterRepository.allFavoriteSpells.collect {
                _allFavoriteSpells.postValue(it)
            }
        }
    }

    private fun getPotions() {
        viewModelScope.launch {
            potterRepository.getAllPotions()
                .onStart {
                    _isOnLoadingPotions.postValue(true)
                }
                .onCompletion {
                    _isOnLoadingPotions.postValue(false)
                }
                .catch {
                    _isOnErrorPotions.postValue(it.localizedMessage)
                }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        val potions = it.data?.data?.map(PotionData?::toPotionUIModel)
                        _potions.postValue(potions)
                    }
                }
        }
    }

    private fun getSpells() {
        viewModelScope.launch {
            potterRepository.getAllSpells()
                .onStart {
                    _isOnLoadingSpells.postValue(true)
                }
                .onCompletion {
                    _isOnLoadingSpells.postValue(false)
                }
                .catch {
                    _isOnErrorSpells.postValue(it.localizedMessage)
                }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        val spells = it.data?.data?.map(SpellData?::toSpellUIModel)
                        _spells.postValue(spells)
                    }
                }
        }
    }
}