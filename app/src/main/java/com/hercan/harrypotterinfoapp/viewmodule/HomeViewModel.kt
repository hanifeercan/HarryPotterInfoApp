package com.hercan.harrypotterinfoapp.viewmodule

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hercan.harrypotterinfoapp.network.repository.CharactersRepositoryImpl
import com.hercan.harrypotterinfoapp.network.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: CharactersRepositoryImpl) :
    ViewModel() {

    init {
        getCharacters()
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
}