package com.thawzintoe.ptut.adrinkp.deligate

import android.widget.ImageView
import com.thawzintoe.ptut.adrinkp.vos.filterList.DrinksCategoryFilter

interface onTapFilterItem {
    fun tapFilter(item:DrinksCategoryFilter,imageView: ImageView)
}