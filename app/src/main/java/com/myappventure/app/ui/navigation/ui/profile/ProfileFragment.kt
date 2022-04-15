package com.myappventure.app.ui.navigation.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.myappventure.app.databinding.FragmentMengikutiBinding
import com.myappventure.app.databinding.FragmentPengikutBinding
import com.myappventure.app.databinding.FragmentProfileBinding
import com.myappventure.app.ui.register.RegisterActivity

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.apply {
            this.adapter = ViewPagerAdapter2(this@ProfileFragment)
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Pengikut"
                else -> "Mengikuti"
            }
        }.attach()

        binding.txtMengikuti.setOnClickListener { showTab(true) }
        binding.txtPengikut.setOnClickListener { showTab(true) }
        binding.edtFoto.setOnClickListener {
        }
        setupObserver()

    }

    private fun showTab(isShow: Boolean) {
        if (isShow) {
            binding.linearLayout.visibility = View.VISIBLE
            binding.viewPager.visibility = View.VISIBLE
        } else {
            binding.linearLayout.visibility = View.GONE
            binding.viewPager.visibility = View.GONE
        }
    }

    private fun setupObserver() {

    }

}