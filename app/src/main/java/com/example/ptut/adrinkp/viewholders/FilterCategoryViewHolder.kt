package com.example.ptut.adrinkp.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.ptut.adrinkp.deligate.onTapFilterItem
import com.example.ptut.adrinkp.vos.filterList.DrinksCategoryFilter
import com.example.ptut.adrinkp.viewholders.base.BaseViewHolder
import kotlinx.android.synthetic.main.content_filter_item.view.*

class FilterCategoryViewHolder(itemView: View,private val tapItem:onTapFilterItem):BaseViewHolder<DrinksCategoryFilter>(itemView) {
    private lateinit var drinksCategoryFilter: DrinksCategoryFilter
    override fun setData(data: DrinksCategoryFilter) {
        drinksCategoryFilter=data
        if(data.strDrink!!.isEmpty() && data.strDrinkThumb!!.isEmpty())
            itemView.visibility=View.GONE
        Glide.with(itemView)
                .load(data.strDrinkThumb)
                .into(itemView.filterImage)
        itemView.filterName.text=data.strDrink
    }

    override fun onClick(v: View?) {
        tapItem.tapFilter(drinksCategoryFilter)
    }
}