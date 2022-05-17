package com.myappventure.app.ui.navigation.ui.komunitas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.databinding.FragmentKomunitasBinding
import com.myappventure.app.ui.navigation.ui.community.NewCommunityActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KomunitasFragment : Fragment() {

    private var _binding: FragmentKomunitasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKomunitasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (MySharedPref.isLoggedIn) binding.imgCreate.visibility = View.VISIBLE

        binding.imgCreate.setOnClickListener {
            val intent = Intent(requireContext(), NewCommunityActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObserver() {

    }
}