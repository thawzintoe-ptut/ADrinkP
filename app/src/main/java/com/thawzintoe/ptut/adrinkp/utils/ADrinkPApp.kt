package com.thawzintoe.ptut.adrinkp.utils

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.thawzintoe.ptut.adrinkp.models.*
import io.fabric.sdk.android.Fabric

class ADrinkPApp:Application() {
    override fun onCreate() {
        super.onCreate()
//        Fabric.with(this, Crashlytics())
        ItemListModel.initModel(this@ADrinkPApp)
        FilterModel.inintModel(this@ADrinkPApp)
        SearchModel.inintModel(this@ADrinkPApp)
        RandomModel.initModel(this@ADrinkPApp)
        LookUpModel.initModel(this@ADrinkPApp)
    }
}