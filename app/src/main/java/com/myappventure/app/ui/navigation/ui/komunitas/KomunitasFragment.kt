package com.myappventure.app.ui.navigation.ui.komunitas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.myappventure.app.databinding.FragmentDestinasiBinding
import com.myappventure.app.databinding.FragmentKomunitasBinding

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

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        private fun setupObserver() {

        }
    }