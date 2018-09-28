package com.thawzintoe.ptut.adrinkp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import com.bumptech.glide.Glide


fun isConnected(context: Context): Boolean {
    val conMan = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val infoWifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    if (infoWifi != null) {
        val wifi = infoWifi.state
        if (wifi == NetworkInfo.State.CONNECTED) {
            return true
        }
    }

    val infoMobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    if (infoMobile != null) {
        val mobile = infoMobile.state
        if (mobile == NetworkInfo.State.CONNECTED) {
            return true
        }
    }

    return false
}

fun isConnectedWifi(context: Context): Boolean {
    val info = getNetworkInfo(context)
    return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_WIFI
}

fun isConnectedMobile(context: Context): Boolean {
    val info = getNetworkInfo(context)
    return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_MOBILE
}

private fun getNetworkInfo(context: Context): NetworkInfo? {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo
}




