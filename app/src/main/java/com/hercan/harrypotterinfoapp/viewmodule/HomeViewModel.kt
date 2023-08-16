package com.hercan.harrypotterinfoapp.viewmodule

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hercan.harrypotterinfoapp.network.repository.characters.CharactersRepositoryImpl
import com.hercan.harrypotterinfoapp.network.repository.potterdb.PotterDBRepositoryImpl
import com.hercan.harrypotterinfoapp.network.utils.Status
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

    init {
        getCharacters()
        getPotions()
    }

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCharacters()
                .onStart {
                    Log.d("charactersData", "onStart")
                }
                .onCompletion {
                    Log.d("charactersData", "onCompletion")
                }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        Log.d("charactersData", it.data.toString())
                    } else {
                        Log.e("charactersData", it.message.toString())
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
}