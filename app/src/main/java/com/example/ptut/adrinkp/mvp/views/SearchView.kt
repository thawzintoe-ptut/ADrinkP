package com.example.ptut.adrinkp.mvp.views

import com.example.ptut.adrinkp.vos.searchList.SearchDrinksItem

interface SearchView :BaseView{
    fun lunchSearchDetailView(searchDrinksItem: SearchDrinksItem)
}