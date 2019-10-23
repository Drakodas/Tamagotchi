package com.example.tamagotchie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    val button_level: ImageButton by lazy { findViewById<ImageButton>(R.id.level_button) }
    val button_food: ImageButton by lazy { findViewById<ImageButton>(R.id.food_button) }
    val button_poop: ImageButton by lazy { findViewById<ImageButton>(R.id.poop_button) }
    val set: Button by lazy { findViewById<Button>(R.id.set0) }
    //val button_training: ImageButton by lazy {findViewById<ImageButton>(R.id.training_button)}
    val picture_tamagotchi: ImageView by lazy { findViewById<ImageView>(R.id.tamagotchi) }

    var Running = true

    val gameLoop = Thread() {
        val appobject = application as App
        var oldsize = appobject.size
        var eventSizeStart: Long = 0L
        while (Running) {
            val zeitJetzt = System.currentTimeMillis()

            if (appobject.size == 15 && eventSizeStart == 0L) {
                eventSizeStart = System.currentTimeMillis()
            }

            if (eventSizeStart != 0L && zeitJetzt - eventSizeStart > 1000) {
                runOnUiThread { picture_tamagotchi.visibility = View.INVISIBLE }
            }

            if (appobject.size != oldsize) {
                oldsize = appobject.size
                runOnUiThread { poop_image.visibility = View.VISIBLE }
                appobject.settings.edit().putInt("Size", appobject.size).apply()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameLoop.start()


        val appobject = application as App
        appobject.size

        set.setOnClickListener {
            appobject.size = 0

        }

        button_level.setOnClickListener {
            startActivity(
                Intent(this, level::class.java)
            )

        }
        button_food.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Food")
            builder.setMessage("Choose the Food")
            builder.setPositiveButton("Pill") { dialog, which ->
                Toast.makeText(applicationContext, "You´ve chosen the Pill", Toast.LENGTH_SHORT)
                    .show()
                appobject.size += 1
            }
            builder.setNegativeButton("Meat") { dialog, which ->
                Toast.makeText(applicationContext, "You´ve chosen the Meat", Toast.LENGTH_SHORT)
                    .show()
                appobject.size += 1
            }
            val dialog: AlertDialog = builder.create()

            dialog.show()
        }

        button_poop.setOnClickListener {
            poop_image.visibility = View.INVISIBLE


        }

        /* button_training.setOnClickListener {
             startActivity(
                 Intent(this, training::class.java)
             )

         }*/


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
