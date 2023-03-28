package com.example.recipeapp

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.example.recipeapp.adapters.MenuAdapter
import java.io.InputStream

class ReadJson(context: Context, private val resources: Resources){
    private val packageName: String = context.packageName
    private val thisContext = context
    data class Button(
        val name:String,
        val img: String
    )

    private fun createJsonObject(label: String): Array<Button>? {
        val gson = Gson();
        var inputStreamT: InputStream? = null

            when(label){
                "sweets" -> inputStreamT = resources.openRawResource(R.raw.sweets)
                "drinks" -> inputStreamT = resources.openRawResource(R.raw.drinks)
                "dinners" -> inputStreamT = resources.openRawResource(R.raw.dinners)
            }
        val inputStream: InputStream = inputStreamT!!
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        return gson.fromJson(jsonString, Array<Button>::class.java)
    }


    private fun setRecycleView(view: View): RecyclerView? {
        val recyclerView = view.findViewById<RecyclerView>(R.id.optionsBox)
        val layoutManager = GridLayoutManager(thisContext, 3)
        recyclerView.layoutManager = layoutManager
        return recyclerView
    }




    fun loadDataToMenu(view: View, label: String){
        val jsonObject = createJsonObject(label)
        val recyclerView = setRecycleView(view)

        val adapter = MenuAdapter(jsonObject!!.toList(), resources, packageName)
        recyclerView?.adapter = adapter
    }


}