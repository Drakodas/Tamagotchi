package com.example.tamagotchie

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var Running = true

    val gameLoop = Thread() {
        val appobject = application as App
        var oldsize = appobject.size
        val sharedPref: SharedPreferences = getSharedPreferences("Tamagotchi", Context.MODE_PRIVATE)
        sharedPref.getInt("Size", 1)
        sharedPref.edit().putInt("Size", appobject.size).apply()

        while (Running) {
            val appobject = application as App
            appobject.size
            if (appobject.size != oldsize) {
                oldsize = appobject.size
                runOnUiThread { poop_image.visibility = View.VISIBLE }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameLoop.start()


        val button_level: ImageButton = findViewById(R.id.level_button)
        val button_food: ImageButton = findViewById(R.id.food_button)
        val button_poop: ImageButton = findViewById(R.id.poop_button)

        val appobject = application as App
        appobject.size

        button_level.setOnClickListener {
            startActivity(
                Intent(this, level::class.java)
            )

        }
        button_food.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Food")
            builder.setMessage("Choose the Food")
            builder.setPositiveButton("Powerpil") { dialog, which ->
                Toast.makeText(applicationContext, "You choosed Powerpil", Toast.LENGTH_SHORT)
                    .show()
                appobject.size += 1
            }
            builder.setNegativeButton("Flesh") { dialog, which ->
                Toast.makeText(applicationContext, "You choosed Flesh", Toast.LENGTH_SHORT).show()
                appobject.size += 1
            }
            val dialog: AlertDialog = builder.create()

            dialog.show()
        }

        button_poop.setOnClickListener {
            poop_image.visibility = View.INVISIBLE


        }


    }


    val random = Random()
    fun rand(from: Int, to: Int): Int {
        return random.nextInt(3 - 1) + 3
    }

    override fun onDestroy() {
        Running = false
        super.onDestroy()
    }
}