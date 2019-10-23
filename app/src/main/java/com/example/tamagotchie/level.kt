package com.example.tamagotchie

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class level : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level)


        var textSize: TextView = findViewById(R.id.text_sizelevel)
        var appobject = application as App
        textSize.text = appobject.size.toString()
    }

}

