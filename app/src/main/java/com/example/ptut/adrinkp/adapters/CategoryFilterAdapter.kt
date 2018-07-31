package com.example.ptut.adrinkp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.example.ptut.adrinkp.R
import com.example.ptut.adrinkp.adapters.base.BaseRecyclerAdapter
import com.example.ptut.adrinkp.deligate.onTapFilterItem
import com.example.ptut.adrinkp.vos.filterList.DrinksCategoryFilter
import com.example.ptut.adrinkp.viewholders.FilterCategoryViewHolder
import com.example.ptut.adrinkp.viewholders.base.BaseViewHolder

class CategoryFilterAdapter(context: Context,private val tapItem:onTapFilterItem) :
        BaseRecyclerAdapter<FilterCategoryViewHolder,DrinksCategoryFilter>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DrinksCategoryFilter> {
        val view:View=mLayoutInflator.inflate(R.layout.content_filter_item,parent,false)
        return FilterCategoryViewHolder(view,tapItem)
    }
}