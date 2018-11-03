package com.thawzintoe.ptut.adrinkp.mvp.presenters

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class SearchViewModelFactory(private val repository: SearchPresenter) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchPresenter::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return repository as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}