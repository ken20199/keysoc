package com.test.assessment.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat


object DeviceUtils {
    var savedBrightness: Float = 0F

    fun isNetworkConnected(context: Context): Boolean {
        (context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            return activeNetworkInfo != null && activeNetworkInfo!!.isConnected
        }
    }

    fun dpToPx(dp: Float, context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun pxToDp(px: Float, context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return (px * displayMetrics.density).toInt()
    }

    fun getScreenWidth(context: Context?): Int {
        if (context == null)
            return 0

        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)

        return displayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        val windowManager = requireNotNull(ContextCompat.getSystemService(context, WindowManager::class.java))
        val size = Point()
        windowManager.defaultDisplay.getSize(size)
        return size.y
    }

    fun dpToPxHeight(dp: Float, context: Context?): Int {
        if (context == null) return 0
        val displayMetrics = context.resources.displayMetrics
        return Math.round(dp * (displayMetrics.ydpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun setScreenBrightness(window: Window, brightness: Float) {
        savedBrightness = window.attributes.screenBrightness
        val lp = window.attributes
        lp.screenBrightness = brightness
        window.attributes = lp
    }

    fun resetScreenBrightness(window: Window) {
        val lp = window.attributes
        lp.screenBrightness = savedBrightness
        window.attributes = lp
    }

    fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)

    fun Context.dpToPx(dp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()
}