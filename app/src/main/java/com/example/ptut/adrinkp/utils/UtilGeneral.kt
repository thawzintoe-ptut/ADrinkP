package com.example.ptut.adrinkp.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.FrameLayout
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.activities.base.BaseActivity
import java.util.*

open class UtilGeneral {
    companion object {
        fun showNetworkError(rootLayout: View, context: Context, error: NetworkError) {
            Snackbar.make(rootLayout, error.msg, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Sorry for not available",null)
                    .setActionTextColor(ContextCompat.getColor(context, R.color.colorAccent)).show()

        }
        fun randomColor(view:View){
            val androidColors = view.resources.getIntArray(R.array.androidcolors)
            val randomAndroidColor = androidColors[Random().nextInt(androidColors.size)]
            view.setBackgroundColor(randomAndroidColor)
        }
    }

}