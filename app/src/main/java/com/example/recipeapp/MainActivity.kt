package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1:ImageButton = findViewById(R.id.imageButton);
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, Recipe::class.java)
            intent.putExtra("title", "Simple White 1")
            startActivity(intent)
        }

        val button2:ImageButton = findViewById(R.id.imageButton2);
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, Recipe::class.java)
            intent.putExtra("title", "Simple White 2")
            startActivity(intent)
        }

        val button3:ImageButton = findViewById(R.id.imageButton3);
        button3.setOnClickListener {
            val intent = Intent(this@MainActivity, Recipe::class.java)
            intent.putExtra("title", "Simple White 3")
            startActivity(intent)
        }

        print("reload")
    }
}