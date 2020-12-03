package com.infotronic.tech.facade.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.test.assessment.R
import com.test.assessment.utils.DeviceUtils.isNetworkConnected
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.view.*


@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

    lateinit var active: BaseFragment

    abstract fun initUIControl()

    val networkConnected: Boolean
        get() {
            return isNetworkConnected(this)
        }

    val allDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }


    open val targetFragmentManager: FragmentManager
        get() = supportFragmentManager

    open val currentFragment: Fragment
        get() {
            return targetFragmentManager.fragments.reversed()[0]
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun printToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun printLog(msg: String) {
        Log.d(this.javaClass.simpleName, msg)
    }


    fun addFragment(fragment: BaseFragment) {
        targetFragmentManager.beginTransaction().add(R.id.container, fragment, "").commit()

    }

    fun hideFragment(fragment: BaseFragment) {
        targetFragmentManager.beginTransaction().hide(fragment).commit()
    }

    fun showFragment(fragment: BaseFragment) {
        targetFragmentManager.beginTransaction().show(fragment).commit()
        active = fragment
    }

}