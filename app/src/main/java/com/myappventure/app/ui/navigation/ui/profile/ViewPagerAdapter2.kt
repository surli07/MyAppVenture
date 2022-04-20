package com.myappventure.app.ui.navigation.ui.profile

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.myappventure.app.ui.navigation.ui.mengikuti.MengikutiFragment
import com.myappventure.app.ui.navigation.ui.pengikut.PengikutFragment

class ViewPagerAdapter2(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PengikutFragment()
            else -> MengikutiFragment()
        }
    }
}