package com.thawzintoe.ptut.adrinkp.activities.base

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import com.thawzintoe.ptut.adrinkp.utils.Error

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), Observer<Error> {
    override fun onChanged(error: Error?) {

    }
}