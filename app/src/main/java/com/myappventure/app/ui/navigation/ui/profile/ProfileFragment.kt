package com.myappventure.app.ui.navigation.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.myappventure.app.R
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.databinding.FragmentProfileBinding
import com.myappventure.app.ui.MainFollowActivity
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.ui.navigation.NavigationActivity
import com.myappventure.app.ui.navigation.ui.profile.komunitas.KomunitasActivity
import com.myappventure.app.ui.navigation.ui.profile.profile.ProfileProfileActivity
import com.myappventure.app.ui.navigation.ui.profile.tentangkami.TentangKamiActivity
import com.myappventure.app.ui.navigation.ui.profile.unggahan.UnggahanSayaActivity
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by activityViewModels()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (MySharedPref.isLoggedIn) {
            lifecycleScope.launch {
                viewModel.getFollower()
                viewModel.getFollowing()
            }
            val username = MySharedPref.userName
            val photo = MySharedPref.userURLFilename
            binding.txtUserName.text = username
            if (photo != null) {
                binding.imgFoto.visibility = View.GONE
                binding.imgPhotoUser.visibility = View.VISIBLE
                Glide.with(requireContext())
                    .load(photo)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(binding.imgPhotoUser)
            } else {
                binding.imgFoto.visibility = View.VISIBLE
                binding.imgPhotoUser.visibility = View.GONE
            }

            binding.cardPenUpload.visibility = View.VISIBLE
            binding.Invite.visibility = View.VISIBLE
            binding.Logout.visibility = View.VISIBLE
            binding.recProfile.visibility = View.VISIBLE
            binding.icProfil.visibility = View.VISIBLE
            binding.txtProfil.visibility = View.VISIBLE
            binding.icNextP.visibility = View.VISIBLE
            binding.recYourPost.visibility = View.VISIBLE
            binding.icNextYP.visibility = View.VISIBLE
            binding.txtYourPost.visibility = View.VISIBLE
            binding.icYourPost.visibility = View.VISIBLE
            binding.recYourCommunity.visibility = View.VISIBLE
            binding.icYourCommunity.visibility = View.VISIBLE
            binding.txtYourCommunity.visibility = View.VISIBLE
            binding.icNextYC.visibility = View.VISIBLE
            binding.recTentangKita.visibility = View.VISIBLE
            binding.icTentangKita.visibility = View.VISIBLE
            binding.txtTentangKita.visibility = View.VISIBLE
            binding.icNextTentangKita.visibility = View.VISIBLE
            binding.imgBelumIkuti.visibility = View.GONE
            binding.txtAndaBelum.visibility = View.GONE
            binding.txtYukBergabung.visibility = View.GONE

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

            binding.txtTentangKita.setOnClickListener {
                val intent = Intent(requireContext(), TentangKamiActivity::class.java)
                startActivity(intent)
            }

            binding.icNextTentangKita.setOnClickListener {
                val intent = Intent(requireContext(), TentangKamiActivity::class.java)
                startActivity(intent)
            }

            binding.Logout.setOnClickListener {
                MySharedPref.logout()
                val intent = Intent(requireContext(), NavigationActivity::class.java)
                startActivity(intent)
                activity?.finishAffinity()
            }
            binding.icNextYP.setOnClickListener {
                val intent = Intent(requireContext(), UnggahanSayaActivity::class.java)
                startActivity(intent)
            }
            binding.txtYourPost.setOnClickListener {
                val intent = Intent(requireContext(), UnggahanSayaActivity::class.java)
                startActivity(intent)
            }
            binding.icTentangKita.setOnClickListener {
                val intent = Intent(requireContext(), TentangKamiActivity::class.java)
                startActivity(intent)
            }
        } else {
            binding.txtYukBergabung.setOnClickListener {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }
            binding.Invite.visibility = View.GONE
            binding.Logout.visibility = View.GONE
            binding.recProfile.visibility = View.GONE
            binding.icProfil.visibility = View.GONE
            binding.txtProfil.visibility = View.GONE
            binding.icNextP.visibility = View.GONE
            binding.recYourPost.visibility = View.GONE
            binding.icNextYP.visibility = View.GONE
            binding.txtYourPost.visibility = View.GONE
            binding.icYourPost.visibility = View.GONE
            binding.recYourCommunity.visibility = View.GONE
            binding.icYourCommunity.visibility = View.GONE
            binding.txtYourCommunity.visibility = View.GONE
            binding.icNextYC.visibility = View.GONE
            binding.recTentangKita.visibility = View.GONE
            binding.icTentangKita.visibility = View.GONE
            binding.txtTentangKita.visibility = View.GONE
            binding.icNextTentangKita.visibility = View.GONE
            binding.imgBelumIkuti.visibility = View.VISIBLE
            binding.txtAndaBelum.visibility = View.VISIBLE
            binding.txtYukBergabung.visibility = View.VISIBLE
        }
        setupObserver()
    }

    private fun setupObserver() {

        viewModel.jumlahFollowingResponse.observe(viewLifecycleOwner) {
            binding.txtMengikuti.text = it.data.toString() + " Mengikuti"
        }

        viewModel.jumlahFollowerResponse.observe(viewLifecycleOwner) {
            binding.txtPengikut.text = it.data.toString() + " Pengikut"
        }
    }

}