package com.example.capfinal.ui.tabmain.mainadapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.capfinal.ui.tabmain.mainfragments.FirstFragment
import com.example.capfinal.ui.tabmain.mainfragments.FourthFragment
import com.example.capfinal.ui.tabmain.mainfragments.SecondFragment
import com.example.capfinal.ui.tabmain.mainfragments.ThirdFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> ThirdFragment()
            else -> FourthFragment()
        }
    }
}