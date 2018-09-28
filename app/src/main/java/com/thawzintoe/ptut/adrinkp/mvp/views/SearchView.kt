package com.thawzintoe.ptut.adrinkp.mvp.views

import android.widget.ImageView
import com.thawzintoe.ptut.adrinkp.vos.searchList.SearchDrinksItem

interface SearchView :BaseView{
    fun lunchSearchDetailView(searchDrinksItem: SearchDrinksItem,imageView: ImageView)
}