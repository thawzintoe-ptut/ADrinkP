package com.mmgoogleexpert.ptut.shared.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mmgoogleexpert.ptut.shared.data.Error

abstract class BasePresenter<T : BaseView> : ViewModel() {

    protected lateinit var mView: T
    private lateinit var mErrorLD: MutableLiveData<Error>

    val errorLD: MutableLiveData<Error>
        get() = mErrorLD

    open fun initPresenter(mView: T) {
        this.mView = mView
        mErrorLD = MutableLiveData()
    }
}