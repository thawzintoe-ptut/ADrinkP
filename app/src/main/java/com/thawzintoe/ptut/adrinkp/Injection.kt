package com.thawzintoe.ptut.adrinkp

import android.arch.lifecycle.ViewModelProvider
import com.thawzintoe.ptut.adrinkp.mvp.presenters.*

object Injection {
    fun provideViewModelFactoryCategory(categoryPresenter: CategoryPresenter): ViewModelProvider.Factory {
        return CategoryViewModelFactory(categoryPresenter)
    }

    fun provideViewModelFactoryFilter(filterPresenter: FilterPresenter): ViewModelProvider.Factory {
        return FilterViewModelFactory(filterPresenter)
    }

    fun provideViewModelLookUp(lookUpPresenter: LookUpPresenter): ViewModelProvider.Factory {
        return LookUpViewModelFactory(lookUpPresenter)
    }

    fun provideViewModelRandom(randomPresenter: RandomPresenter): ViewModelProvider.Factory {
        return RandomViewModelFactory(randomPresenter)
    }

    fun provideViewModelSearch(searchPresenter: SearchPresenter): ViewModelProvider.Factory {
        return SearchViewModelFactory(searchPresenter)
    }
}