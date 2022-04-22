package com.myappventure.app.ui.navigation.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.myappventure.app.databinding.ActivityProfileBinding
import com.myappventure.app.databinding.FragmentProfileBinding
import com.myappventure.app.ui.MainFollowActivity
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.ui.navigation.ui.mengikuti.MengikutiFragment
import com.myappventure.app.ui.navigation.ui.pengikut.PengikutFragment
import com.myappventure.app.ui.navigation.ui.profile.profile.ProfileActivity
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

        binding.txtPengikut.setOnClickListener {
            val intent = Intent(requireContext(), MainFollowActivity::class.java)
            startActivity(intent)
        }
        binding.txtMengikuti.setOnClickListener {
            val intent = Intent(requireContext(), MainFollowActivity                                                          ::class.java)
            startActivity(intent)
        }
        binding.edtFoto.setOnClickListener {
        }
        binding.icNextP.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity                                                         ::class.java)
            startActivity(intent)
        }
        binding.txtProfil.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity                                                         ::class.java)
            startActivity(intent)
        }
        setupObserver()

    }

    private fun setupObserver() {

    }

}