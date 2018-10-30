package com.mmgoogleexpert.ptut.shared.ui

import android.annotation.SuppressLint
import com.mmgoogleexpert.ptut.shared.data.Error
import android.arch.lifecycle.Observer

@SuppressLint("Registered")
open class BaseActivity : android.support.v7.app.AppCompatActivity(), Observer<Error> {
    override fun onChanged(error: Error?) {

    }
}