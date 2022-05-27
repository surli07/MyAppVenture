package com.myappventure.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myappventure.app.R
import com.myappventure.app.databinding.FragmentOnboardingContentBinding
import dagger.hilt.android.AndroidEntryPoint



private  const val  ARG_PARAM1 = "param1"

@AndroidEntryPoint
class OnboardingContentFragment : Fragment() {
    private var param1: String? = null
    private var _binding: FragmentOnboardingContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when(param1) {
            "3" -> {
                binding.imgAnimasi.setBackgroundResource(R.drawable.group_2071)
                binding.txtSelamatDatang.text = "Ayo Buat Komunitas \nPecinta Alam Lainnya"
                binding.txtDeskripsi.text = "Buat komunitas mu sendiri dan bagikan \npengalaman seru kamu hanya di \nMy Appventure"
            }
            "2" -> {
                binding.imgAnimasi.setBackgroundResource(R.drawable.exploring_pana_1)
                binding.txtSelamatDatang.text = "Jelajahi Tempat Menarik"
                binding.txtDeskripsi.text = "Jelajahi dan Temukan Wisata - Wisata \nAlam menarik yang ada di negeri kita \nhanya di My Appventure"
            }
            "1" -> {
                binding.imgAnimasi.setBackgroundResource(R.drawable.winners_pana_1)
                binding.txtSelamatDatang.text = "Selesaikan Tantangan"
                binding.txtDeskripsi.text = "Selesaikan Tantangan yang sudah \ndiberikan dan kumpulkan berbagai \nAchievement yang dapat dilihat oleh \ntemanmu atau pengguna lainnya."
            }
            else -> {
                binding.imgAnimasi.setBackgroundResource(R.drawable.social_life_pana_1)
                binding.txtSelamatDatang.text = "Selamat Datang di \nMy Appventure!"
                binding.txtDeskripsi.text = "Temukan berbagai Komunitas \nPecinta Alam yang menarik hanya \ndi My Appventure"
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            OnboardingContentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}