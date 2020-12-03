package com.infotronic.tech.facade.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.test.assessment.view.MainActivity
import javax.annotation.OverridingMethodsMustInvokeSuper


abstract class BaseFragment : Fragment() {

    abstract val layout: Int

    var rootView: View? = null

    companion object {
        val TAG: String = BaseFragment::javaClass.name
    }


    abstract fun subscribeViewModel()

    abstract fun initUIControl()


    @OverridingMethodsMustInvokeSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null && layout != 0) {
            rootView = inflater.inflate(layout, container, false)
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUIControl()
        subscribeViewModel()
    }


    @SuppressLint("CheckResult")
    private fun setToolBarClickListener() {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }



    override fun onResume() {
        super.onResume()
        setToolBarClickListener()

    }

    override fun onPause() {
        super.onPause()
    }

    fun printToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


    fun print(msg: String) {
        Log.d(TAG, msg)
    }

    protected fun onBackPress() {
        if (requireActivity() is MainActivity) {
            (activity as MainActivity).onBackPressed()
        }
    }




}