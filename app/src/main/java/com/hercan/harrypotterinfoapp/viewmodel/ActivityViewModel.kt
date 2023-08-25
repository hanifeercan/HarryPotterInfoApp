package com.hercan.harrypotterinfoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.hercan.harrypotterinfoapp.network.checkconnect.NetworkConnectivityMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    networkConnectivityMonitor: NetworkConnectivityMonitor
) : ViewModel() {

    val isConnected: Flow<Boolean> = networkConnectivityMonitor.isConnected
}