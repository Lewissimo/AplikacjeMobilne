package com.example.recipeapp

import android.content.Context
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
}