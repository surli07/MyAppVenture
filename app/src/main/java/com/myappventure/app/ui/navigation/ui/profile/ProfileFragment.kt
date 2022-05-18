package com.myappventure.app.ui.navigation.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.myappventure.app.R
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.databinding.FragmentProfileBinding
import com.myappventure.app.ui.MainFollowActivity
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.ui.navigation.NavigationActivity
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

        if (MySharedPref.isLoggedIn) {
            val username = MySharedPref.userName
            val photo = MySharedPref.userFilename
            binding.txtUserName.text = username
//            binding.imgFoto.visibility = View.INVISIBLE
//            binding.imgPhotoUser.visibility = View.VISIBLE
//            Glide.with(requireContext())
//                .load(photo)
//                .error(R.drawable.ic_launcher_foreground)
//                .into(binding.imgPhotoUser)

            binding.cardPenUpload.visibility = View.VISIBLE
            binding.AboutUs.visibility = View.VISIBLE
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
            binding.recYourBill.visibility = View.VISIBLE
            binding.icYourBill.visibility = View.VISIBLE
            binding.txtYourBill.visibility = View.VISIBLE
            binding.icNextYB.visibility = View.VISIBLE
            binding.recMembership.visibility = View.VISIBLE
            binding.icMembership.visibility = View.VISIBLE
            binding.txtMembership.visibility = View.VISIBLE
            binding.icNextM.visibility = View.VISIBLE
            binding.recPencapaian.visibility = View.VISIBLE
            binding.icPencapaian.visibility = View.VISIBLE
            binding.txtPencapaianSaya.visibility = View.VISIBLE
            binding.icNextPencapaian.visibility = View.VISIBLE
            binding.recUbahSandi.visibility = View.VISIBLE
            binding.icUbahSandi.visibility = View.VISIBLE
            binding.txtUbahSandi.visibility = View.VISIBLE
            binding.icNextUbahSandi.visibility = View.VISIBLE
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
            binding.icNextPencapaian.setOnClickListener {
                val intent = Intent(requireContext(), PencapaianActivity::class.java)
                startActivity(intent)
            }
            binding.txtPencapaianSaya.setOnClickListener {
                val intent = Intent(requireContext(), PencapaianActivity::class.java)
                startActivity(intent)
            }
            binding.Logout.setOnClickListener {
                MySharedPref.logout()
                val intent = Intent(requireContext(), NavigationActivity::class.java)
                startActivity(intent)
                activity?.finishAffinity()
            }
        } else {
            binding.txtYukBergabung.setOnClickListener {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }

            binding.AboutUs.visibility = View.GONE
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
            binding.recYourBill.visibility = View.GONE
            binding.icYourBill.visibility = View.GONE
            binding.txtYourBill.visibility = View.GONE
            binding.icNextYB.visibility = View.GONE
            binding.recMembership.visibility = View.GONE
            binding.icMembership.visibility = View.GONE
            binding.txtMembership.visibility = View.GONE
            binding.icNextM.visibility = View.GONE
            binding.recPencapaian.visibility = View.GONE
            binding.icPencapaian.visibility = View.GONE
            binding.txtPencapaianSaya.visibility = View.GONE
            binding.icNextPencapaian.visibility = View.GONE
            binding.recUbahSandi.visibility = View.GONE
            binding.icUbahSandi.visibility = View.GONE
            binding.txtUbahSandi.visibility = View.GONE
            binding.icNextUbahSandi.visibility = View.GONE
            binding.imgBelumIkuti.visibility = View.VISIBLE
            binding.txtAndaBelum.visibility = View.VISIBLE
            binding.txtYukBergabung.visibility = View.VISIBLE
        }
//            binding.linearLayout.visibility = View.GONE
        setupObserver()
    }

    private fun setupObserver() {

    }

}