package com.example.capfinal.ui.tabmain

import android.view.LayoutInflater
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kg.geektech.finalprojectcustomcap.R
import com.example.capfinal.core.extentions.setIconAlpha
import com.example.capfinal.core.extentions.setIconColor
import kg.geektech.finalprojectcustomcap.databinding.ActivityMainBinding
import com.example.capfinal.core.base.BaseActivity
import com.example.capfinal.ui.tabmain.mainadapters.PagerAdapter

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel
        get() = TODO("Not yet implemented")

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initView() {
        initViewPager()
        initTabLayout()
    }

    private fun initTabLayout() {
        binding.tabLayout.tabIconTint = null
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.setIcon(R.drawable.ic_launcher_background)
                    setIconColor(tab, R.color.purple_500)
                }
                1 -> {
                    tab.setIcon(R.drawable.ic_launcher_background)
                    setIconColor(tab, R.color.teal_200)
                    setIconAlpha(tab, 70)
                }
                2 -> {
                    tab.setIcon(R.drawable.ic_launcher_background)
                    setIconColor(tab, R.color.custom_yellow)
                    setIconAlpha(tab, 70)
                }
                else -> {
                    tab.setIcon(R.drawable.ic_launcher_background)
                    setIconColor(tab, R.color.teal_700)
                    setIconAlpha(tab, 70)
                }
            }
        }.attach()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                setIconAlpha(tab, 250)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                setIconAlpha(tab, 70)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun initViewPager() {
        binding.viewPager2.adapter = PagerAdapter(this)
    }
}