package com.myappventure.app.ui.navigation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.myappventure.app.R
import com.myappventure.app.base.BaseActivity
import com.myappventure.app.databinding.ActivityNavigationBinding
import com.myappventure.app.dialog.CustomLoadingDialog
import com.myappventure.app.ui.navigation.ui.destinasi.BaliViewModel
import com.myappventure.app.ui.navigation.ui.destinasi.DestinasiViewModel
import com.myappventure.app.ui.navigation.ui.home.follow.DiikutiViewModel
import com.myappventure.app.ui.navigation.ui.home.foryou.PostinganViewModel
import com.myappventure.app.ui.navigation.ui.komunitas.KomunitasViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NavigationActivity : BaseActivity() {

    private lateinit var binding: ActivityNavigationBinding
    private val postinganDiikutiViewModel: DiikutiViewModel by viewModels()
    private val destinasiViewModel: DestinasiViewModel by viewModels()
    private val baliViewModel: BaliViewModel by viewModels()
    private val komunitasViewModel: KomunitasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)
        binding.navView.setupWithNavController(navController)

        val radius = resources.getDimension(R.dimen.radius_small)
        val bottomNavigation = binding.navView.background as MaterialShapeDrawable
        bottomNavigation.shapeAppearanceModel =
            bottomNavigation.shapeAppearanceModel.toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED, radius)
                .setTopLeftCorner(CornerFamily.ROUNDED, radius)
                .build()

        lifecycleScope.launch {
            postinganDiikutiViewModel.getAllPost()
            destinasiViewModel.getAllDestinasi()
            baliViewModel.getBaliDestinasi()
            komunitasViewModel.getListKomunitas()
        }

    }

    override fun setupObserver() {

    }
}