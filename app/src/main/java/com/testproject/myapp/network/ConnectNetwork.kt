package com.testproject.myapp.network

import android.content.Context

object ConnectNetwork {
    private var internetCheck: InternetCheck? = null

    fun create(context: Context) {
        if (internetCheck == null) {
            internetCheck = InternetCheck(context)
        }
    }

    fun isConnect(): Boolean {
        return internetCheck!!.isConnect()
    }
}