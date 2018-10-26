package com.thawzintoe.ptut.adrinkp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.adapters.base.BaseRecyclerAdapter
import com.thawzintoe.ptut.adrinkp.deligate.onTapCategoryItem
import com.thawzintoe.ptut.adrinkp.utils.inflate
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksItem
import com.thawzintoe.ptut.adrinkp.viewholders.CategoryViewHolder
import com.thawzintoe.ptut.adrinkp.viewholders.base.BaseViewHolder

class CategoryItemsAdapter(context:Context,private val tapClick:onTapCategoryItem):
        BaseRecyclerAdapter<CategoryViewHolder, DrinksItem>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DrinksItem> {
        return CategoryViewHolder(parent.inflate(R.layout.view_item_layout),tapClick)
    }
}