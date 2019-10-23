package com.example.tamagotchie

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class App : Application() {
    var size = 1
    override fun onCreate() {
        super.onCreate()
        val sharedPref: SharedPreferences = getSharedPreferences("Tamagotchi", Context.MODE_PRIVATE)
        sharedPref.edit().putInt("Size", size).apply()
        size = sharedPref.getInt("Size", 1)
    }
}


