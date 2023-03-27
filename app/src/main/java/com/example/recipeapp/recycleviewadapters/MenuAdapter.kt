package com.example.recipeapp.recycleviewadapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.ReadJson

class MenuAdapter(private val jsonData: List<ReadJson.Button>, resources: Resources, packageName: String) : RecyclerView.Adapter<MenuAdapter.ViewHolder>(){

    private val thisResources = resources
    private val thisPackageName = packageName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView.text = jsonData[position].name
        val drawableRes = thisResources.getIdentifier(jsonData[position].img, "drawable", thisPackageName)

        holder.btnPlace.setImageResource(drawableRes)

    }

    override fun getItemCount(): Int {
        return jsonData.size
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.mealName)
        val btnPlace: ImageButton = view.findViewById(R.id.goToRecipe)
    }

}