package com.hercan.harrypotterinfoapp.viewmodule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hercan.harrypotterinfoapp.network.model.character.CharacterModel
import com.hercan.harrypotterinfoapp.network.model.potion.PotionData
import com.hercan.harrypotterinfoapp.network.model.spell.SpellData
import com.hercan.harrypotterinfoapp.network.repository.characters.CharactersRepositoryImpl
import com.hercan.harrypotterinfoapp.network.repository.potterdb.PotterDBRepositoryImpl
import com.hercan.harrypotterinfoapp.network.utils.Status
import com.hercan.harrypotterinfoapp.presentation.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private val _isOnErrorCharacters: MutableLiveData<String?> = MutableLiveData(null)
    val isOnErrorCharacters: LiveData<String?> = _isOnErrorCharacters

    private val _potions = MutableLiveData<List<PotionUIModel?>?>()
    val potions: LiveData<List<PotionUIModel?>?> = _potions

    private val _isOnLoadingPotions: MutableLiveData<Boolean> = MutableLiveData()
    val isOnLoadingPotions: LiveData<Boolean> = _isOnLoadingPotions

    private val _isOnErrorPotions: MutableLiveData<String?> = MutableLiveData(null)
    val isOnErrorPotions: LiveData<String?> = _isOnErrorPotions

    private val _spells = MutableLiveData<List<SpellUIModel?>?>()
    val spells: LiveData<List<SpellUIModel?>?> = _spells

    private val _isOnLoadingSpells: MutableLiveData<Boolean> = MutableLiveData()
    val isOnLoadingSpells: LiveData<Boolean> = _isOnLoadingSpells

    private val _isOnErrorSpells: MutableLiveData<String?> = MutableLiveData(null)
    val isOnErrorSpells: LiveData<String?> = _isOnErrorSpells

    init {
        getCharacters()
        getPotions()
        getSpells()
    }

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCharacters()
                .onStart {
                    _isOnLoadingCharacters.postValue(true)
                }
                .onCompletion {
                    _isOnLoadingCharacters.postValue(false)
                }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        val characters = it.data?.map(CharacterModel::toCharacterUIModel)
                        _characters.postValue(characters)
                    } else {
                        _isOnErrorCharacters.postValue(it.message)
                    }
                }
        }
    }

    fun getPotions() {
        viewModelScope.launch(Dispatchers.IO) {
            potterRepository.getAllPotions()
                .onStart {
                    _isOnLoadingPotions.postValue(true)
                }
                .onCompletion {
                    _isOnLoadingPotions.postValue(false)
                }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        val potions = it.data?.data?.map(PotionData?::toPotionUIModel)
                        _potions.postValue(potions)
                    } else {
                        _isOnErrorPotions.postValue(it.message)
                    }
                }
        }
    }

    fun getSpells() {
        viewModelScope.launch(Dispatchers.IO) {
            potterRepository.getAllSpells()
                .onStart {
                    _isOnLoadingSpells.postValue(true)
                }
                .onCompletion {
                    _isOnLoadingSpells.postValue(false)
                }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        val spells = it.data?.data?.map(SpellData?::toSpellUIModel)
                        _spells.postValue(spells)
                    } else {
                        _isOnErrorSpells.postValue(it.message)
                    }
                }
        }
    }
}