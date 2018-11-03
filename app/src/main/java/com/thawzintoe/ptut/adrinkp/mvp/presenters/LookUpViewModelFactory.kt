package com.thawzintoe.ptut.adrinkp.mvp.presenters

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class LookUpViewModelFactory(private val repository: LookUpPresenter) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LookUpPresenter::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return repository as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}