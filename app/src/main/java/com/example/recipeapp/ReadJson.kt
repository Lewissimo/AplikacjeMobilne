package com.example.recipeapp

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.recipeapp.recycleviewadapters.MenuAdapter

class ReadJson(context: Context, private val resources: Resources){
    private val packageName: String = context.packageName
    private val thisContext = context
    private val view: View = (context as AppCompatActivity).findViewById(android.R.id.content)

    data class Button(
        val name:String,
        val img: String
    )

    private fun createJsonObject(): Array<Button>? {
        val gson = Gson();
        val inputStream = resources.openRawResource(R.raw.abcde)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        return gson.fromJson(jsonString, Array<Button>::class.java)
    }


    private fun setRecycleView(): RecyclerView? {
        val recyclerView = view.findViewById<RecyclerView>(R.id.optionsBox)
        val layoutManager = GridLayoutManager(thisContext, 3)
        recyclerView.layoutManager = layoutManager
        return recyclerView
    }




    fun loadDataToMenu(){

        val jsonObject = createJsonObject()
        val recyclerView = setRecycleView()


        val adapter = MenuAdapter(jsonObject!!.toList(), resources, packageName)
        recyclerView?.adapter = adapter
    }


}