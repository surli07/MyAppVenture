package com.myappventure.app.ui.navigation.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.databinding.FragmentProfileBinding
import com.myappventure.app.ui.MainFollowActivity
import com.myappventure.app.ui.navigation.ui.profile.komunitas.KomunitasActivity
import com.myappventure.app.ui.navigation.ui.profile.pencapaian.PencapaianActivity
import com.myappventure.app.ui.navigation.ui.profile.profile.ProfileProfileActivity

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = MySharedPref.userName
        binding.txtUserName.text = username

        binding.txtPengikut.setOnClickListener {
            val intent = Intent(requireContext(), MainFollowActivity::class.java)
            startActivity(intent)
        }
        binding.txtMengikuti.setOnClickListener {
            val intent = Intent(requireContext(), MainFollowActivity::class.java)
            startActivity(intent)
        }
        binding.edtFoto.setOnClickListener {
        }
        binding.icNextP.setOnClickListener {
            val intent = Intent(requireContext(), ProfileProfileActivity::class.java)
            startActivity(intent)
        }
        binding.txtProfil.setOnClickListener {
            val intent = Intent(requireContext(), ProfileProfileActivity::class.java)
            startActivity(intent)
        }
        binding.icNextYC.setOnClickListener {
            val intent = Intent(requireContext(), KomunitasActivity::class.java)
            startActivity(intent)
        }
        binding.txtYourCommunity.setOnClickListener {
            val intent = Intent(requireContext(), KomunitasActivity::class.java)
            startActivity(intent)
        }
        binding.icNextPencapaian.setOnClickListener {
            val intent = Intent(requireContext(), PencapaianActivity::class.java)
            startActivity(intent)
        }
        binding.txtPencapaianSaya.setOnClickListener {
            val intent = Intent(requireContext(), PencapaianActivity::class.java)
            startActivity(intent)
        }

        setupObserver()
    }

    private fun setupObserver() {

    }

}