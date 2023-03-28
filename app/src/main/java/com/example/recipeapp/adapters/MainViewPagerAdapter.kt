package com.example.recipeapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.recipeapp.mainMenuFragments.DinnersPage
import com.example.recipeapp.mainMenuFragments.DrinksPage
import com.example.recipeapp.mainMenuFragments.SweetsPage

class MainViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity){
    private val fragments = mutableListOf<Fragment>()

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}