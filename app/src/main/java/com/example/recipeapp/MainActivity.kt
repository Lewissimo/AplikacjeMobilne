package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader




// Objects to store data from json file

data class JSONData(
    val categories: Categories
)

data class Categories(
    val dinners: List<Any>,
    val drinks: List<Any>,
    val sweets: List<Sweet>
)

data class Sweet(
    val amount_of_steps: Int,
    val ingredients: List<Ingredient>,
    val name: String,
    val steps: List<String>,
    val time_of_preparing: Int
)

data class Ingredient(
    val amount: Int,
    val ingredient: String
)

//----------------------------



//







class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Reading data from json
            val gson = Gson()
            val inputF: InputStream = assets.open("recipes.json")
            val json = BufferedReader(InputStreamReader(inputF))
            val ecp = gson.fromJson(json, JSONData::class.java)


//        Displaying ingredients

            val recyclerView: RecyclerView = findViewById(R.id.optionsBox)
            recyclerView.layoutManager = GridLayoutManager(this, 3)
            recyclerView.adapter



    }
}