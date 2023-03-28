package com.example.recipeapp.adapters

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.DetailsActivity
import com.example.recipeapp.R
import com.example.recipeapp.ReadJson

class MenuAdapter(private val jsonData: List<ReadJson.Button>, resources: Resources, packageName: String) : RecyclerView.Adapter<MenuAdapter.ViewHolder>(){

    private val thisResources = resources
    private val thisPackageName = packageName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dish, parent, false)
        return ViewHolder(view,parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView.text = jsonData[position].name
        val drawableRes = thisResources.getIdentifier(jsonData[position].img, "drawable", thisPackageName)
        jsonData[position].steps[1]
        holder.btnPlace.setImageResource(drawableRes)
    }

    override fun getItemCount(): Int {
        return jsonData.size
    }
    inner class ViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.mealName)
        val btnPlace: ImageButton = view.findViewById(R.id.goToRecipe)
        init{
            btnPlace.setOnClickListener{
                val intent = Intent(context, DetailsActivity::class.java)
                println(jsonData[position].steps[0])
                intent.putExtra("data", jsonData[position].name)
                intent.putExtra("steps", jsonData[position].steps)
                intent.putExtra("ingredients", jsonData[position].ingredients)
                context.startActivity(intent)
            }
        }


    }

}