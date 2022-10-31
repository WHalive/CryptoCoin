package com.example.coincap

import android.app.Application

class CryptoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        CryptoDatabase.createInstance(this)
    }
}