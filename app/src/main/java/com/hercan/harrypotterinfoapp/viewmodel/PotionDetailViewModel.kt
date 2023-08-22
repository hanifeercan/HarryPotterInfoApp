package com.hercan.harrypotterinfoapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hercan.harrypotterinfoapp.network.repository.potterdb.PotterDBRepositoryImpl
import com.hercan.harrypotterinfoapp.network.utils.Status
import com.hercan.harrypotterinfoapp.presentation.model.PotionUIModel
import com.hercan.harrypotterinfoapp.presentation.model.toPotionUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PotionDetailViewModel @Inject constructor(
    private val potterRepository: PotterDBRepositoryImpl
) :
    ViewModel() {

    private val _potion = MutableLiveData<PotionUIModel?>()
    val potion: LiveData<PotionUIModel?> = _potion

    private val _isOnLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isOnLoading: LiveData<Boolean> = _isOnLoading

    private val _isOnError: MutableLiveData<String> = MutableLiveData()
    val isOnError: LiveData<String> = _isOnError

    fun getPotionWithSlug(slug: String) {
        viewModelScope.launch {
            potterRepository.getPotionWithSlug(slug)
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
                         val potion = it.data?.data?.toPotionUIModel()
                        _potion.postValue(potion)
                    } else {
                        _isOnError.postValue(it.message ?: "Error")
                    }
                }
        }
    }
}