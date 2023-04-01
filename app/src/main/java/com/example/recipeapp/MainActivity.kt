package com.example.recipeapp

import android.content.Context
import android.content.res.Configuration
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.recipeapp.adapters.MainViewPagerAdapter
import com.example.recipeapp.mainMenuFragments.DinnersPage
import com.example.recipeapp.mainMenuFragments.DrinksPage
import com.example.recipeapp.mainMenuFragments.SweetsPage
import com.google.gson.Gson
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainText: TextView = findViewById(R.id.textView)
        val buttonImgList: List<ImageButton> = listOf(findViewById(R.id.sweets_categoryBTN), findViewById(R.id.drinks_categoryBTN), findViewById(R.id.dinners_categoryBTN))

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mainText.visibility = View.VISIBLE

            for (el in buttonImgList){
                el.visibility = View.VISIBLE
            }
        } else {
            mainText.visibility = View.GONE

            for ((i, el) in buttonImgList.withIndex()){
                el.visibility = View.GONE
            }

        }

        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager)
        val fragmentList = arrayListOf(SweetsPage(), DrinksPage(), DinnersPage())
        val adapter = ViewPagerAdapter(this, fragmentList)
        viewPager2.adapter = adapter
    }

    private inner class ViewPagerAdapter(
        fragmentActivity: FragmentActivity,
        private val fragmentList: ArrayList<Fragment>
    ) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {

            return fragmentList[position]
        }
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val mainText: TextView = findViewById(R.id.textView)
        val buttonImgList: List<ImageButton> = listOf(findViewById(R.id.sweets_categoryBTN), findViewById(R.id.drinks_categoryBTN), findViewById(R.id.dinners_categoryBTN))
        val optionBOX: RecyclerView = findViewById(R.id.optionsBox)
        val fragmentSweets = supportFragmentManager.findFragmentById(R.id.sweets)
        val recyclerView = fragmentSweets?.view?.findViewById<RecyclerView>(R.id.optionsBox)
        val lyManager = optionBOX.layoutManager as? GridLayoutManager
        val viewPager2: ViewPager2 = findViewById(R.id.viewPager)
        val cardView = findViewById<CardView>(R.id.mainViewCard)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

//            lyManager?.spanCount = 4
            mainText.visibility = View.GONE

            for ((i, el) in buttonImgList.withIndex()){
                el.visibility = View.GONE
            }

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mainText.visibility = View.VISIBLE
//            lyManager?.spanCount = 3

            for (el in buttonImgList){
                el.visibility = View.VISIBLE
            }
        }
    }
}