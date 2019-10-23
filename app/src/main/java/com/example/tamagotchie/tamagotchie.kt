package com.example.tamagotchie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class tamagotchie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val appobject = application as App
        appobject.size




        while (appobject.size == +1) {
            poop_image.visibility = View.VISIBLE
        }
    }

    val random = Random()
    fun rand(from: Int, to: Int): Int {
        return random.nextInt(3 - 1) + 3
    }
}

