package com.thawzintoe.ptut.adrinkp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.adapters.base.BaseRecyclerAdapter
import com.thawzintoe.ptut.adrinkp.deligate.onTapCategoryItem
import com.thawzintoe.ptut.adrinkp.utils.inflate
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksGlass
import com.thawzintoe.ptut.adrinkp.viewholders.GlassViewHolder
import com.thawzintoe.ptut.adrinkp.viewholders.base.BaseViewHolder

class GlassItemsAdapter(context:Context,private val tapClick: onTapCategoryItem):
        BaseRecyclerAdapter<GlassViewHolder, DrinksGlass>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DrinksGlass> {
        return GlassViewHolder(parent.inflate(R.layout.view_item_layout),tapClick)
    }
}