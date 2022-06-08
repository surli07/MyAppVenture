package com.myappventure.app.ui.navigation.ui.pengikut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.myappventure.app.data.local.MySharedPref
import com.myappventure.app.databinding.FragmentPengikutBinding
import kotlinx.coroutines.launch

class PengikutFragment : Fragment() {

    private var _binding: FragmentPengikutBinding? = null
    private val pengikutViewmodel: PengikutViewmodel by activityViewModels()
    private val pengikutAdapter = RecyclerPengikutAdapter()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPengikutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        if (MySharedPref.isLoggedIn) {
            lifecycleScope.launch {
                pengikutViewmodel.getListFollower()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerPengikut.apply {
            adapter = RecyclerPengikutAdapter()
            layoutManager = LinearLayoutManager(view.context)
        }
        setupObserver()
    }

    private fun setupObserver() {
        pengikutViewmodel.listFollowerResult.observe(viewLifecycleOwner) {
            pengikutAdapter.pengikut.clear()
            pengikutAdapter.pengikut.addAll(it)
            pengikutAdapter.notifyDataSetChanged()
        }
    }
}
