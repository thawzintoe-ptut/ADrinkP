package com.thawzintoe.ptut.adrinkp.utils

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.crashlytics.android.Crashlytics
import com.thawzintoe.ptut.adrinkp.models.*
import io.fabric.sdk.android.Fabric

class ADrinkPApp:Application() {
    companion object {
        lateinit var instance: ADrinkPApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
////        Fabric.with(this, Crashlytics())
        ItemListModel.initModel(this@ADrinkPApp)
        FilterModel.inintModel(this@ADrinkPApp)
        SearchModel.inintModel(this@ADrinkPApp)
        RandomModel.initModel(this@ADrinkPApp)
        LookUpModel.initModel(this@ADrinkPApp)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}