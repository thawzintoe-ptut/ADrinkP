package com.example.ptut.adrinkp.mvp.views

import com.example.ptut.adrinkp.vos.filterList.DrinksCategoryFilter

interface FilterView :BaseView{
    fun launchDetails(item:DrinksCategoryFilter)
}