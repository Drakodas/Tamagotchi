package com.example.tamagotchie

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class App : Application() {
    lateinit var settings: SharedPreferences

    var size = 1
    override fun onCreate() {
        super.onCreate()
        settings = getSharedPreferences("Tamagotchi", Context.MODE_PRIVATE)
        size = settings.getInt("Size", 1)


    }
}


