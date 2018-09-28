package com.thawzintoe.ptut.adrinkp.deligate

import android.widget.ImageView
import com.thawzintoe.ptut.adrinkp.vos.searchList.SearchDrinksItem

interface onTapCocktailDetail {
    fun lunchCocktailDetail(searchDrinksItem: SearchDrinksItem,image:ImageView)
}