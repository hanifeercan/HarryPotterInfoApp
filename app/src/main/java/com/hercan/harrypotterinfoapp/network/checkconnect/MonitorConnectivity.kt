package com.hercan.harrypotterinfoapp.network.checkconnect

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object MonitorConnectivity {
    fun isConnected(connectivityManager: ConnectivityManager): Boolean {
        return ActiveNetwork.isConnected(connectivityManager)
    }
}

interface ConnectedCompat {
    fun isConnected(connectivityManager: ConnectivityManager): Boolean
}

object ActiveNetwork : ConnectedCompat {
    override fun isConnected(connectivityManager: ConnectivityManager): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            ) == true
    }
}