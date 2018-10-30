package com.thawzintoe.ptut.adrinkp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.mmgoogleexpert.ptut.shared.ui.BaseRecyclerAdapter
import com.mmgoogleexpert.ptut.shared.ui.BaseViewHolder
import com.thawzintoe.ptut.adrinkp.R
import com.thawzintoe.ptut.adrinkp.deligate.onTapCategoryItem
import com.thawzintoe.ptut.adrinkp.utils.inflate
import com.thawzintoe.ptut.adrinkp.vos.categoryList.DrinksIngredient
import com.thawzintoe.ptut.adrinkp.viewholders.IngredientViewHolder

class IngredientItemsAdapter(context: Context,private val tapClick: onTapCategoryItem):
        BaseRecyclerAdapter<IngredientViewHolder, DrinksIngredient>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DrinksIngredient> {
        return IngredientViewHolder(parent.inflate(R.layout.view_item_layout),tapClick)
    }
}