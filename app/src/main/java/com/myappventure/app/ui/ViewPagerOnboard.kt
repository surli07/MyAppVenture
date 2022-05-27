package com.myappventure.app.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.myappventure.app.ui.OnboardingContentFragment

class ViewPagerOnboard(
    Fa: FragmentActivity
) : FragmentStateAdapter(Fa){
    private val dataFragments = mutableListOf(
        OnboardingContentFragment.newInstance("0"),
        OnboardingContentFragment.newInstance("1"),
        OnboardingContentFragment.newInstance("2"),
        OnboardingContentFragment.newInstance("3"),
    )

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment = dataFragments[position]
}