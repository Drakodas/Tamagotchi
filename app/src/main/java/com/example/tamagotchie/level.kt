package com.example.tamagotchie

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class level : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level)

        val cancel: ImageButton by lazy { findViewById<ImageButton>(R.id.cancel) }
        var textSize: TextView = findViewById(R.id.text_sizelevel)
        var appobject = application as App
        textSize.text = appobject.size.toString()

        cancel.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )

        }
    }

}

