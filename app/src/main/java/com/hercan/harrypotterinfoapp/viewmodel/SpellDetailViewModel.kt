package com.hercan.harrypotterinfoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hercan.harrypotterinfoapp.network.repository.potterdb.PotterDBRepositoryImpl
import com.hercan.harrypotterinfoapp.network.utils.Status
import com.hercan.harrypotterinfoapp.presentation.model.SpellUIModel
import com.hercan.harrypotterinfoapp.presentation.model.toSpellUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpellDetailViewModel @Inject constructor(
    private val potterRepository: PotterDBRepositoryImpl
) :
    ViewModel() {

    private val _spell = MutableLiveData<SpellUIModel?>()
    val spell: LiveData<SpellUIModel?> = _spell

    private val _isOnLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isOnLoading: LiveData<Boolean> = _isOnLoading

    private val _isOnError: MutableLiveData<String> = MutableLiveData()
    val isOnError: LiveData<String> = _isOnError

    fun getSpellWithSlug(slug: String) {
        viewModelScope.launch {
            potterRepository.getSpellWithSlug(slug)
                .onStart {
                    _isOnLoading.postValue(true)
                }
                .onCompletion {
                    _isOnLoading.postValue(false)
                }
                .catch {
                    _isOnError.postValue(it.localizedMessage)
                }
                .collect {
                    if (it.status == Status.SUCCESS) {
                        val spell = it.data?.data?.toSpellUIModel()
                        _spell.postValue(spell)
                    } else {
                        _isOnError.postValue(it.message ?: "Error")
                    }
                }
        }
    }
}