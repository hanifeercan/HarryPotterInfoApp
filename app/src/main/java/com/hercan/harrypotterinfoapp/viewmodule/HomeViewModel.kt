package com.hercan.harrypotterinfoapp.viewmodule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hercan.harrypotterinfoapp.network.model.character.CharacterModel
import com.hercan.harrypotterinfoapp.network.repository.characters.CharactersRepositoryImpl
import com.hercan.harrypotterinfoapp.network.repository.potterdb.PotterDBRepositoryImpl
import com.hercan.harrypotterinfoapp.network.utils.Status
import com.hercan.harrypotterinfoapp.presentation.model.CharacterUIModel
import com.hercan.harrypotterinfoapp.presentation.model.toCharacterUIModel
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
                    Log.d("potionsData", "onStart")
                }
                .onCompletion {
                    Log.d("potionsData", "onCompletion")
                }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        Log.d("potionsData", it.data.toString())
                    } else {
                        Log.e("potionsData", it.message.toString())
                    }
                }
        }
    }

    fun getSpells() {
        viewModelScope.launch(Dispatchers.IO) {
            potterRepository.getAllSpells()
                .onStart {
                    Log.d("spellsData", "onStart")
                }
                .onCompletion {
                    Log.d("spellsData", "onCompletion")
                }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        Log.d("spellsData", it.data.toString())
                    } else {
                        Log.e("spellsData", it.message.toString())
                    }
                }
        }
    }
}