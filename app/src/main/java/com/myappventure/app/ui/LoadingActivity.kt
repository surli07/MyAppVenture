package com.myappventure.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myappventure.app.R
import com.myappventure.app.ui.register.RegisterActivity

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        goToLanding()
    }

    private fun goToLanding() {
        Thread.sleep(3000)
        val i = Intent(this@LoadingActivity, RegisterActivity::class.java)
        finish()
        startActivity(i)
    }
}