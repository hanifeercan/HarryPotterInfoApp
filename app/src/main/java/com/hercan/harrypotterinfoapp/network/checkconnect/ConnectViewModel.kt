package com.hercan.harrypotterinfoapp.network.checkconnect

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ConnectViewModel @Inject constructor(
    connectNetwork: ConnectNetwork
) : ViewModel() {

    val isConnected: Flow<Boolean> = connectNetwork.isConnected
}