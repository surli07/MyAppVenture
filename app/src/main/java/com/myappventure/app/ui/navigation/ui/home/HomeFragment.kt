package com.myappventure.app.ui.navigation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.myappventure.app.R
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //private val viewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (MySharedPref.isLoggedIn) binding.imgCreate.visibility = View.VISIBLE

        binding.recyclerKategori.apply {
            this.layoutManager = GridLayoutManager(context, 4)
        }
        binding.viewPager.apply {
            this.adapter = ViewPagerAdapter(this@HomeFragment)
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            if (position == 0) {
                tab.text = "Untuk Kamu"
                tab.setIcon(R.drawable.ic_explore)
            } else {
                tab.text = "Diikuti"
                tab.setIcon(R.drawable.ic_following)
            }
            val color = ContextCompat.getColor(requireContext(), R.color.accent)
            val colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                color,
                BlendModeCompat.SRC_ATOP
            )
            tab.icon?.colorFilter = colorFilter
        }.attach()
        setupObserver()
    }

    private fun setupObserver() {

    }
}

