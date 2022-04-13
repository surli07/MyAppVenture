package com.myappventure.app.ui.navigation.ui.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.myappventure.app.ui.navigation.ui.mengikuti.MengikutiFragment
import com.myappventure.app.ui.navigation.ui.pengikut.PengikutFragment

class ViewPagerAdapter2(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PengikutFragment()
            else -> MengikutiFragment()
        }
    }
}