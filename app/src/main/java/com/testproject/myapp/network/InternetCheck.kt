package com.testproject.myapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network

class InternetCheck(context: Context): ConnectListener{
    private var cm: ConnectivityManager? = null
    private var isConnectNetwork = false

    init {
        try {
            cm = context.getSystemService(ConnectivityManager::class.java)
                    as ConnectivityManager
            cm!!.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback(){

                override fun onAvailable(network: Network) {
                    isConnectNetwork = true
                }

                override fun onLost(network: Network) {
                    isConnectNetwork = false
                }
            })
        }catch (e: Exception) {
            isConnectNetwork = false
        }
    }

    override fun isConnect(): Boolean {
        return isConnectNetwork
    }
}