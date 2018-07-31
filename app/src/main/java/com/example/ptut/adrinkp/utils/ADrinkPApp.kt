package com.example.ptut.adrinkp.utils

import android.app.Application
import com.example.ptut.adrinkp.models.FilterModel
import com.example.ptut.adrinkp.models.ItemListModel
import com.example.ptut.adrinkp.models.SearchModel

class ADrinkPApp:Application() {
    override fun onCreate() {
        super.onCreate()
        TypefaceUtil.overrideFont(applicationContext, "SERIF", "font/ubuntu.ttf");
        ItemListModel.initModel(this@ADrinkPApp)
        FilterModel.inintModel(this@ADrinkPApp)
        SearchModel.inintModel(this@ADrinkPApp)
    }
}