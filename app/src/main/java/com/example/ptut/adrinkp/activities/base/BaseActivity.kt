package com.example.ptut.adrinkp.activities.base

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import com.example.ptut.adrinkp.utils.Error
import com.example.ptut.adrinkp.utils.UtilGeneral

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), Observer<Error> {
    override fun onChanged(error: Error?) {

    }
}