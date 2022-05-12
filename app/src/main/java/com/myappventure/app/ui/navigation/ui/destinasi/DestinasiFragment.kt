package com.myappventure.app.ui.navigation.ui.destinasi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.databinding.FragmentDestinasiBinding
import com.myappventure.app.ui.login.LoginActivity
import com.myappventure.app.ui.register.RegisterActivity

class DestinasiFragment : Fragment() {

    private var _binding: FragmentDestinasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDestinasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerKategori.apply {
            this.layoutManager = GridLayoutManager(context, 4)
        }

        setupObserver()
    }

    private fun setupObserver() {

    }
}