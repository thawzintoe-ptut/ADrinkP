package com.thawzintoe.ptut.adrinkp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.mmgoogleexpert.ptut.shared.ui.BaseRecyclerAdapter
import com.mmgoogleexpert.ptut.shared.ui.BaseViewHolder
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.deligate.onTapFilterItem
import com.thawzintoe.ptut.adrinkp.utils.inflate
import com.thawzintoe.ptut.adrinkp.vos.filterList.DrinksCategoryFilter
import com.thawzintoe.ptut.adrinkp.viewholders.FilterCategoryViewHolder

class CategoryFilterAdapter(context: Context,private val tapItem:onTapFilterItem) :
        BaseRecyclerAdapter<FilterCategoryViewHolder, DrinksCategoryFilter>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DrinksCategoryFilter> {
        return FilterCategoryViewHolder(parent.inflate(R.layout.content_filter_item),tapItem)
    }
}