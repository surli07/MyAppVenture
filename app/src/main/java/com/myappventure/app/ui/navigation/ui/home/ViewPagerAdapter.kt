package com.myappventure.app.ui.navigation.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.myappventure.app.ui.navigation.ui.home.challenge.TantanganFragment
import com.myappventure.app.ui.navigation.ui.home.foryou.ForYouFragment

class ViewPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ForYouFragment()
            else -> TantanganFragment()
        }
    }
}

