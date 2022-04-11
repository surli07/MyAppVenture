package com.myappventure.app.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.myappventure.app.dialog.CustomLoadingDialog
import com.orhanobut.hawk.BuildConfig

abstract class BaseActivity : AppCompatActivity() {

    private var isAlertShow = false

    override fun onStart() {
        super.onStart()
        if (BuildConfig.DEBUG) {
            window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        //setupObserver()
    }

    protected fun showMessage(message: Message) {
        when (message) {
            is Message.Toast -> showMessageToast(message.message)
            is Message.LongToast -> showMessageLongToast(message.message)
            is Message.Dialog -> showMessageDialog(message.message)
        }
    }

    protected fun showMessageDialog(msg: String?) {
        msg?.let {
            if (!isAlertShow) {
                AlertDialog.Builder(this)
                    .setMessage(it)
                    .setCancelable(false)
                    .setPositiveButton("OK") { dialog, _ ->
                        isAlertShow = false
                        dialog.dismiss()
                    }.show()
                isAlertShow = true
            }
        }
    }

    protected fun showMessageToast(msg: String?) {
        msg?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    protected fun showMessageLongToast(msg: String?) {
        msg?.let {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    protected abstract fun setupObserver()
}