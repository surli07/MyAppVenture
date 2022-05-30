package com.myappventure.app.ui.navigation.ui.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerSearchAdapter(fragmentAct: FragmentActivity) : FragmentStateAdapter(fragmentAct) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> KomunitasSearchFragment()
            1 -> PenggunaSearchFragment()
            else -> UnggahanSearchFragment()
        }
    }
}